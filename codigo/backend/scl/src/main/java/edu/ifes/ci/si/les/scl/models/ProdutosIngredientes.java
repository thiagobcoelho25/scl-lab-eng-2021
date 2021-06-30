package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import edu.ifes.ci.si.les.scl.model.enums.TipoIngrediente;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class ProdutosIngredientes implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProdutosIngredientesPK id = new ProdutosIngredientesPK();
	
	private Integer quantidade;

	private TipoIngrediente tipo;
	
	@Builder
	public ProdutosIngredientes (Produto produto, Ingrediente ingrediente, Integer quantidade, TipoIngrediente tipoIngrediente){
		this.id.setProduto(produto);
		this.id.setIngrediente(ingrediente);
		this.quantidade = quantidade;
		this.tipo = tipoIngrediente;
	}

}
