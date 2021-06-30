package edu.ifes.ci.si.les.scl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scl.models.ItensPedido;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer> {
}
