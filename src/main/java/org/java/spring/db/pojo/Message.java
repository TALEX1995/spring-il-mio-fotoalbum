package org.java.spring.db.pojo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "La mail è obbligatoria")
	@Column(nullable = false)
	private String email;
	
	@NotBlank(message = "Il messaggio è obbligatorio")
	@Column(columnDefinition = "TEXT", nullable = false)
	private String messageText;
	
	private LocalDate readTime;

	public Message() { };
	
	public Message(String email, String messageText) {
		setEmail(email);
		setMessageText(messageText);
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String message) {
		this.messageText = message;
	}

	public LocalDate getReadTime() {
		return readTime;
	}

	public void setReadTime(LocalDate readTime) {
		this.readTime = readTime;
	}
	
	
}
