package edu.ifes.ci.si.les.scl.controllers.exception;

import java.time.OffsetDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;


@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(OffsetDateTime.now(), HttpStatus.NOT_FOUND.value(),"Não Encontrado",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
