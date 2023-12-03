package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Table(name = "rolegroups")
    @Entity(name = "rolegroups")
    public class Rolegroup {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;

        @ManyToMany
        private Set<User> users;


        @OneToMany(mappedBy = "rolegroup")
        private List<Role> roles;

        @OneToMany(mappedBy = "rolegroup")
        private List<Board> boards;
        @OneToMany(mappedBy = "rolegroup")
        private List<KanbanList> lists;

        @OneToMany(mappedBy = "rolegroup")
        private List<Card> cards;

    }

