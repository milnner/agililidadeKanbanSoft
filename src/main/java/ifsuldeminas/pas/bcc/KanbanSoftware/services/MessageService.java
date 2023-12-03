package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.MessageNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Message;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(int id) {
        Optional<Message> opt = messageRepository.findById(id);
        if (!opt.isPresent()) {
            throw new MessageNotFoundException(id);
        }
        return opt.get();
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(int id) throws MessageNotFoundException {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
        } else {
            throw new MessageNotFoundException(id);
        }
    }
}
