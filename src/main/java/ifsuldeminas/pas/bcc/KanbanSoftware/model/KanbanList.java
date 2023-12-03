package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "kanban_list")
@Entity(name = "kanban_list")
public class KanbanList extends KanbanElement {
    @Id
    private int id;
    @OneToMany(mappedBy = "list")
    private List<Card> cards;
    @ManyToOne
    private Board board;

    @ManyToOne
    private Rolegroup rolegroup;
}
