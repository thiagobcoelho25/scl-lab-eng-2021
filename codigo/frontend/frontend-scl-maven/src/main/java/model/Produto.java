package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String nome;

	private Double precoFinal;
	
	private Collection<ProdutosIngredientes> ingredientes = new ArrayList<>();
	
	@Builder
	public Produto(Integer id, String nome, Double precoFinal) {
		this.id = id;
		this.nome = nome;
		this.precoFinal = precoFinal;
	}
	
	public void setIngredientes(Collection<ProdutosIngredientes> ingredientes) {
	       this.ingredientes = ingredientes;
	     }
	
}
