package model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Estoque implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer quantidade;
	
}
