package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Message;
import org.java.spring.db.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public List<Message> findAll(){
		return messageRepository.findAll();
	}
	
	public Message findById(int id) {
		return messageRepository.findById(id).get();
	}
	
	public void save(Message message) {
		messageRepository.save(message);
	}
	
	public void delete(Message message) {
		messageRepository.delete(message);
	}
}
