package org.java.spring.controller;

import org.java.spring.db.pojo.Message;
import org.java.spring.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessageRestController {
	
	@Autowired
	private MessageService messageService;
	
	
	@PostMapping
	public ResponseEntity<Message> create(
			@RequestBody Message newMessage){
		
		System.out.println(newMessage.getEmail());
		System.out.println(newMessage.getMessageText());
		
		messageService.save(newMessage);
		
		return new ResponseEntity<>(newMessage, HttpStatus.OK);
		
	}
}
