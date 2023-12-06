package ifsuldeminas.pas.bcc.KanbanSoftware.exceptions;

public class UnauthorizedUserException extends RuntimeException {

    public UnauthorizedUserException(Integer userId) {
        super("Usuário #" + userId + " não tem permissão");
    }
}
