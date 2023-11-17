package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;
import java.util.List;

    @Table(name = "rolegroups")
    @Entity(name = "rolegroups")
    public class Rolegroup {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;

        @ManyToMany
        @JoinTable(
                name = "user_role_group",
                joinColumns = @JoinColumn(name = "role_group_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
        )
        private List<User> users;


        @OneToMany(mappedBy = "rolegroup")
        private List<Role> roles;

        @ManyToMany(mappedBy = "rolegroups")
        private List<Board> boards;
        @ManyToMany(mappedBy = "rolegroups")
        private List<KanbanList> lists;

        @ManyToMany(mappedBy = "rolegroups")
        private List<Card> cards;
    }

