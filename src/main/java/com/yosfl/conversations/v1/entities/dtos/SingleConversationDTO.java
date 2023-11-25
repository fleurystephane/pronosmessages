package com.yosfl.conversations.v1.entities.dtos;

import com.yosfl.conversations.v1.entities.Message;
import java.util.List;

public class SingleConversationDTO extends ConversationDTO {
    private List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
