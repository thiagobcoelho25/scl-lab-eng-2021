package edu.ifes.ci.si.les.scl.controllers.exception;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private OffsetDateTime timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}