package org.java.spring;

import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.RoleService;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Message;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.serv.CategoryService;
import org.java.spring.db.serv.MessageService;
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
	
	@Autowired 
	private RoleService roleService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public void run(String...args) throws Exception {
		
//		Category
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
		
//		User
		Role roleAdmin = new Role("ADMIN");
		Role roleUser = new Role("USER");
		
		roleService.save(roleAdmin);
		roleService.save(roleUser);
		
		String pass = AuthConf.passwordEncoder().encode("password");
		
		User alexAdmin = new User("alexAdmin", pass, roleAdmin);
		User alexUser = new User("alexUser", pass, roleUser);
		
		userService.save(alexAdmin);
		userService.save(alexUser);
		
//		Photo
		Photo p1 = new Photo("La mia foto", "Questa è la mia prima photo", "https://www.nationalgeographic.it/upload/ngi-hero/gettyimages-660629130_1.jpg", true, cat1, cat4);
		Photo p2 = new Photo("La mia seconda foto", "Questa è la mia seconda photo", "https://guidaperphotoshop.com/wp-content/uploads/2011/11/come-unire-foto-photoshop-panoramica.jpg", true, cat2);
		Photo p3 = new Photo("La mia terza foto", "Questa è la mia terza photo", "https://www.keblog.it/wp-content/uploads/2021/12/foto-piu-belle-2021-30.jpg", true, cat2, cat4);
		Photo p4 = new Photo("La mia quarta foto", "Questa è la mia quarta photo", "https://images.agi.it/pictures/agi/agi/2016/03/17/154253836-ebce30d5-3e45-46f8-8dde-df97ac3e95e8.jpg", false, cat1, cat4);
		Photo p5 = new Photo("La mia quinta foto", "Questa è la mia quinta photo", "https://heymondo.it/blog/wp-content/uploads/2023/05/Citta-piu-grande-del-mondo.jpg", false, cat5);
		
		p1.setUser(alexUser);
		p2.setUser(alexUser);
		p3.setUser(alexAdmin);
		p4.setUser(alexAdmin);
		p5.setUser(alexAdmin);
		
		photoService.save(p1);
		photoService.save(p2);
		photoService.save(p3);
		photoService.save(p4);
		photoService.save(p5);
		
		Message m1 = new Message("alex@gmail.com", "Ciao la foto numero 1 è bellissima");
		Message m2 = new Message("aleeeex@gmail.com", "Ciao la foto numero 4 è bellissima");
		
		messageService.save(m1);
		messageService.save(m2);
		
		
	}
}
