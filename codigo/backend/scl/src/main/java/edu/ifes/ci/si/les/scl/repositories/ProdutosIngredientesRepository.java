package edu.ifes.ci.si.les.scl.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scl.models.ProdutosIngredientes;
import edu.ifes.ci.si.les.scl.models.ProdutosIngredientesPK;

@Repository
public interface ProdutosIngredientesRepository extends JpaRepository<ProdutosIngredientes, ProdutosIngredientesPK>{
	
	@Query(value = "SELECT pi.* FROM PRODUTOS_INGREDIENTES pi\r\n"
					+ "WHERE pi.PRODUTO_ID = ?", nativeQuery = true)
	public Collection<ProdutosIngredientes> findByProduto(Integer produtoID);
}
