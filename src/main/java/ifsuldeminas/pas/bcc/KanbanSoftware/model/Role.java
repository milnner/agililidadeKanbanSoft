package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;


@Table(name = "roles")
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private  Rolegroup rolegroup;
    @Enumerated(EnumType.STRING)
    private KanbanPerms permission;
}
