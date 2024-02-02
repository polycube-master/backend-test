package com.sp.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	// 404
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public MyErrorMessage handle404(NoHandlerFoundException e) {
		return new MyErrorMessage(HttpStatus.NOT_FOUND.toString(), e.getMessage());
	}
	
	// 400
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public MyErrorMessage handle400(MethodArgumentNotValidException e) {
		return new MyErrorMessage(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
	}
	
	// 기타
	@ExceptionHandler(Exception.class)
	public MyErrorMessage handle(Exception e) {
		MyErrorMessage err = new MyErrorMessage();
		
		err.setReason(e.getMessage());
		
		return err;
	}

}
