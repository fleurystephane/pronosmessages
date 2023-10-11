package com.yosfl.conversations.v1.entities.dtos;

import com.yosfl.conversations.v1.entities.infra.Message;

import java.util.List;

public class ConversationDTO {
    private MessageDTO lastMessage;

    private long interlocuteurId;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MessageDTO getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageDTO lastMessage) {
        this.lastMessage = lastMessage;
    }

    public long getInterlocuteurId() {
        return interlocuteurId;
    }

    public void setInterlocuteurId(long interlocuteurId) {
        this.interlocuteurId = interlocuteurId;
    }
}
