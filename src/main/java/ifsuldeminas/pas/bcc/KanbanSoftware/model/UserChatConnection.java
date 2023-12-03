package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "user_chat_connections")
@Entity(name = "user_chat_connections")
public class UserChatConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserChatConnection that)) return false;

        if (getId() != that.getId()) return false;
        if (getCreationDate() != null ? !getCreationDate().equals(that.getCreationDate()) : that.getCreationDate() != null)
            return false;
        return getUserId() != null ? getUserId().equals(that.getUserId()) : that.getUserId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        return result;
    }
}
