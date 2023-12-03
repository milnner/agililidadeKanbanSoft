package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.UserNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.User;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.UserRepository;

import java.util.Optional;

public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository){}

    public User getUserByEmail(String email) throws UserNotFoundException, Exception {
        try {
            Optional<User> opt = userRepository.findByEmail(email);
            if (!opt.isPresent()) {
                throw new UserNotFoundException(email);
            }
            User user = new User();
            user = opt.get();
            return user;
        } catch (Exception e) {
            throw e;
        }
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
