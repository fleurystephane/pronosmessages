package com.yosfl.conversations.v1.entities.infra;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name="conversation")
public class Conversation extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conversation_id_seq")
    @SequenceGenerator(name = "conversation_id_seq", sequenceName = "conversation_id_seq",
            allocationSize = 1, initialValue = 100)
    private long id;

    @NotNull
    private long userId;
    @NotNull
    private long creatorId;

    @NotNull
    @ColumnDefault("true")
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conversation", fetch = FetchType.LAZY)
    @OrderColumn(name = "message_order")
    private List<Message> messages;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
