package edu.ifes.ci.si.les.scl.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scl.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT c.NOME as cliente, COUNT(*) as QtdPedidos, u.NOME as funcionario, SUM(p.VALOR_TOTAL) as TotalGasto FROM PEDIDO p\r\n"
			+ "    INNER JOIN CLIENTE c ON p.CLIENTE_ID = c.ID\r\n"
			+ "    INNER JOIN USUARIO u ON p.USUARIO_ID = u.ID\r\n"
			+ "    WHERE c.ID = 1 = :?1 \r\n"
			+ "    GROUP BY c.Nome, u.Nome", nativeQuery = true)
	public Collection<?> findPedidoByClienteID(@Param("?1") Integer clienteID);
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT c.NOME as cliente, COUNT(*) as QtdPedidos, u.NOME as funcionario, SUM(p.VALOR_TOTAL) as TotalGasto FROM PEDIDO p\r\n"
			+ "    INNER JOIN CLIENTE c ON p.CLIENTE_ID = c.ID\r\n"
			+ "    INNER JOIN USUARIO u ON p.USUARIO_ID = u.ID\r\n"
			+ "    WHERE c.BAIRRO_ID = :?1 \r\n"
			+ "    GROUP BY c.Nome, u.Nome", nativeQuery = true)
	public Collection<?> findPedidoByBairroID(@Param("?1") Integer bairroID);
}
