package edu.ifes.ci.si.les.scl.controllers.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.ifes.ci.si.les.scl.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scl.exceptions.ConstraintException;
import edu.ifes.ci.si.les.scl.exceptions.DataIntegrityException;
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
		problema.setTimestamp(System.currentTimeMillis());
		problema.setError("Um ou mais campos est??o inv??lidos. Fa??a o preenchimento correto e tente novamente.");
		problema.setCampos(campos);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),"N??o Encontrado",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(ConstraintException.class)
	public ResponseEntity<StandardError> constraintError(ConstraintException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Constraint nao respeitada",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<StandardError> businessRule(BusinessRuleException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(),"Regra de neg??cio nao respeitada",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),"Erro Integridade dos dados",e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	// Ambiguous @ExceptionHandler method mapped for
	/*@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<StandardError> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpServletRequest request) {
		String errorString = "Parametro " + e.getParameterName() + " faltando!";
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),"Erro Integridade dos dados",errorString,request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}*/
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> handleArgumentTypeParams(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		String errorString = "Parametro " + e.getValue() + " diferente do que se esperava " + e.getRequiredType();
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),"Erro Tipo de parametro",errorString,request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

}
