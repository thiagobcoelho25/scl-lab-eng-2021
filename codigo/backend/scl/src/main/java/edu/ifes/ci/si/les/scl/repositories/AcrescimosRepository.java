package edu.ifes.ci.si.les.scl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scl.models.Acrescimos;
import edu.ifes.ci.si.les.scl.models.AcrescimosPK;

@Repository
public interface AcrescimosRepository extends JpaRepository<Acrescimos, AcrescimosPK> {
}
