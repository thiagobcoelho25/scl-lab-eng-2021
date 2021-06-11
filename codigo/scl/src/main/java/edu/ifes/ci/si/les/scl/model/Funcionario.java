package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Funcionario extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(length = 20)
    @NotBlank(message = "Login do Funcionario deve ser preenchido")
    @Size(min = 2, max = 20, message = "Login do Funcionario deve ter entre 2 a 20 caracteres")
	private String login;
	
	@Column(length = 20)
    @NotBlank(message = "Senha do Funcionario deve ser preenchida")
    @Size(min = 3, max = 20, message = "Senha do Funcionario deve ter entre 3 a 20 caracteres")
	private String senha;
	
	@Column(length = 20)
	@NotBlank(message = "Cargo do Funcionario deve ser preenchido")
    @Size(min = 2, max = 20, message = "Cargo do Funcionario deve ter entre 2 a 20 caracteres")
	private String Cargo;
	
	@DecimalMin(value = "0.00")
	@Digits(integer = 6, fraction = 2)
	private Double salario;
	
	private String foto;

}
