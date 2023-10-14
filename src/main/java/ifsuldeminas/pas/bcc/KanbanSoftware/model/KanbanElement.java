package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import java.time.LocalDateTime;

public abstract class KanbanElement {
    private int id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
}
