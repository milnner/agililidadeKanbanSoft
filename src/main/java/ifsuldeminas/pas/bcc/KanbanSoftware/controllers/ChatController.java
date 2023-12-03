package ifsuldeminas.pas.bcc.KanbanSoftware.controllers;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.ChatNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Chat;
import ifsuldeminas.pas.bcc.KanbanSoftware.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public ResponseEntity<List<Chat>> getAllChats() {
        try {
            List<Chat> chats = chatService.getAllChats();
            return ResponseEntity.ok(chats);
        } catch (ChatNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable int id) {
        try {
            Chat chat = chatService.getChatById(id);
            return ResponseEntity.ok(chat);
        } catch (ChatNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) {
        try {
            Chat createdChat = chatService.createChat(chat);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdChat);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable int id) {
        try {
            chatService.deleteChat(id);
            return ResponseEntity.noContent().build();
        } catch (ChatNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
