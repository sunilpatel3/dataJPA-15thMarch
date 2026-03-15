package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ProductDTO.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.projection.ProductView;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;

@Service	
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	
	public List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}
	
	public List<ProductView> findByPriceGreaterThan(double price)
	{
		return productRepository.findByPriceGreaterThan(price);
	}
	

	public List<ProductDTO> findByNameContaining(String name)
	{
		return productRepository.findByNameContaining(name);
	}
	
	public List<Product> getAllProductsWithPage(int page,int size)
	{
		Pageable pagable=PageRequest.of(page, size, Sort.by("id").ascending());;
		return productRepository.findAll(pagable).getContent();
	}
	
	public String createProduct(Product product)
	{
		String categoryName = product.getCategory().getName();

        Optional<Category> existingCategory =
                categoryRepository.findByName(categoryName);

        Category category;

        if(existingCategory.isPresent()) {
            category = existingCategory.get();
        } else {
            category = new Category();
            category.setName(categoryName);
            category = categoryRepository.save(category);
        }

        product.setCategory(category);

        productRepository.save(product);
		return "Product Saved Successfully";
	}
	
	

}
