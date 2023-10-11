package com.yosfl.conversations.v1.entities.dtos;

import jakarta.validation.constraints.NotEmpty;

public class MessageInputDTO {
    @NotEmpty
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
