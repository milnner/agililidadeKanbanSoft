package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "board")
@Entity(name = "board")
public class Board extends KanbanElement {
    @Id
    private int id;
    @OneToMany(mappedBy = "list")
    private List<KanbanList> lists;
}
