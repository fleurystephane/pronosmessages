package com.yosfl.conversations.v1.entities.dtos;

public class ConversationDTO {
    private MessageDTO lastMessage;

    private long interlocuteurId;
    private long id;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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
