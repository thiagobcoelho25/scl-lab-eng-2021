package edu.ifes.ci.si.les.scl.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
@Data
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class ItensPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@NotNull(message = "Valor deve ser não nulo")
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	
	@NotNull(message = "Valor deve ser não nulo")
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@OneToMany(mappedBy = "id.itensPedido", cascade = CascadeType.ALL, orphanRemoval = true)
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
