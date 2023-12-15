package org.java.spring.controller;

import java.util.List;

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

import jakarta.validation.Valid;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/photos")
	public String photoIndex(Model model) {
		
		List<Photo> photos = photoService.findAll();
		
		model.addAttribute("photos", photos);
		
		return "photo-index";
	}
	
	@GetMapping("/photos/{id}")
	public String photoDetail(Model model, @PathVariable int id) {
		
		Photo photo = photoService.findById(id);
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
			
			System.out.println(bindingResult);
			model.addAttribute("photo", photo);
			return "photo-form";
		}
			
		photoService.save(photo);
		
		int id = photo.getId();
		
		return "redirect:/photos/" + id;
	}
	
	
	@GetMapping("/photos/edit/{id}")
	public String editPhoto(Model model, @PathVariable int id) {
		
		Photo photo = photoService.findById(id);
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
			
			System.out.println(bindingResult);
			model.addAttribute("photo", photo);
			return "photo-form";
		}
		
		photoService.save(photo);	
		
		return "redirect:/photos/" + id;
	}
	
	@PostMapping("/photos/delete/{id}")
	public String deletePhoto(Model model, @PathVariable int id) {
		
		Photo photo = photoService.findById(id);
		
		photoService.delete(photo);
		
		return "redirect:/photos";
	}
	
	
}
