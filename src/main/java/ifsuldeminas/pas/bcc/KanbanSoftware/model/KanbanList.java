package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Table(name = "kanban_list")
@Entity(name = "kanban_list")
public class KanbanList extends KanbanElement {
    @Id
    private int id;
    @ManyToMany(mappedBy = "card")
    private List<Card> cards;
}
