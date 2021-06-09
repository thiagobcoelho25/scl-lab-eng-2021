package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;

public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Double precoFinal;

	private Pedido pedido;

	private Ingrediente ingrediente;

}
