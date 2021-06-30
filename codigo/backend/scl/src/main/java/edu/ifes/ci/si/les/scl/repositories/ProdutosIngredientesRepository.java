package edu.ifes.ci.si.les.scl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scl.models.ProdutosIngredientes;
import edu.ifes.ci.si.les.scl.models.ProdutosIngredientesPK;

@Repository
public interface ProdutosIngredientesRepository extends JpaRepository<ProdutosIngredientes, ProdutosIngredientesPK>{

}
