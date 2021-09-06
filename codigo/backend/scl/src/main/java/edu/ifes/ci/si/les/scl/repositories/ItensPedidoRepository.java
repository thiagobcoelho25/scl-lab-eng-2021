package edu.ifes.ci.si.les.scl.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scl.models.ItensPedido;
import edu.ifes.ci.si.les.scl.models.Pedido;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer> {
	
	@Transactional(readOnly = true)
	public Collection<ItensPedido> findByPedido(Pedido pedido);
}
