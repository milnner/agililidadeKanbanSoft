package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.KanbanElementNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Card;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card getCardById(int id) throws KanbanElementNotFoundException, Exception {
        Optional<Card> opt = cardRepository.findById(id);
        if (!opt.isPresent()) {
            throw new KanbanElementNotFoundException(id);
        }
        return opt.get();
    }

    public Card save(Card card) throws org.springframework.dao.DataIntegrityViolationException, Exception {
        card = cardRepository.save(card);
        return card;
    }

    public void deleteById(int id) throws KanbanElementNotFoundException {
        Optional<Card> opt = cardRepository.findById(id);
        if (!opt.isPresent()) {
            throw new KanbanElementNotFoundException(id);
        }
        cardRepository.deleteById(id);
    }

    public Card updateCard(int id, Card updatedCard) throws KanbanElementNotFoundException {
        Optional<Card> opt = cardRepository.findById(id);
        if (opt.isPresent()) {
            Card existingCard = opt.get();
            existingCard.setName(updatedCard.getName());
            existingCard.setDescription(updatedCard.getDescription());
            return cardRepository.save(existingCard);
        } else {
            throw new KanbanElementNotFoundException(id);
        }
    }
}
