package ifsuldeminas.pas.bcc.KanbanSoftware.exceptions;

public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(Integer id){
        super("Message #" + id + " não encontrado");
    }
}