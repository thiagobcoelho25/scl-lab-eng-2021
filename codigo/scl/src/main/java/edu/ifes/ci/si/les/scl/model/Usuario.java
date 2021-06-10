package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
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
