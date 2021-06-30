package edu.ifes.ci.si.les.scl.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private Double precoFinal;
	
	@OneToMany(mappedBy = "id.produto")
	private List<ProdutosIngredientes> ingredientes = new ArrayList<>();
	
	@Builder
	public Produto(Integer id, String nome, Double precoFinal) {
		this.id = id;
		this.nome = nome;
		this.precoFinal = precoFinal;
	}

}
