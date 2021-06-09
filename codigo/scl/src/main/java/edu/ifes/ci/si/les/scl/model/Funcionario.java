package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;

public class Funcionario extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	private String login;

	private String senha;

	private String Cargo;

	private Double salario;

	private String foto;

}
