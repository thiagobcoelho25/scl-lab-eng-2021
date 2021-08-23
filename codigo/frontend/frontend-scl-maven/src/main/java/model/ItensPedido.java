package model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
@Data
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ItensPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	private Produto produto;
	
	private Pedido pedido;
	
	private Collection<Acrescimos> acrescimos = new ArrayList<>();

	@Builder
	public ItensPedido(Integer id, Produto produto, Pedido pedido){
		this.id = id;
		this.produto = produto;
		this.pedido = pedido;
	}
	
	public void setAcrescimos(Collection<Acrescimos> acrescimos) {
		this.acrescimos = acrescimos;
	}

}
