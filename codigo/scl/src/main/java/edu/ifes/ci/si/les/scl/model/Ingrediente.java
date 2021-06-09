package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;
import java.util.Collection;

public class Ingrediente implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Double valor;

	private Collection<Produto> produto;

	private Estoque estoque;

}
