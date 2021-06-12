package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;

import edu.ifes.ci.si.les.scl.model.enums.TipoFormaPagamento;
import lombok.*;

import javax.persistence.*;


public class Pagamento implements Serializable{

	private static final long serialVersionUID = 1L;


	private Integer id;

	private Double valor;

	private Double desconto;


	private TipoFormaPagamento formaDePagamento;

}
