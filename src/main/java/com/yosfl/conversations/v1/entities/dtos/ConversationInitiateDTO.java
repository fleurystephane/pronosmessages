package com.yosfl.conversations.v1.entities.dtos;

public class ConversationInitiateDTO {

    private long userId;
    private long creatorId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }
}
