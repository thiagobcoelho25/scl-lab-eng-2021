package edu.ifes.ci.si.les.scl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scl.models.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer>{
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM ingrediente ing INNER JOIN estoque estq ON ing.id = estq.ingrediente_id WHERE "
					+ "estq.quantidade > ?1", nativeQuery = true)
	public List<Ingrediente> listOfIngredientesThatHaveEstoqueQuantidadeGreaterThan(Integer quantidade);
	
}
