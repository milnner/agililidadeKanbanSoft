package ifsuldeminas.pas.bcc.KanbanSoftware.model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String nickname;
    private String name;
    private String surname;
    private LocalDateTime entryDate;
    private LocalDateTime bournDate;
    private String email;
    private String passwordHash;
    private Sex sex;
}
