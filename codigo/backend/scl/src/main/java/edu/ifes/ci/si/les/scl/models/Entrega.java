package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private LocalDateTime horaSaida;

	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	//@OneToMany
	//@Column(name = "pedido_id")
	//private List<Pedido> dados = new ArrayList<Pedido>();
	

}
