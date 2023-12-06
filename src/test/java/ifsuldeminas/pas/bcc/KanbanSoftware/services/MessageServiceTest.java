package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.MessageNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Message;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.User;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.MessageRepository;
import ifsuldeminas.pas.bcc.KanbanSoftware.services.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getMessageById_MessageExists_ReturnsMessage() {
        Message existingMessage = createMessage(1, "Hello", createUser("john.doe"), LocalDateTime.now());
        when(messageRepository.findById(1)).thenReturn(Optional.of(existingMessage));

        Message resultMessage = messageService.getMessageById(1);

        verify(messageRepository, times(1)).findById(1);

        assertThat(resultMessage).isEqualTo(existingMessage);
    }

    @Test
    void getMessageById_MessageNotFound_ThrowsException() {
        when(messageRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> messageService.getMessageById(1))
                .isInstanceOf(MessageNotFoundException.class)
                .hasMessage("Message #1 não encontrado");

        verify(messageRepository, times(1)).findById(1);
    }

    @Test
    void createMessage_ReturnsCreatedMessage() {
        Message newMessage = createMessage(1, "Hello", createUser("john.doe"), LocalDateTime.now());

        when(messageRepository.save(any(Message.class))).thenReturn(newMessage);

        Message resultMessage = messageService.createMessage(newMessage);

        verify(messageRepository, times(1)).save(any(Message.class));

        assertThat(resultMessage).isEqualTo(newMessage);
    }

    @Test
    void deleteMessage_MessageExists_DeletesMessage() {
        when(messageRepository.existsById(1)).thenReturn(true);

        messageService.deleteMessage(1);

        verify(messageRepository, times(1)).existsById(1);
        verify(messageRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteMessage_MessageNotFound_ThrowsException() {
        when(messageRepository.existsById(1)).thenReturn(false);

        assertThatThrownBy(() -> messageService.deleteMessage(1))
                .isInstanceOf(MessageNotFoundException.class)
                .hasMessage("Message #1 não encontrado");

        verify(messageRepository, times(1)).existsById(1);
        verify(messageRepository, never()).deleteById(1);
    }

    private Message createMessage(int id, String content, User user, LocalDateTime creationDate) {
        Message message = new Message();
        message.setId(id);
        message.setMessageContent(content);
        message.setUserId(user);
        message.setCreationDate(creationDate);
        return message;
    }

    private User createUser(String nickname) {
        User user = new User();
        user.setNickname(nickname);
        return user;
    }
}
