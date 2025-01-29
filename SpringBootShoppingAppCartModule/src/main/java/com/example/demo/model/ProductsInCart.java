package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Data
@Entity
public class ProductsInCart {
	
	@Id
	private int productId;
	@NotEmpty
	private String productName;
	@Min(10)
	@Max(10000000)
	private int productPrice;
	@NotEmpty
	private String productCategory;
	private LocalDate productValidity;
	
	private int cartId;

}
