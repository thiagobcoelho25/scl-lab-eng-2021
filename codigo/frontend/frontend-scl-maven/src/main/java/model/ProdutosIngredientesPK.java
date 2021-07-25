package model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"ingrediente", "produto"})
public class ProdutosIngredientesPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Ingrediente ingrediente;
	
	private Produto produto;

}
