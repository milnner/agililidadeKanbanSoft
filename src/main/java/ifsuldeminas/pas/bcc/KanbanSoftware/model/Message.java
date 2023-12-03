//package ifsuldeminas.pas.bcc.KanbanSoftware.model;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDateTime;
//
//@Table(name = "messages")
//@Entity(name = "messages")
//public class Message {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime creationDate;
//    private String messageContent;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User userId;
//}
