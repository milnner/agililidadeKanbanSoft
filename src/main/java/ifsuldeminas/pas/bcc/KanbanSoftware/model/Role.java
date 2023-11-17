package ifsuldeminas.pas.bcc.KanbanSoftware.model;
import jakarta.persistence.*;
import java.util.List;


@Table(name = "roles")
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private KanbanPerms permission;
}
