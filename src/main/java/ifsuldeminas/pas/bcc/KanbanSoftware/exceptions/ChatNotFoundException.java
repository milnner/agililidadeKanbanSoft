package ifsuldeminas.pas.bcc.KanbanSoftware.exceptions;

public class ChatNotFoundException extends RuntimeException {

    public ChatNotFoundException(Integer id){
        super("Chat #" + id + " não encontrado");
    }
}