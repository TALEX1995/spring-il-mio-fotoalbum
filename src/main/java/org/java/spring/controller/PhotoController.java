package org.java.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.serv.CategoryService;
import org.java.spring.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.validation.Valid;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/photos")
	public String photoIndex(Model model, @RequestParam(required=false) String photoTitle) {
			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		List<Photo> photos = new ArrayList<>();
		
		User user = userService.findByUsername(authentication.getName());
		
		Boolean isAdmin = false;
		
		if (authentication != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ADMIN")) {
                    // L'utente ha il ruolo "ADMIN"
                	photos = photoTitle == null
            				? photoService.findAll()
            				: photoService.findByTitle(photoTitle);
                	
                	isAdmin = true;
                    
                } else if (authority.getAuthority().equals("USER")) {
                	// L'utente ha il ruolo "USER"
                	if(photoTitle == null || photoTitle.isEmpty()) {
                		photos = photoService.findByUserId((long)user.getId());
                	} else {
                		photos = photoService.findByTitleAndUserId(photoTitle, (long)user.getId());
                	}
            				
                }
            }
		};
		
		if(isAdmin) {
			model.addAttribute("role", isAdmin);
		}
		model.addAttribute("photoTitle", photoTitle);
		model.addAttribute("photos", photos);
		
		return "photo-index";
	}
	
	@GetMapping("/photos/{id}")
	public String photoDetail(Model model, @PathVariable int id) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findByUsername(authentication.getName());
		
		Photo photo = photoService.findById(id);
		
		boolean isAdmin = isAdmin();
		
		if(user.getId() != photo.getUser().getId() && !isAdmin) {
			return "redirect:/photos";
		}
		
		model.addAttribute("photo", photo);
		
		return "photo-detail";
	}
	
	@GetMapping("/photos/create")
	public String photoCreate(Model model) {
		
		Photo photo = new Photo();
			
		List<Category> categories = categoryService.findAll();

		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo-form";
	}
	
	@PostMapping("photos/create")
	public String storePhoto(Model model,
			@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Category> categories = categoryService.findAll();
			model.addAttribute("categories", categories);
			System.out.println(bindingResult);
			model.addAttribute("photo", photo);
			return "photo-form";
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findByUsername(authentication.getName());
		photo.setUser(user);
			
		photoService.save(photo);
		
		int id = photo.getId();
		
		return "redirect:/photos/" + id;
	}
	
	
	@GetMapping("/photos/edit/{id}")
	public String editPhoto(Model model, @PathVariable int id) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findByUsername(authentication.getName());
		
		boolean isAdmin = isAdmin();
		
		Photo photo = photoService.findById(id);
		
		if(user.getId() != photo.getUser().getId() && !isAdmin) {
			return "redirect:/photos";
		}
		
		List<Category> categories = categoryService.findAll();

		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo-form";
	}
	
	@PostMapping("/photos/edit/{id}")
	public String updatePhoto(Model model,
			@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult,
			@PathVariable int id) {
		
		if (bindingResult.hasErrors()) {
			List<Category> categories = categoryService.findAll();
			model.addAttribute("categories", categories);
			System.out.println(bindingResult);
			model.addAttribute("photo", photo);
			return "photo-form";
		}
		
		photoService.save(photo);	
		
		return "redirect:/photos/" + id;
	}
	
	@PostMapping("/photos/delete/{id}")
	public String deletePhoto(@PathVariable int id) {
		
		Photo photo = photoService.findById(id);
		
		photoService.delete(photo);
		
		return "redirect:/photos";
	}
	
	public boolean isAdmin() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin = false;
		
		if (authentication != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ADMIN")) {
                    // L'utente ha il ruolo "ADMIN"
                	isAdmin = true;         
                }
            }
		}
		
		return isAdmin;
	}
	
}
