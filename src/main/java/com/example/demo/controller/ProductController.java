package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ProductDTO.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.projection.ProductView;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
    
    @GetMapping("/getAll/price/{price}")
    public List<ProductView> findByPriceGreaterThan(@PathVariable int price)
	{
		return productService.findByPriceGreaterThan(price);
	}
	
    @GetMapping("/getAll/name/{name}")
	public List<ProductDTO> findByNameContaining(@PathVariable String name)
	{
		return productService.findByNameContaining(name);
	}

    @GetMapping("/page/{page}/size/{size}")
    public List<Product> getAllProductsWithPage(@PathVariable int page, @PathVariable int size) {
        return productService.getAllProductsWithPage(page, size);
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody Product p) {
        return productService.createProduct(p);
    }
}