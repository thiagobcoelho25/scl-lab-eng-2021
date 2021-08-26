package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
public class Pedido implements Serializable{

	/*
	*  Modifiquei o diagrama de Classes UML
	*  na relação entre Usuário -> Pedido coloquei 1 -> 0..*
	*  na relação entre Pedido -> Cliente coloquei * -> 1
	* */

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date data;
	
	//@JsonDeserialize(as = Funcionario.class)
	private Usuario usuario;
	
	private Cliente cliente;
	
	//private Pagamento pagamento;

	//private Entrega entrega;
	
	private Double valorTotal;
	
	@JsonIgnore
	private Collection<ItensPedido> itensPedido = new ArrayList<>();

	@Builder
	public Pedido(Integer id, Date data, Usuario user, Cliente cliente/*, Pagamento pagamento, Entrega entrega*/, Double valorTotal) {
		this.id = id;
		this.data = data;
		this.usuario  = user;
		this.cliente = cliente;
		this.valorTotal = valorTotal;
//		this.pagamento = pagamento;
//		this.entrega = entrega;
	}
	
	public void setItensPedido(Collection<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}



}
