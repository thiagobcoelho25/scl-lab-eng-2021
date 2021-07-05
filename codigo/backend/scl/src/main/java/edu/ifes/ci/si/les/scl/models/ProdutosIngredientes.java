package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.ifes.ci.si.les.scl.model.enums.TipoIngrediente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class ProdutosIngredientes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ProdutosIngredientesPK id = new ProdutosIngredientesPK();
	
	private Integer quantidade;
	
	@Enumerated(EnumType.STRING)
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
