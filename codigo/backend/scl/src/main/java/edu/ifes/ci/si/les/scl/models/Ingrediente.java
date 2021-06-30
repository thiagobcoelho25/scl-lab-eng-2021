package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Ingrediente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Nome Ingrediente nao pode ser vazio")
	@Size(min = 1, max = 100, message = "Deve ter entre 1 a 100 caracteres")
	private String nome;
	
	@DecimalMin(value = "0.00")
	@Digits(integer = 6, fraction = 2)
	private Double valor;
	
	@OneToOne
	@JoinColumn(name = "estoque_id")
	private Estoque estoque;

}
