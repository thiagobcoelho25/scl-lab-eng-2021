package edu.ifes.ci.si.les.scl.controllers.exception;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.ifes.ci.si.les.scl.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scl.exceptions.ConstraintException;
import edu.ifes.ci.si.les.scl.exceptions.DataIntegrityException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.ifes.ci.si.les.scl.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ValidationStandardError.Campo> campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new ValidationStandardError.Campo(nome, mensagem));
		}
		
		ValidationStandardError problema = new ValidationStandardError();
		problema.setStatus(status.value());
		problema.setTimestamp(OffsetDateTime.now());
		problema.setError("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(OffsetDateTime.now(), HttpStatus.NOT_FOUND.value(),"Não Encontrado",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ConstraintException.class)
	public ResponseEntity<StandardError> constraintError(ConstraintException e, HttpServletRequest request){
		StandardError err = new StandardError(OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value(),"Constraint nao respeitada",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<StandardError> businessRule(BusinessRuleException e, HttpServletRequest request){
		StandardError err = new StandardError(OffsetDateTime.now(), HttpStatus.CONFLICT.value(),"Regra de negócio nao respeitada",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		StandardError err = new StandardError(OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value(),"Erro Integridade dos dados",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}



}
