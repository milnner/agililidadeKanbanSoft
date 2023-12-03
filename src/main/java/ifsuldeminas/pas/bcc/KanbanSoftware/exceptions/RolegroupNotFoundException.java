package ifsuldeminas.pas.bcc.KanbanSoftware.exceptions;

public class RolegroupNotFoundException extends RuntimeException {

    public RolegroupNotFoundException(Integer id){
        super("Rolegroup #" + id + " não encontrado");
    }
}

