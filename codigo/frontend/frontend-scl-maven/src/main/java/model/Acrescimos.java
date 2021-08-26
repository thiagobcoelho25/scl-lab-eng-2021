package model;

import java.io.Serializable;

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
public class Acrescimos implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private AcrescimosPK id = new AcrescimosPK();

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
