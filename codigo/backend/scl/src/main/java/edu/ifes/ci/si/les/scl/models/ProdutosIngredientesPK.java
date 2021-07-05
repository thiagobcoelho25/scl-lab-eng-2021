package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(of = {"ingrediente", "produto"})
@Embeddable
public class ProdutosIngredientesPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "ingrediente_id")
	private Ingrediente ingrediente;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

}
