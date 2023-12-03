package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "chats")
@Entity(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "user_chat_connection_id")
    private UserChatConnection userChatConnection;
    @ManyToOne
    @JoinColumn(name = "message_ids")
    private Message message;

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

    public UserChatConnection getUserChatConnection() {
        return userChatConnection;
    }

    public void setUserChatConnection(UserChatConnection userChatConnection) {
        this.userChatConnection = userChatConnection;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chat chat)) return false;

        if (getId() != chat.getId()) return false;
        if (getCreationDate() != null ? !getCreationDate().equals(chat.getCreationDate()) : chat.getCreationDate() != null)
            return false;
        if (getUserChatConnection() != null ? !getUserChatConnection().equals(chat.getUserChatConnection()) : chat.getUserChatConnection() != null)
            return false;
        return getMessage() != null ? getMessage().equals(chat.getMessage()) : chat.getMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
        result = 31 * result + (getUserChatConnection() != null ? getUserChatConnection().hashCode() : 0);
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        return result;
    }

}
