package model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.enums.PagamentoStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})

public class Pagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Cliente cliente;
	
	//private Pedido pedido;
	
	private float desconto;
	
	private String formaDePagamento;
	
	private PagamentoStatus pagamentoStatus;
}
