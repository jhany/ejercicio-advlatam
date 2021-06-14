package ec.desarollo.no_circula.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ec.desarollo.no_circula.jpa.Restriccion;

@Repository
public interface RestriccionRepository extends CrudRepository<Restriccion, Long> {

	// consulta de último digito	
	Restriccion findByDigito(int digito);
	
}