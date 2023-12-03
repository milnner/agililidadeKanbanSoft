package ifsuldeminas.pas.bcc.KanbanSoftware.controllers;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.KanbanElementNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Board;
import ifsuldeminas.pas.bcc.KanbanSoftware.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/create")
    public String showCreateBoardForm(Model model) {
        model.addAttribute("board", new Board());
        return "createBoard";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBoard(@RequestBody Board board) {
        try {
            board = boardService.save(board);
            return ResponseEntity.status(HttpStatus.CREATED).body(board.toJson());
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> readBoard(@PathVariable int id) {
        Board board;
        try {
            board = boardService.getBoardById(id);
        } catch (KanbanElementNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        return ResponseEntity.status(HttpStatus.OK).body(board.toJson());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable int id) {
        try {
            boardService.deleteById(id);
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
    public ResponseEntity<String> updateBoard(@PathVariable int id, @RequestBody Board updatedBoard) {
        try {
            Board updated = boardService.updateBoard(id, updatedBoard);
            String jsonBoard = updated.toJson();
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (KanbanElementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
}
