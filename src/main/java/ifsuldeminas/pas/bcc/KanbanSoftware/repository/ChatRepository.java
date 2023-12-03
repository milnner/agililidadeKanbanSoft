package ifsuldeminas.pas.bcc.KanbanSoftware.repository;

import ifsuldeminas.pas.bcc.KanbanSoftware.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
