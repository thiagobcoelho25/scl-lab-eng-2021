package edu.ifes.ci.si.les.scl.repositories;

import edu.ifes.ci.si.les.scl.model.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer> {
}
