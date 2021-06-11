package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date data;

	private Usuario usuario;

	private Collection<Cliente> cliente;

	private Pagamento pagamento;

	private Entrega entrega;

}
