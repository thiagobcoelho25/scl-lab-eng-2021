package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
	private String cargo;
	
	@DecimalMin(value = "0.00")
	@Digits(integer = 6, fraction = 2)
	private Double salario;
	
	@Size(max = 2000000)
	private String foto;
	
	@Builder
    public Funcionario(Integer id, String nome, String rua, Integer numero, Bairro bairro, String login, String senha, String cargo, Double salario) {
        super(id, nome, rua, numero, bairro);
        this.login = login;
        this.senha = senha;
        this.salario = salario;
        this.cargo = cargo;
    }

}