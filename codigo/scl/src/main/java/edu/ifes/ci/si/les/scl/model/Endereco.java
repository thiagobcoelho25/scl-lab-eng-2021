package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;

public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String rua;

	private Integer numero;

	private String pontoReferencia;

	private Bairro bairro;

}
