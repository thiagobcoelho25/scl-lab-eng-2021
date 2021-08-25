package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Acrescimos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private AcrescimosPK id = new AcrescimosPK();

	@NotNull(message = "A quantidade deve ser definida")
	@Min(value = 1, message = "A quantidade deve ser maior que zero")
	private Integer quantidade;

	@Builder
	public Acrescimos(ItensPedido itensPedido, Ingrediente ingrediente, Integer quantidade){
		this.id.setItensPedido(itensPedido);
		this.id.setIngrediente(ingrediente);
		this.quantidade = quantidade;
	}
	
	@JsonIgnore
	public ItensPedido getItensPedido() {
        return id.getItensPedido();
    }

    public void setItensPedido(ItensPedido itensPedido) {
        id.setItensPedido(itensPedido);
    }
    
    @JsonIgnore
    public Ingrediente getIngrediente() {
        return id.getIngrediente();
    }

    public void setIngrediente(Ingrediente ingrediente) {
        id.setIngrediente(ingrediente);
    }

}
