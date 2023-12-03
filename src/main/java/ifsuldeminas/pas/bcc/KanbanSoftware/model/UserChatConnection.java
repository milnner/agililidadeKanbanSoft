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
}
