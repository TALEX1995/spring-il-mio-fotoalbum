package org.java.spring.controller;

import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.pojo.Message;
import org.java.spring.db.serv.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/messages")
public class MessageRestController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping
	public ResponseEntity<String> create(
			@RequestBody Message newMessage, @RequestParam int userId ){
		
		User user = userService.findById(userId);
		newMessage.setUser(user);
		
		messageService.save(newMessage);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
