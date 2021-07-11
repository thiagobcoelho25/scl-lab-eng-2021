package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private Double precoFinal;
	
	@OneToMany(mappedBy = "id.produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ProdutosIngredientes> ingredientes = new ArrayList<>();
	
	@Builder
	public Produto(Integer id, String nome, Double precoFinal) {
		this.id = id;
		this.nome = nome;
		this.precoFinal = precoFinal;
	}
	
	public void setIngredientes(Collection<ProdutosIngredientes> ingredientes) {
	       this.ingredientes = ingredientes;
	     }
	
}
