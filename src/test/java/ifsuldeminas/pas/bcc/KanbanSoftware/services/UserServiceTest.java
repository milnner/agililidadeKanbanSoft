package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.UserNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Sex;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.User;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void updateUserSuccess() throws UserNotFoundException {

        User existingUser = createUser("john.doe", "John", "Doe", "john@example.com", "password123", Sex.MALE);
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(existingUser));

        User updatedUser = createUser("john.jack", "John", "Jack", "john@example.com", "password321", Sex.MALE);

        userService.updateUser("john@example.com", updatedUser);

        verify(userRepository, times(1)).findByEmail("john@example.com");
        verify(userRepository, times(1)).save(any(User.class));

        assertThat(existingUser.equals(updatedUser)).isFalse();
    }

    @Test
    void updateUserUserNotFound() {

        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        User updatedUser = createUser("john.doe", "John Updated", "Doe Updated", "nonexistent@example.com", "newpassword", Sex.MALE);

        UserNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.updateUser("nonexistent@example.com", updatedUser);
        });

        assertThat(exception.getMessage()).isEqualTo("Usuario #nonexistent@example.com não encontrado");

        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
        verify(userRepository, never()).save(any(User.class));
    }

    private User createUser(String nickname, String name, String surname, String email, String password, Sex sex) {
        User user = new User(nickname, name, surname, LocalDateTime.now(), LocalDateTime.now(), email, password, sex);
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }
}
