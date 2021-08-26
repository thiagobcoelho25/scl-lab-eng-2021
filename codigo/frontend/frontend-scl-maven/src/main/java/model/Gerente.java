package model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})


public class Gerente extends Usuario implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private String login;
	
		private String senha;
		
	    private Integer id;
	    
	    private String nome;
	    
	    private String rua; 
	    
	    private Integer numero;
	    
	    private Bairro bairro;
	  
	    
	
}