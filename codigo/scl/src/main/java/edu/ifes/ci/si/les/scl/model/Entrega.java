package edu.ifes.ci.si.les.scl.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import edu.ifes.ci.si.les.scl.model.enums.StatusEntrega;
import lombok.*;

import javax.persistence.*;


public class Entrega implements Serializable{

	private static final long serialVersionUID = 1L;


	private Integer id;

	private LocalDateTime horaSaida;


	private StatusEntrega status;

}
