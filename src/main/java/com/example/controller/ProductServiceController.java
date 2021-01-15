package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ProductNotFoundException;
import com.example.model.Product;

@RestController
public class ProductServiceController {
	private static Map<String, Product> productRepo=new HashMap<>();
	static List<Product> ob=new ArrayList<>();
	static {
		Product honey=new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);
		
		Product almond=new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
		
		ob.add(honey);
		ob.add(almond);
		
	}
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return ob;
	}
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable("id") String id){
		if(!productRepo.containsKey(id)) {
			throw new ProductNotFoundException();
		}
	    Product p=productRepo.get(id);
	    
	   return new ResponseEntity<>("product is received successfully "+p.getName(),HttpStatus.OK);
	

}
@PostMapping("/postproduct")	
public ResponseEntity<Object> postProduct(@RequestBody Product p1){
	productRepo.put(p1.getId(), p1);
	
	ob.add(p1);
	return new ResponseEntity<>("Product added successfully "+p1.getName(),HttpStatus.OK);
}
@PutMapping("/products/{id}")
public ResponseEntity<Object> pitProduct(@PathVariable("id") String id,@RequestBody Product p1){
	if(!productRepo.containsKey(id))throw new ProductNotFoundException();
	p1.setId(id);
	productRepo.put(id, p1);
	
	return new ResponseEntity<>("Product Updated successfully "+p1.getName(),HttpStatus.OK);
}
@DeleteMapping("/delete/{id}")
public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id){
	if(!productRepo.containsKey(id))throw new ProductNotFoundException();
	
	productRepo.remove(id);
	
	return new ResponseEntity<>("Product Updated successfully ",HttpStatus.OK);
}
}
