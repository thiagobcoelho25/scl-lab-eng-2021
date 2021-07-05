package edu.ifes.ci.si.les.scl.controllers.exception;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class ValidationStandardError implements Serializable {
	
	private OffsetDateTime timestamp;
	private Integer status;
	private String error;
	private String path;
	
	private List<Campo> campos;
	
	@AllArgsConstructor
	@Getter
	public static class Campo{
		
		private String nome;
		private String mensagem;
		
	}
}
