package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "messages")
@Entity(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;
    private String messageContent;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;

        if (getId() != message.getId()) return false;
        if (getCreationDate() != null ? !getCreationDate().equals(message.getCreationDate()) : message.getCreationDate() != null)
            return false;
        if (getMessageContent() != null ? !getMessageContent().equals(message.getMessageContent()) : message.getMessageContent() != null)
            return false;
        return getUserId() != null ? getUserId().equals(message.getUserId()) : message.getUserId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
        result = 31 * result + (getMessageContent() != null ? getMessageContent().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        return result;
    }
}
