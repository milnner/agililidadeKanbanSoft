package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "card")
@Entity(name = "card")
public class Card extends KanbanElement {
    @Id
    private int id;
}
