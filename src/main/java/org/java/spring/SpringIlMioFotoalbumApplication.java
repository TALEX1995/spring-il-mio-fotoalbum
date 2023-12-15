package org.java.spring;

import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.serv.CategoryService;
import org.java.spring.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public void run(String...args) throws Exception {
		
		Category cat1 = new Category("Montagna");
		Category cat2 = new Category("Mare");
		Category cat3 = new Category("Bianco e nero");
		Category cat4 = new Category("Animali");
		Category cat5 = new Category("Urbano");
		
		categoryService.save(cat1);
		categoryService.save(cat2);
		categoryService.save(cat3);
		categoryService.save(cat4);
		categoryService.save(cat5);
		
		photoService.save(new Photo("La mia foto", "Questa è la mia prima photo", "https://www.nationalgeographic.it/upload/ngi-hero/gettyimages-660629130_1.jpg", true, cat1, cat4));
		photoService.save(new Photo("La mia seconda foto", "Questa è la mia seconda photo", "https://guidaperphotoshop.com/wp-content/uploads/2011/11/come-unire-foto-photoshop-panoramica.jpg", true, cat2));
		photoService.save(new Photo("La mia terza foto", "Questa è la mia terza photo", "https://www.keblog.it/wp-content/uploads/2021/12/foto-piu-belle-2021-30.jpg", true, cat2, cat4));
		photoService.save(new Photo("La mia quarta foto", "Questa è la mia quarta photo", "https://images.agi.it/pictures/agi/agi/2016/03/17/154253836-ebce30d5-3e45-46f8-8dde-df97ac3e95e8.jpg", false, cat1, cat4));
		photoService.save(new Photo("La mia quinta foto", "Questa è la mia quinta photo", "https://heymondo.it/blog/wp-content/uploads/2023/05/Citta-piu-grande-del-mondo.jpg", false, cat5));
	}
}
