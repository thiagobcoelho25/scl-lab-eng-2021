package edu.ifes.ci.si.les.scl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scl.models.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer>{
	
	@Modifying
	@Query(value = "UPDATE ESTOQUE e\r\n"
			+ "  SET QUANTIDADE  = e.Quantidade - :?1 \r\n"
			+ "   WHERE e.INGREDIENTE_ID = :?2", nativeQuery = true)
	public void diminuirQtdEstoquePedido(@Param("?1") Integer quantidade, @Param("?2") Integer ingredienteID);
	
}
