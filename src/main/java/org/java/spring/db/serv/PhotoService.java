package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Photo;
import org.java.spring.db.repo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	public List<Photo> findAll(){
		return photoRepository.findAll();
	}
	
	public Photo findById(int id) {
		return photoRepository.findById(id).get();
	}
	
	public void save(Photo photo) {
		photoRepository.save(photo);
	}
	
	public void delete(Photo photo) {
		photoRepository.delete(photo);
	}
	
	public List<Photo> findByTitle(String title){
		return photoRepository.findByTitleContainingIgnoreCase(title);
	}
	
	public List<Photo> findByVisible(){
		return photoRepository.findByVisibleTrue();
	}
	
	public List<Photo> findByUserId(Long userId) {
        return photoRepository.findByUserId(userId);
    }
	
	public List<Photo> findByTitleAndUserId(String photoTitle, Long userId){
		return photoRepository.findByTitleContainingIgnoreCaseAndUserId(photoTitle, userId);
	}
}
