package com.yosfl.conversations.v1.services;

import com.yosfl.common.utils.CommonUtility;
import com.yosfl.conversations.v1.entities.dtos.*;
import com.yosfl.conversations.v1.entities.Conversation;
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
import java.util.Optional;

@ApplicationScoped
public class ConversationsRepository implements PanacheRepository<Conversation> {

    @Inject
    ConversationsService conversationsService;

    @Inject
    UserService userService;

    public List<ConversationDTO> getAll(String email) throws ObjectNotFoundException {
        UserExt userConnected = userService.getUserExtByEmail(email);
        //TODO : attention si le user est un createur, retournera autant de conversations que d'abonnés...!!
        return conversationsService.getAllConversationsByUser(userConnected);
    }


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
        //Check if conversation already exists
        Optional<Conversation> conv = conversationsService.getConversation(input.getUserId(), input.getCreatorId());
        if(conv.isPresent()){
            Log.info("Aucune creation de nouvelle conversation car il en existe déjà une. On la reactive");
            Conversation conversation = conv.get();
            conversation.setActive(true);
            conversation.persist();
            return;
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

    @Transactional
    public boolean disableConversation(String userId, String creatorId, String email) throws ObjectNotFoundException {
        if(!Objects.equals(email, CommonUtility.TECNICAL_USER)){
            throw new NotAuthorizedException("Seul le user "+CommonUtility.TECNICAL_USER+" peut désactiver une conversation");
        }
        boolean res =  conversationsService.disable(
                Long.parseLong(userId), Long.parseLong(creatorId));

        if(!res){
            Log.error("La conversation entre "+userId+" et "+creatorId+" aurait du etre desactivee...!!");
        }
        return res;
    }

    public long sendContentEx(long idContentEx, long idAuthor, String email) {
        // Check email is tecnical user
        if(!Objects.equals(email, CommonUtility.TECNICAL_USER)){
            throw new NotAuthorizedException("Seul le user "+CommonUtility.TECNICAL_USER+" peut creer une conversation");
        }

        return conversationsService.sendContentExToAll(idContentEx, idAuthor);
    }

}
