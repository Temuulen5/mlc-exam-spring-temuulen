package workshop.pathfinder.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import workshop.pathfinder.domain.DTOs.MessageAddForm;
import workshop.pathfinder.domain.entities.Message;
import workshop.pathfinder.domain.helpers.LoggedUser;
import workshop.pathfinder.repository.MessageRepository;

import java.time.LocalDate;
import java.util.Date;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public MessageService(MessageRepository messageRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public void AddMessage(MessageAddForm addForm) {
        addForm.setDate(new Date());
        addForm.setRecipientId(loggedUser.getId());
        messageRepository.save(modelMapper.map(addForm, Message.class));
    }
}
