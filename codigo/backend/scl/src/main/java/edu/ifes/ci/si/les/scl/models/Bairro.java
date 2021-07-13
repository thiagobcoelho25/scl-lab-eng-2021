package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import edu.ifes.ci.si.les.scl.model.enums.EntregavelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Bairro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Nao pode ser vazio")
	@NotNull(message = "Nao pode ser nulo")
	@Size(min = 1, max = 40, message = "Deve ter entre 1 e 40 caracteres")
	private String nome;
	
	@DecimalMin(value = "0.00")
	@Digits(integer = 6, fraction = 2)
	private Double frete;
	
	@Enumerated(EnumType.STRING)
	private EntregavelStatus entregavel;

}
