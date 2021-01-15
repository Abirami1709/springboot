package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {
	
	@ExceptionHandler(value=ProductNotFoundException.class)
	public ResponseEntity<Object> exception(ProductNotFoundException ex){
		return new  ResponseEntity<>("Product Not found",HttpStatus.NOT_FOUND);
	}

}
