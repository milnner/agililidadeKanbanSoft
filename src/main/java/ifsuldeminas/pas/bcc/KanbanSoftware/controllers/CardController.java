package ifsuldeminas.pas.bcc.KanbanSoftware.controllers;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.KanbanElementNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Card;
import ifsuldeminas.pas.bcc.KanbanSoftware.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/create")
    public String showCreateCardForm(Model model) {
        model.addAttribute("card", new Card());
        return "createCard";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCard(@RequestBody Card card) {
        try {
            card = cardService.save(card);
            return ResponseEntity.status(HttpStatus.CREATED).body(card.toJson());
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> readCard(@PathVariable int id) {
        Card card;
        try {
            card = cardService.getCardById(id);
        } catch (KanbanElementNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(card.toJson());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable int id) {
        try {
            cardService.deleteById(id);
        } catch (KanbanElementNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCard(@PathVariable int id, @RequestBody Card updatedCard) {
        try {
            Card updated = cardService.updateCard(id, updatedCard);
            String jsonCard = updated.toJson();
            return ResponseEntity.status(HttpStatus.OK).body(jsonCard);
        } catch (KanbanElementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
}
