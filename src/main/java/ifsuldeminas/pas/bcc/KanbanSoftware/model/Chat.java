package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Table(name = "chats")
@Entity(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "chat_connection_ids")
    private ArrayList<Integer> userChatConnectionIds;
    @ManyToOne
    @JoinColumn(name = "message_ids")
    private Message message;
}
