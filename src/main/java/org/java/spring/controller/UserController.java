package org.java.spring.controller;


import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.RoleService;
import org.java.spring.auth.db.serv.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/register")
	public String createUser(Model model) {
		
		User user = new User();
		
		model.addAttribute("user", user);
		
		return "register-form";
	}
	
	@PostMapping("/register")
	public String storeUser(Model model, @Valid @ModelAttribute User user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("user", user);
			return "register-form";
		}
		
		String pass = AuthConf.passwordEncoder().encode(user.getPassword());
		
		user.setPassword(pass);
		
		Role role = roleService.findById(2);
		
		user.setRoles(role);
		
		userService.save(user);
		
		return "redirect:/";
	}
}
