package edu.ifes.ci.si.les.scl.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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

	@NotBlank(message = "A quantidade deve ser definida")
	@Min(value = 1, message = "A quantidade deve ser maior que zero")
	private Integer quantidade;

	@Builder
	public Acrescimos(ItensPedido itensPedido, Ingrediente ingrediente, Integer quantidade){
		this.id.setItensPedido(itensPedido);
		this.id.setIngrediente(ingrediente);
		this.quantidade = quantidade;
	}

}
