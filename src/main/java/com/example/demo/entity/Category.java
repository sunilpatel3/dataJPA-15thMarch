package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	
	@Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	 
	 private String name;
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
/*
 *  If JSON is like this then only we need to add below Mapping
	{
	  "categoryname": "Electronic",
	  "products": [
	    {
	      "name": "Earphone",
	      "price": 19.99,
	      "description": "Red earphone"
	    },
	    {
	      "name": "Headphone",
	      "price": 49.99,
	      "description": "Bluetooth headphone"
	    }
	  ]
	}

	
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

*/	

}
