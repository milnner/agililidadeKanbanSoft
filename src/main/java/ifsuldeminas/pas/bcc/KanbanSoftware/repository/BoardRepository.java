package ifsuldeminas.pas.bcc.KanbanSoftware.repository;

import ifsuldeminas.pas.bcc.KanbanSoftware.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
