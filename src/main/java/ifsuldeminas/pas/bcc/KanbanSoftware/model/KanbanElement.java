package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class KanbanElement {
    private String name;
    private String description;
    private LocalDateTime creationDate;
}
