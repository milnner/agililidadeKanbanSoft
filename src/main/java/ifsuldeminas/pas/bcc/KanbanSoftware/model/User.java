package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "users")
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String nickname;
    private String name;
    private String surname;
    private LocalDateTime entryDate;
    private LocalDateTime bournDate;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToMany(mappedBy = "users")
    private Set<Rolegroup> rolegroupIds;
}
