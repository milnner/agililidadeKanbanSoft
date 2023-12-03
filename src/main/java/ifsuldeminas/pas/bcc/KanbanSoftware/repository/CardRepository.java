package ifsuldeminas.pas.bcc.KanbanSoftware.repository;

import ifsuldeminas.pas.bcc.KanbanSoftware.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
