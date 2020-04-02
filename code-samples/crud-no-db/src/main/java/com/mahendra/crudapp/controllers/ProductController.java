package com.mahendra.crudapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahendra.crudapp.models.Product;
import com.mahendra.crudapp.models.ProductList;
import com.mahendra.crudapp.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired private ProductService service;
	
	@GetMapping(produces= {"application/json","application/xml"})
	public ResponseEntity<ProductList> getAll(){
		List<Product> products = service.getAll();
		ProductList plist = new ProductList();
		plist.setProducts(products);
		return  new ResponseEntity<>(plist,HttpStatus.OK);
	}
	
	@GetMapping(value="/find",produces="application/json")
	public ResponseEntity<Product> findBy(@RequestParam("id") Integer id) {
		Product product  = service.findById(id);
		if(product==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json", produces="application/json")
	public Product create(@RequestBody Product product) {
		return service.create(product);
	}
	
	@PutMapping(value="/{id}", consumes="application/json", produces="application/json")
	public Product update(@PathVariable("id")Integer id, @RequestBody Product product) {
		return service.update(product);
	}
	
	@DeleteMapping(value="/{id}",produces="application/json")
	public ResponseEntity<String> delete(@PathVariable("id")Integer id) {
		try{
			 service.deleteById(id);
			return  new ResponseEntity<>("Deleted product "+id,HttpStatus.OK);	
		}catch(RuntimeException ex) {
			return new ResponseEntity<>("Cannot delete non-existing product",HttpStatus.NOT_FOUND);
		}
	}
	
	
}
