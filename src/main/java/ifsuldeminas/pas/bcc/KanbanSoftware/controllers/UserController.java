package ifsuldeminas.pas.bcc.KanbanSoftware.controllers;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.UserNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.User;
import ifsuldeminas.pas.bcc.KanbanSoftware.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping("/user/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            user = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user.toJson());
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<String> readUser(@PathVariable String email) {
        try {
            userService.getUserByEmail(email);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        try {
            userService.deleteByEmail(email);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUser(email, updatedUser);
            String jsonUser = updated.toJson();
            return ResponseEntity.ok(jsonUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
}
