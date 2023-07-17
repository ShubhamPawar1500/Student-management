package com.example.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Exceptions.UserNotFoundException;

@RestControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> UserNotFound(UserNotFoundException ex){
		Map<String, String> errors = new HashMap<>();
		errors.put("error", ex.getMessage());
		return errors;
	}

}
