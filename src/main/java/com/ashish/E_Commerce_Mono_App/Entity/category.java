package com.ashish.E_Commerce_Mono_App.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	
	@Column
	private String category_name;
	
	@Column
	private String description;
	
	@OneToMany(mappedBy = "cat",fetch = FetchType.EAGER)
	private List<products> productsByCat;

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public category(int category_id, String category_name, String description) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.description = description;
	}

	public category() {
		super();
	}
	
	
}
