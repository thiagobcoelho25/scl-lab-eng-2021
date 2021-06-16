package edu.ifes.ci.si.les.scl.repositories;

import edu.ifes.ci.si.les.scl.model.Acrescimos;
import edu.ifes.ci.si.les.scl.model.AcrescimosPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcrescimosRepository extends JpaRepository<Acrescimos, AcrescimosPK> {
}
