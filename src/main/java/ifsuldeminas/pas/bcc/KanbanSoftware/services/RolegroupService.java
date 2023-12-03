package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.RolegroupNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Rolegroup;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.RolegroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolegroupService {
    private final RolegroupRepository rolegroupRepository;

    @Autowired
    public RolegroupService(RolegroupRepository rolegroupRepository) {
        this.rolegroupRepository = rolegroupRepository;
    }

    public List<Rolegroup> getAllRolegroups() {
        return rolegroupRepository.findAll();
    }

    public Rolegroup getRolegroupById(int id) {
        Optional<Rolegroup> opt = rolegroupRepository.findById(id);
        if (!opt.isPresent()) {
            throw new RolegroupNotFoundException(id);
        }
        return opt.get();
    }

    public Rolegroup createRolegroup(Rolegroup rolegroup) {
        return rolegroupRepository.save(rolegroup);
    }

    public Rolegroup updateRolegroup(Integer id, Rolegroup updatedRolegroup) throws RolegroupNotFoundException{
        if (rolegroupRepository.existsById(id)) {
            updatedRolegroup.setId(id);
            return rolegroupRepository.save(updatedRolegroup);
        }
        throw new RolegroupNotFoundException(id);
    }

    public void deleteRolegroup(int id) throws RolegroupNotFoundException {
        if (rolegroupRepository.existsById(id)) {
            rolegroupRepository.deleteById(id);
        }
        throw new RolegroupNotFoundException(id);
    }
}
