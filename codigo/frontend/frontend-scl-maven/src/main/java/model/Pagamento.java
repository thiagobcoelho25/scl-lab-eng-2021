package model;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.TipoFormaPagamento;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Pagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Double valor;

	private Double desconto;

	private TipoFormaPagamento formaDePagamento;
	
//	@OneToOne
//	@JoinColumn(name = "pedido_id")
//	private Pedido pedido;
	
	
	public Pagamento() {}
}
