package org.java.spring;

import org.java.spring.db.pojo.Photo;
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
	
	@Override
	public void run(String...args) throws Exception {
		
		photoService.save(new Photo("La mia foto", "Questa è la mia prima photo", "https://www.nationalgeographic.it/upload/ngi-hero/gettyimages-660629130_1.jpg"));
	}
}
