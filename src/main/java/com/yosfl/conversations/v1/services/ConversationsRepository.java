package com.yosfl.conversations.v1.services;

import com.yosfl.common.utils.CommonUtility;
import com.yosfl.conversations.v1.entities.dtos.*;
import com.yosfl.conversations.v1.entities.infra.Conversation;
import com.yosfl.conversations.v1.services.usersservicesext.UserService;
import com.yosfl.exceptions.ObjectNotFoundException;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotAuthorizedException;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ConversationsRepository implements PanacheRepository<Conversation> {

    @Inject
    ConversationsService conversationsService;

    @Inject
    UserService userService;

    public List<ConversationDTO> getAll(String email) throws ObjectNotFoundException {
        UserExt userConnected = userService.getUserExtByEmail(email);
        return conversationsService.getAllConversationsByUser(userConnected);
    }

    @Transactional
    public MessageDTO createMessage(MessageInputDTO input, Long idConversation, String email) throws ObjectNotFoundException {
        UserExt userConnected = userService.getUserExtByEmail(email);

        return conversationsService.addMessage(input, idConversation, userConnected.getId());
    }

    @Transactional
    public void createConversation(ConversationInitiateDTO input, String clientEmail) {
        // Check clientEmail is tecnical user
        if(!Objects.equals(clientEmail, CommonUtility.TECNICAL_USER)){
            throw new NotAuthorizedException("Seul le user "+CommonUtility.TECNICAL_USER+" peut creer une conversation");
        }
        Conversation conversation = new Conversation();
        conversation.setUserId(input.getUserId());
        conversation.setCreatorId(input.getCreatorId());
        conversation.setActive(true);
        conversation.persistAndFlush();


        MessageInputDTO message = new MessageInputDTO();
        message.setText("Welcome !");

        try {
            conversationsService.addMessage(message, conversation.getId(), input.getCreatorId());
        } catch (ObjectNotFoundException e) {
            Log.error("Message de bienvenue non enregistré : "+e.getMessage());
        }
    }

    @Transactional
    public boolean deleteMessage(String idConversation, String idMessage, String email)
            throws NumberFormatException, ObjectNotFoundException {
        UserExt userConnected = userService.getUserExtByEmail(email);
        boolean res =  conversationsService.deleteMessage(
                Long.parseLong(idConversation),
                Long.parseLong(idMessage),
                userConnected.getId());
        if(res){
            Log.info("Message supprimé dans la conversation "+idConversation);
        }
        return res;
    }

    public List<MessageDTO> getAllMessages(long idConversation, String email) throws ObjectNotFoundException {
        UserExt userConnected = userService.getUserExtByEmail(email);
        return conversationsService.getAllMessagesFromConversation(idConversation);
    }
}
