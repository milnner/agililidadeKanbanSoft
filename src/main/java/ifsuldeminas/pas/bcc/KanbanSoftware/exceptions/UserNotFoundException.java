package ifsuldeminas.pas.bcc.KanbanSoftware.exceptions;

public class UserNotFoundException extends  RuntimeException {

    public UserNotFoundException(Long id){
        super("Usuario #" + id + " não encontrado");
    }
    public UserNotFoundException(String email){
        super("Usuario #" + email + " não encontrado");
    }
}
