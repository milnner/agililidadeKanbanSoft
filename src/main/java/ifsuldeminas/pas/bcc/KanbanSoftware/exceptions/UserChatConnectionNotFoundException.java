package ifsuldeminas.pas.bcc.KanbanSoftware.exceptions;

public class UserChatConnectionNotFoundException extends RuntimeException {

    public UserChatConnectionNotFoundException(Integer id){
        super("UserChatConnection #" + id + " não encontrado");
    }
}

