package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	//@JsonFormat(pattern = "HH-mm")
	private LocalDateTime horaSaida;

	private StatusEntrega status;
	
	private Collection<Pedido> pedidos = new ArrayList<>();
	
	public Entrega()  {}
}
