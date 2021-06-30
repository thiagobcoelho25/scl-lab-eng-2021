package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Gerente extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(length = 20)
    @NotBlank(message = "Login do Gerente deve ser preenchido")
    @Size(min = 2, max = 20, message = "Login do Gerente deve ter entre 2 a 20 caracteres")
	private String login;
	
	@Column(length = 20)
    @NotBlank(message = "Senha do Gerente deve ser preenchida")
    @Size(min = 3, max = 20, message = "Senha do Gerente deve ter entre 3 a 20 caracteres")
	private String senha;
	
	@Builder
    public Gerente(Integer id, String nome, String rua, Integer numero, Bairro bairro, String login, String senha) {
        super(id, nome, rua, numero, bairro);
        this.login = login;
        this.senha = senha;
    }

}
