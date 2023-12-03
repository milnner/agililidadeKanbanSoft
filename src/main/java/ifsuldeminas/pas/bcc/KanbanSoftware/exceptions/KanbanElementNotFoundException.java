package ifsuldeminas.pas.bcc.KanbanSoftware.exceptions;

public class KanbanElementNotFoundException extends RuntimeException {

    public KanbanElementNotFoundException(Integer id){
        super("KanbanElement #" + id + " não encontrado");
    }
}