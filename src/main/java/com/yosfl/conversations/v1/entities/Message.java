package com.yosfl.conversations.v1.entities;

import com.yosfl.common.utils.CommonUtility;
import com.yosfl.conversations.v1.entities.Conversation;
import com.yosfl.conversations.v1.entities.dtos.MessageDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name="message")
public class Message extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_seq")
    @SequenceGenerator(name = "message_id_seq", sequenceName = "message_id_seq",
            allocationSize = 1, initialValue = 100)
    private long id;
    @NotNull
    private String text;
    @NotNull
    private long authorId;
    @NotNull
    private LocalDateTime creationDate;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id", nullable = false)
    private Conversation conversation;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public MessageDTO toDTO() {
        MessageDTO dto = new MessageDTO();
        dto.setText((this.isDeleted() ? "" : this.getText()));
        dto.setAuthorId(this.getAuthorId());
        dto.setDeleted(this.isDeleted());
        dto.setId(this.getId());
        dto.setCreationDate(CommonUtility.formatDateTime(this.getCreationDate()));
        return dto;
    }
}
