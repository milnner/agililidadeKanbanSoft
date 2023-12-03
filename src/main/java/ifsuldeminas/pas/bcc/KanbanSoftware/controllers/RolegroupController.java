package ifsuldeminas.pas.bcc.KanbanSoftware.controllers;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.RolegroupNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Rolegroup;
import ifsuldeminas.pas.bcc.KanbanSoftware.services.RolegroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rolegroups")
public class RolegroupController {
    private final RolegroupService rolegroupService;

    @Autowired
    public RolegroupController(RolegroupService rolegroupService) {
        this.rolegroupService = rolegroupService;
    }

    @GetMapping
    public ResponseEntity<List<Rolegroup>> getAllRolegroups() {
        try {
            List<Rolegroup> rolegroups = rolegroupService.getAllRolegroups();
            return ResponseEntity.ok(rolegroups);
        } catch (RolegroupNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rolegroup> getRolegroupById(@PathVariable int id) {
        try {
            Rolegroup rolegroup = rolegroupService.getRolegroupById(id);
            return ResponseEntity.ok(rolegroup);
        } catch (RolegroupNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Rolegroup> createRolegroup(@RequestBody Rolegroup rolegroup) {
        try {
            Rolegroup createdRolegroup = rolegroupService.createRolegroup(rolegroup);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRolegroup);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rolegroup> updateRolegroup(@PathVariable int id, @RequestBody Rolegroup updatedRolegroup) {
        try {
            Rolegroup rolegroup = rolegroupService.updateRolegroup(id, updatedRolegroup);
            return rolegroup != null ? ResponseEntity.ok(rolegroup) : ResponseEntity.notFound().build();
        } catch (RolegroupNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRolegroup(@PathVariable int id) {
        try {
            rolegroupService.deleteRolegroup(id);
            return ResponseEntity.noContent().build();
        } catch (RolegroupNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
