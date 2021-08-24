package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import model.enums.StatusEntrega;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Entrega implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private LocalDateTime horaSaida;

	private StatusEntrega status;

	// private Pedido pedido;
}
