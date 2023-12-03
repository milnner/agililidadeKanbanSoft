package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.UserChatConnectionNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.UserChatConnection;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.UserChatConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserChatConnectionService {
    private final UserChatConnectionRepository userChatConnectionRepository;

    @Autowired
    public UserChatConnectionService(UserChatConnectionRepository userChatConnectionRepository) {
        this.userChatConnectionRepository = userChatConnectionRepository;
    }

    public List<UserChatConnection> getAllUserChatConnections() {
        return userChatConnectionRepository.findAll();
    }

    public UserChatConnection getUserChatConnectionById(int id) {
        Optional<UserChatConnection> opt = userChatConnectionRepository.findById(id);
        if (!opt.isPresent()) {
            throw new UserChatConnectionNotFoundException(id);
        }
        return opt.get();
    }

    public UserChatConnection createUserChatConnection(UserChatConnection userChatConnection) {
        return userChatConnectionRepository.save(userChatConnection);
    }

    public void deleteUserChatConnection(int id) throws UserChatConnectionNotFoundException {
        if (userChatConnectionRepository.existsById(id)) {
            userChatConnectionRepository.deleteById(id);
        } else {
            throw new UserChatConnectionNotFoundException(id);
        }
    }
}
