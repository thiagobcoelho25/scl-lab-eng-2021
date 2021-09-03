package edu.ifes.ci.si.les.scl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scl.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Transactional(readOnly = true)
	@Query(value = "select * from CLIENTE where CLIENTE.BAIRRO_ID = ?", nativeQuery = true)
	public List<Cliente> findByBairro(Integer idBairro);
	
}
