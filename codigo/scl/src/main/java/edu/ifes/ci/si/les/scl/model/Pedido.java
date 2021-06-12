package edu.ifes.ci.si.les.scl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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

//	@NotBlank(message = "Usuário deve ser difinido")
//	@ManyToOne
//	@JoinColumn(name = "usuario_id", nullable = false)
//	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "pagamento_id", referencedColumnName = "id")
//	private Pagamento pagamento;

//	@ManyToOne
//	@JoinColumn(name = "entrega_id")
//	private Entrega entrega;

//	@Builder
//	public Pedido(Integer id, Date data, Usuario user, Cliente cliente/*, Pagamento pagamento, Entrega entrega*/) {
//		this.id = id;
//		this.data = data;
//		this.usuario  = user;
//		this.cliente = cliente;
////		this.pagamento = pagamento;
////		this.entrega = entrega;
//	}



}
