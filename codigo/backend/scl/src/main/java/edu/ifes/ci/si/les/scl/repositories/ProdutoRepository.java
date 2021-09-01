package edu.ifes.ci.si.les.scl.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scl.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT p.* FROM PRODUTO p\r\n"
			+ "   INNER JOIN PRODUTOS_INGREDIENTES pi ON pi.PRODUTO_ID = p.ID \r\n"
			+ "   INNER JOIN INGREDIENTE i ON pi.INGREDIENTE_ID = i.ID\r\n"
			+ "   INNER JOIN ESTOQUE e ON i.ID = e.INGREDIENTE_ID\r\n"
			+ "   WHERE pi.TIPO = 'principal' AND e.quantidade > 0\r\n", nativeQuery = true)
	public Collection<Produto> findAllDisponiveis();
	
			  

}
