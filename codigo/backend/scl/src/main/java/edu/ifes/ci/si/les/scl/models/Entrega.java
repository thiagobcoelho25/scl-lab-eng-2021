package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import com.sun.istack.NotNull;

import edu.ifes.ci.si.les.scl.model.enums.StatusEntrega;
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
public class Entrega implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	//@JsonFormat(pattern = "HH-mm")
	private LocalDateTime horaSaida;

	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	@OneToMany()
	@JoinColumn(name = "entrega_id")
	private Collection<Pedido> pedidos = new ArrayList<>();
	

}
