package model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.enums.TipoIngrediente;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ProdutosIngredientes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
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
	
	@JsonIgnore
	public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }
    
    public Ingrediente getIngrediente() {
        return id.getIngrediente();
    }

    public void setIngrediente(Ingrediente ingrediente) {
        id.setIngrediente(ingrediente);
    }
}
