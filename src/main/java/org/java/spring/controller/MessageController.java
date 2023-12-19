package org.java.spring.controller;

import java.util.List;

import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/messages")
	public String messageIndex(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUsername(authentication.getName());
		
		List<Message> messages = user.getMessages();
		
		model.addAttribute("messages", messages);
		
		return "message-index";
	}
}
