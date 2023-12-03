package ifsuldeminas.pas.bcc.KanbanSoftware.controllers;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.KanbanElementNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.KanbanList;
import ifsuldeminas.pas.bcc.KanbanSoftware.services.KanbanListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kanban-list")
public class KanbanListController {
    private final KanbanListService kanbanListService;

    @Autowired
    public KanbanListController(KanbanListService kanbanListService) {
        this.kanbanListService = kanbanListService;
    }

    @GetMapping("/create")
    public String showCreateKanbanListForm(Model model) {
        model.addAttribute("kanbanList", new KanbanList());
        return "createKanbanList";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createKanbanList(@RequestBody KanbanList kanbanList) {
        try {
            kanbanList = kanbanListService.save(kanbanList);
            return ResponseEntity.status(HttpStatus.CREATED).body(kanbanList.toJson());
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> readKanbanList(@PathVariable int id) {
        KanbanList kanbanList;
        try {
            kanbanList = kanbanListService.getKanbanListById(id);
        } catch (KanbanElementNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(kanbanList.toJson());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKanbanList(@PathVariable int id) {
        try {
            kanbanListService.deleteById(id);
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
    public ResponseEntity<String> updateKanbanList(@PathVariable int id, @RequestBody KanbanList updatedKanbanList) {
        try {
            KanbanList updated = kanbanListService.updateKanbanList(id, updatedKanbanList);
            String jsonKanbanList = updated.toJson();
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (KanbanElementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
}
