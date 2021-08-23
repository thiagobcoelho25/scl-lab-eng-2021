package model;

import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;


@Data
@EqualsAndHashCode(of = {"itensPedido", "ingrediente"})
public class AcrescimosPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private ItensPedido itensPedido;

    private Ingrediente ingrediente;
}
