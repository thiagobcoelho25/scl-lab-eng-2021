package model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Funcionario extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login;
	
	private String senha;
	
	private String cargo;
	
	private Double salario;
	
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
