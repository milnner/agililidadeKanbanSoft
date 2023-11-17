package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class KanbanElement {
    @Id
    private String name;
    private String description;
    private LocalDateTime creationDate;
}
