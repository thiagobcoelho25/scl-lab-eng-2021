package edu.ifes.ci.si.les.scl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scl.model.Gerente;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer>{

}
