package ifsuldeminas.pas.bcc.KanbanSoftware.services;

import ifsuldeminas.pas.bcc.KanbanSoftware.exceptions.ChatNotFoundException;
import ifsuldeminas.pas.bcc.KanbanSoftware.model.Chat;
import ifsuldeminas.pas.bcc.KanbanSoftware.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public Chat getChatById(int id) {
        Optional<Chat> opt = chatRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ChatNotFoundException(id);
        }
        return opt.get();
    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public void deleteChat(int id) throws ChatNotFoundException {
        if (chatRepository.existsById(id)) {
            chatRepository.deleteById(id);
        } else {
            throw new ChatNotFoundException(id);
        }
    }
}
