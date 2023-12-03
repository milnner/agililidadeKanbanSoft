package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.KanbanElementNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.KanbanList;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.KanbanListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KanbanListService {
    private final KanbanListRepository kanbanListRepository;

    @Autowired
    public KanbanListService(KanbanListRepository kanbanListRepository) {
        this.kanbanListRepository = kanbanListRepository;
    }

    public KanbanList getKanbanListById(int id) throws KanbanElementNotFoundException, Exception {
        Optional<KanbanList> opt = kanbanListRepository.findById(id);
        if (!opt.isPresent()) {
            throw new KanbanElementNotFoundException(id);
        }
        return opt.get();
    }

    public KanbanList save(KanbanList kanbanList) throws org.springframework.dao.DataIntegrityViolationException, Exception {
        kanbanList = kanbanListRepository.save(kanbanList);
        return kanbanList;
    }

    public void deleteById(int id) throws KanbanElementNotFoundException {
        Optional<KanbanList> opt = kanbanListRepository.findById(id);
        if (!opt.isPresent()) {
            throw new KanbanElementNotFoundException(id);
        }
        kanbanListRepository.deleteById(id);
    }

    public KanbanList updateKanbanList(int id, KanbanList updatedKanbanList) throws KanbanElementNotFoundException {
        Optional<KanbanList> opt = kanbanListRepository.findById(id);
        if (opt.isPresent()) {
            KanbanList existingKanbanList = opt.get();
            existingKanbanList.setName(updatedKanbanList.getName());
            existingKanbanList.setDescription(updatedKanbanList.getDescription());
            existingKanbanList.setCards(updatedKanbanList.getCards());
            return kanbanListRepository.save(existingKanbanList);
        } else {
            throw new KanbanElementNotFoundException(id);
        }
    }
}
