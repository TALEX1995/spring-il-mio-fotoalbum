package org.java.spring.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il nome è obbligatorio")
	@Length(min = 3, max = 255, message = "Il nome deve essere più lungo di 3 caratteri e massimo 255")
	@Column(nullable = false)
	private String title;
	
	@NotBlank(message = "La descrizione è obbligatoria")
	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String photoUrl;
	
	@NotNull
	private boolean visible;
	
//	Relations
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Category> categories;
	
	
	public Photo() { };
	
	public Photo(String title, String description, String photoUrl, boolean visible, Category...categories) {
		setTitle(title);
		setDescription(description);
		setPhotoUrl(photoUrl);
		setVisible(visible);
		setCategories(categories);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	@JsonProperty("categories")
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	@JsonIgnore
	private void setCategories(Category...categories) {
		setCategories(Arrays.asList(categories));
	}
	
}
