package model;

import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.StatusEntrega;

@Data
@Setter
@Getter
@AllArgsConstructor
//@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Entrega implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private LocalDateTime horaSaida;

	private StatusEntrega status;
	
	//@OneToMany
	//@Column(name = "pedido_id")
	//private List<Pedido> dados = new ArrayList<Pedido>();
	
	public Entrega()  {}
}
