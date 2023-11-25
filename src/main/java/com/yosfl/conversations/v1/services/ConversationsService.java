package com.yosfl.conversations.v1.services;

import com.yosfl.conversations.v1.entities.dtos.ConversationDTO;
import com.yosfl.conversations.v1.entities.dtos.MessageDTO;
import com.yosfl.conversations.v1.entities.dtos.MessageInputDTO;
import com.yosfl.conversations.v1.entities.dtos.UserExt;
import com.yosfl.conversations.v1.entities.Conversation;
import com.yosfl.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ConversationsService {
    /**
     * Recupère toutes les conversations d'un user avec uniquement le dernier message pour
     * chaque conversation
     * @param idUser l'utilisateur connecté
     * @return la liste des conversations
     */
    public List<ConversationDTO> getAllConversationsByUser(UserExt idUser);

    MessageDTO addMessage(MessageInputDTO input, Long idConversation, long idUserConnected) throws ObjectNotFoundException;

    boolean deleteMessage(long idConversation, long idMessage, long authorId) throws ObjectNotFoundException;

    List<MessageDTO> getAllMessagesFromConversation(long idConversation);

    public Optional<Conversation> getConversation(long userId, long creatorId);

    boolean disable(long userId, long creatorId) throws ObjectNotFoundException;

    long sendContentExToAll(long idContentEx, long idAuthor);
}
