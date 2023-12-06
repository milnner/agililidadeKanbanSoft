package ifsuldeminas.pas.bcc.KanbanSoftware.repository;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.UserNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    void deleteByEmail(String email) throws UserNotFoundException;
}
