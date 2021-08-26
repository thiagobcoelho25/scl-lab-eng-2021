package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.aspectj.weaver.PerTypeWithinTargetTypeMunger;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
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
@Entity
public class Pedido implements Serializable{

	/*
	*  Modifiquei o diagrama de Classes UML
	*  na relação entre Usuário -> Pedido coloquei 1 -> 0..*
	*  na relação entre Pedido -> Cliente coloquei * -> 1
	* */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	//@JsonIgnore
	@NotNull(message = "Usuário deve ser difinido")
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	// gambiarra abaixo. Isso so funciona pq so precisa do id do usuario e Funcionario tem mais campos que gerente
	//@JsonDeserialize(as = Funcionario.class)
	private Usuario usuario;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@NotNull(message = "Valor total do pedido deve ser definido")
	@Min(value = 0)
	private Double valorTotal;
	
	//@JsonIgnore
//	@OneToOne
//	@JoinColumn(name = "pagamento_id", referencedColumnName = "id")
//	private Pagamento pagamento;

	//@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "entrega_id")
//	private Entrega entrega;
//	@JoinColumn(name = "entrega_id")
//	private Entrega entrega;
//	
	@NotNull(message = "Valor total do pedido deve ser definido")
    @Min(value = 0)
    private Double valorTotal;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
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
