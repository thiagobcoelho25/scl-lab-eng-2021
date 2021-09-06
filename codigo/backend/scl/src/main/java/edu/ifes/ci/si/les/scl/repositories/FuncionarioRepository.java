package edu.ifes.ci.si.les.scl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scl.models.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	
	@Query(name = "SELECT f.* FROM FUNCIONARIO f \r\n"
			+ "    INNER JOIN USUARIO u ON f.ID = u.ID \r\n"
			+ "    WHERE u.BAIRRO_ID = ?1 ", nativeQuery = true)
	public List<Funcionario> findByBairroId(Integer bairroID);

}
