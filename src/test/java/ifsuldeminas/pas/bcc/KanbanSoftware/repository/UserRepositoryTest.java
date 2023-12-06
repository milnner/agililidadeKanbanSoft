package ifsuldeminas.pas.bcc.KanbanSoftware.repository;

import ifsuldeminas.pas.bcc.KanbanSoftware.model.Sex;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("find user by email - success")
    void findByEmailSuccess() {

        User user = createUser("JohnDoe", "John", "Doe", "john@example.com", "password123", Sex.MALE);
        Optional<User> foundUser = this.userRepository.findByEmail(user.getEmail());

        assertThat(foundUser.isPresent()).isTrue();

    }

    @Test
    @DisplayName("find user by email - error")
    void findByEmailError() {

        Optional<User> foundUser = this.userRepository.findByEmail("nonexistent@example.com");

        assertThat(foundUser.isEmpty()).isTrue();

    }

    @Test
    @DisplayName("delete user by email - success")
    void deleteByEmailSuccess() {

        User user = createUser("JaneDoe", "Jane", "Doe", "jane@example.com", "password456", Sex.FEMALE);
        this.userRepository.deleteByEmail(user.getEmail());

        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());

        assertThat(foundUser.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("delete user by email - error")
    void deleteByEmailError() {

        userRepository.deleteByEmail("nonexistent@example.com");

        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        assertFalse(foundUser.isPresent());

    }

    private User createUser(String nickname, String name, String surname, String email, String password, Sex sex) {
        User user = new User(nickname, name, surname, LocalDateTime.now(), LocalDateTime.now(), email, password, sex);
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }
}
