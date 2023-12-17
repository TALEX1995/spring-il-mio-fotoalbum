package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.serv.CategoryService;
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
public class CategoryController {

		@Autowired
		private CategoryService categoryService;
		
		@GetMapping("/categories")
		public String findAll(Model model) {
			
			List<Category> categories = categoryService.findAll();
			
			model.addAttribute("categories", categories);
			
			return "category-index";
		}
		
		@GetMapping("/categories/create")
		public String createCategory(Model model) {
			
			Category category = new Category();
			
			model.addAttribute("category", category);
			
			return "category-form";
		}
		
		@PostMapping("/categories/create")
		public String storeCategory(Model model, @Valid @ModelAttribute Category category, BindingResult bindingResult) {
			
			if (bindingResult.hasErrors()) {
				
				model.addAttribute("category", category);
				return "category-form";
			}
			
			categoryService.save(category);
			
			return "redirect:/categories";
		}
		
		@PostMapping("/categories/delete/{id}")
		public String deleteCategory(@PathVariable int id) {
			
			Category category = categoryService.findById(id);
			
			List<Photo> photos = category.getPhotos();
			
			if(photos.size() > 0) {
				for(Photo photo : photos) {
					photo.getCategories().remove(category);
				}
			}
			
			categoryService.delete(category);
			
			return "redirect:/categories";
		}
}
