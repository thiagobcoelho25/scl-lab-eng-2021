package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.ifes.ci.si.les.scl.model.enums.TipoIngrediente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ProdutosIngredientes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ProdutosIngredientesPK id = new ProdutosIngredientesPK();
	
	@Max(value = 999, message = "Não pode ser maior que 999")
	@Min(value = 1, message = "Não pode ser menor que 1")
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
