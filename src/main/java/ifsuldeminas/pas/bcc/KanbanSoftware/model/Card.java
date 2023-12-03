package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

@Table(name = "card")
@Entity(name = "card")
public class Card extends KanbanElement {
    @Id
    private int id;
    @ManyToOne
    private KanbanList list;
    @ManyToOne
    private Rolegroup rolegroup;
}
