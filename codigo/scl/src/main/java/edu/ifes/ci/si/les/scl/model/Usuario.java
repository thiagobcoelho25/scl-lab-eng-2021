package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String rua;

	private Integer numero;

	private Endereco endereco;

	private Bairro bairro;

}
