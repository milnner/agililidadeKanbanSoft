package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.KanbanElementNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Board;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board getBoardById(int id) throws KanbanElementNotFoundException, Exception {
        Optional<Board> opt = boardRepository.findById(id);
        if (!opt.isPresent()) {
            throw new KanbanElementNotFoundException(id);
        }
        return opt.get();
    }

    public Board save(Board board) throws org.springframework.dao.DataIntegrityViolationException, Exception {
        board = boardRepository.save(board);
        return board;
    }

    public void deleteById(int id) throws KanbanElementNotFoundException {
        Optional<Board> opt = boardRepository.findById(id);
        if (!opt.isPresent()) {
            throw new KanbanElementNotFoundException(id);
        }
        boardRepository.deleteById(id);
    }

    public Board updateBoard(int id, Board updatedBoard) throws KanbanElementNotFoundException {
        Optional<Board> opt = boardRepository.findById(id);
        if (opt.isPresent()) {
            Board existingBoard = opt.get();
            existingBoard.setName(updatedBoard.getName());
            existingBoard.setDescription(updatedBoard.getDescription());
            existingBoard.setLists(updatedBoard.getLists());
            return boardRepository.save(existingBoard);
        } else {
            throw new KanbanElementNotFoundException(id);
        }
    }
}
