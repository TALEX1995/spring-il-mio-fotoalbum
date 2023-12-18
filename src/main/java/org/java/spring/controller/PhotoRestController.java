package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Photo;
import org.java.spring.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoRestController {

	@Autowired
	private PhotoService photoService;
	
	@GetMapping
	public ResponseEntity<List<Photo>> getIndex (
			@RequestParam(value= "nameParam", required = false) String nameParam){
		
		List<Photo> photos = photoService.findByVisible();
		
		return new ResponseEntity<>(photos, HttpStatus.OK);
	}
	
}
