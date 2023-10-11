package com.yosfl.conversations.v1.services.infra;

import com.yosfl.conversations.v1.entities.dtos.*;
import com.yosfl.conversations.v1.entities.infra.Conversation;
import com.yosfl.conversations.v1.entities.infra.Message;
import com.yosfl.conversations.v1.services.ConversationsService;
import com.yosfl.exceptions.ObjectNotFoundException;
import com.yosfl.exceptions.PronosNotAuthorizedException;
import com.yosfl.exceptions.PronosNotFoundException;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@ApplicationScoped
public class ConversationsServicePostgresql implements ConversationsService {
    @Override
    public List<ConversationDTO> getAllConversationsByUser(UserExt userConnected) {
        List<Conversation> conversations =
                Conversation.list("userId = ?1 or creatorId = ?1", userConnected.getId());

        List<ConversationDTO> result = new ArrayList<>();
        for (Conversation conversation: conversations) {
            //Récupération du dernier message de la conversation
            Optional<Message> lastMessage =
                    Message.find("conversation",
                            Sort.by("creationDate").descending(), conversation).firstResultOptional();
            if(lastMessage.isPresent()) {
                ConversationDTO conv = new ConversationDTO();
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setCreationDate(lastMessage.get().getCreationDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                messageDTO.setText(lastMessage.get().getText());
                messageDTO.setAuthorId(lastMessage.get().getAuthorId());
                conv.setLastMessage(messageDTO);

                conv.setInterlocuteurId(
                        (Objects.equals(userConnected.getId(), conversation.getCreatorId()) ? conversation.getUserId() : conversation.getCreatorId())
                        );
                conv.setId(conversation.getId());
                result.add(conv);
            }
        }

        return result;
    }

    @Override
    public MessageDTO addMessage(MessageInputDTO input, Long idConversation, long idUserConnected)
            throws ObjectNotFoundException {
        Conversation conversation = Conversation.findById(idConversation);
        if(null == conversation) {
            throw new ObjectNotFoundException("Aucune conversation d'id " + idConversation);
        }
        else if(!conversation.isActive()){
            throw new PronosNotAuthorizedException("Conversation inactive");
        }
        Message message = new Message();
        message.setConversation(conversation);
        message.setText(input.getText());
        message.setCreationDate(LocalDateTime.now());
        message.setAuthorId(idUserConnected);
        message.persist();
        return message.toDTO();
    }

    @Override
    public boolean deleteMessage(long idConversation, long idMessage, long authorId) throws ObjectNotFoundException {

        Message message = Message.findById(idMessage);
        if(null != message &&
                Objects.equals(message.getConversation().getId(),idConversation) &&
                Objects.equals(message.getAuthorId(), authorId)){
            message.setDeleted(true);
            message.setText("");
            message.persist();
            return true;
        }
        return false;
    }

    @Override
    public List<MessageDTO> getAllMessagesFromConversation(long idConversation) {
        List<Message> messages = Message.list("conversation.id",
                Sort.by("creationDate"), idConversation);

        List<MessageDTO> result = new ArrayList<>(messages.size());
        for (Message mess : messages){
            MessageDTO dto = new MessageDTO();
            dto.setId(mess.getId());
            dto.setAuthorId(mess.getAuthorId());
            dto.setCreationDate(mess.getCreationDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            dto.setText(mess.getText());
            dto.setDeleted(mess.isDeleted());
            result.add(dto);
        }
        return result;
    }
}
