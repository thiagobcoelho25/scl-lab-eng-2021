package model;

import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@EqualsAndHashCode(of = {"itensPedido", "ingrediente"})
public class AcrescimosPK implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    private ItensPedido itensPedido;

    private Ingrediente ingrediente;
}
