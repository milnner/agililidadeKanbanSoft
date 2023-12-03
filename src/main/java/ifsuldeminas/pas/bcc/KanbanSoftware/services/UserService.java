package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.UserNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.User;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) throws UserNotFoundException, Exception {
            Optional<User> opt = userRepository.findByEmail(email);
            if (!opt.isPresent()) {
                throw new UserNotFoundException(email);
            }
            return opt.get();
    }

    public User save(User user) throws  org.springframework.dao.DataIntegrityViolationException, Exception {
        user = userRepository.save(user);
        String jsonUser = user.toJson();
        return user;
    }

    public void deleteByEmail(String email) throws UserNotFoundException {

        Optional<User> opt = userRepository.findByEmail(email);
        if (opt.isPresent()) {
            throw new UserNotFoundException(email);
        }
        userRepository.deleteByEmail(email);
        return;

    }

    public User updateUser(String email, User updatedUser) throws UserNotFoundException {
        Optional<User> opt = userRepository.findByEmail(email);
        if (opt.isPresent()) {
            User existingUser = opt.get();
            // Atualize os atributos do usuário com base nos atributos de updatedUser
            existingUser.setName(updatedUser.getName());
            existingUser.setSurname(updatedUser.getSurname());
            return userRepository.save(existingUser);
        } else {
            throw new UserNotFoundException(email);
        }
    }
}
