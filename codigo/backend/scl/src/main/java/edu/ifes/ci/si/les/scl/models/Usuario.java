package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
		@JsonSubTypes({ 
		  @Type(value = Gerente.class, name = "gerente"), 
		  @Type(value = Funcionario.class, name = "funcionario") 
		})
public abstract class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Nao pode ser vazio")
	@Size(min = 1, max = 100, message = "Deve ter entre 1 a 100 caracteres")
	private String nome;
	
	@NotBlank(message = "Nome rua nao pode ser vazio")
	@Size(min = 1, max = 40, message = "Deve ter entre 1 e 40 caracteres")
	private String rua;

	@NotNull
	@Min(value = 0L, message = "Deve ser um numero positivo")
	private Integer numero;
	
	@NotNull(message = "Bairro nao pode ser vazio")
	@ManyToOne
	@JoinColumn(name = "bairro_id")
	private Bairro bairro;

}