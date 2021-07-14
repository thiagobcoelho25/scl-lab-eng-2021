package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
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
	@NotNull(message = "Valor do Empréstimo deve ser preenchido")
	@Digits(integer = 6, fraction = 2, message = "valor deve estar entre 6.2 digitos")
	private Double valor;
	
	@OneToOne(mappedBy = "ingrediente", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Estoque estoque;
	
	@Builder
	public Ingrediente(Integer id, String nome, Double valor) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}

}
