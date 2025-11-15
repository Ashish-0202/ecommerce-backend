package com.ashish.E_Commerce_Mono_App.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	
	private String product_name;
	
	private String product_desc;
	
	private BigDecimal price;
	
	private int stock_avail;
	
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private category cat;
	
	private Timestamp created_at;
}
