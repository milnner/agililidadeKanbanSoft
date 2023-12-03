package ifsuldeminas.pas.bcc.KanbanSoftware.controllers;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.UserChatConnectionNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.UserChatConnection;
import ifsuldeminas.pas.bcc.KanbanSoftware.services.UserChatConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userChatConnections")
public class UserChatConnectionController {
    private final UserChatConnectionService userChatConnectionService;

    @Autowired
    public UserChatConnectionController(UserChatConnectionService userChatConnectionService) {
        this.userChatConnectionService = userChatConnectionService;
    }

    @GetMapping
    public ResponseEntity<List<UserChatConnection>> getAllUserChatConnections() {
        try {
            List<UserChatConnection> userChatConnections = userChatConnectionService.getAllUserChatConnections();
            return ResponseEntity.ok(userChatConnections);
        } catch (UserChatConnectionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserChatConnection> getUserChatConnectionById(@PathVariable int id) {
        try {
            UserChatConnection userChatConnection = userChatConnectionService.getUserChatConnectionById(id);
            return ResponseEntity.ok(userChatConnection);
        } catch (UserChatConnectionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<UserChatConnection> createUserChatConnection(@RequestBody UserChatConnection userChatConnection) {
        try {
            UserChatConnection createdUserChatConnection = userChatConnectionService.createUserChatConnection(userChatConnection);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserChatConnection);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserChatConnection(@PathVariable int id) {
        try {
            userChatConnectionService.deleteUserChatConnection(id);
            return ResponseEntity.noContent().build();
        } catch (UserChatConnectionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
