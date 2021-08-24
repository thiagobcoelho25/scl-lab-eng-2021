package edu.ifes.ci.si.les.scl.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;


@Data
@EqualsAndHashCode(of = {"itensPedido", "ingrediente"})
@Embeddable
public class AcrescimosPK implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "itenspedido_id")
    private ItensPedido itensPedido;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;
}
