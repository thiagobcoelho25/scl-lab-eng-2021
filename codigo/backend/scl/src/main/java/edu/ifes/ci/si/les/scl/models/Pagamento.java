package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import edu.ifes.ci.si.les.scl.model.enums.TipoFormaPagamento;
import lombok.AllArgsConstructor;
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
public class Pagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@DecimalMin(value = "0.00")
	@Digits(integer = 6, fraction = 2)
	private Double valor;

	@DecimalMin(value = "0.00")
	@Digits(integer = 6, fraction = 2)
	private Double desconto;

	@Enumerated(EnumType.STRING)
	private TipoFormaPagamento formaDePagamento;
	
//	@OneToOne
//	@JoinColumn(name = "pedido_id")
//	private Pedido pedido;

}
