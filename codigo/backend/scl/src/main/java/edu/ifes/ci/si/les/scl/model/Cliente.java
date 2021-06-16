package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Nome Cliente nao pode ser vazio")
	@Size(min = 1, max = 100, message = "Deve ter entre 1 a 100 caracteres")
	private String nome;
	
	@NotBlank(message = "Nome rua nao pode ser vazio")
	@Size(min = 1, max = 40, message = "Deve ter entre 1 e 40 caracteres")
	private String rua;
	
	@NotNull
	@Min(value = 0L, message = "Deve ser um numero positivo")
	private Integer numero;
	
	@NotBlank(message = "Ponto de referencia nao pode ser vazio")
	@Size(min = 1, max = 200, message = "Deve ter entre 1 a 200 caracteres")
	private String pontoReferencia;
	
	@NotNull(message = "Bairro nao pode ser vazio")
	@ManyToOne
	@JoinColumn(name = "bairro_id")
	private Bairro bairro;

}
