package edu.ifes.ci.si.les.scl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scl.models.Bairro;
	

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer>{
	@Query(value = "SELECT * FROM BAIRRO where entregavel = ?1 order by entregavel = 'sim' desc", nativeQuery = true)
	public List<Bairro> findByEntregavel(String entregavel);
}
