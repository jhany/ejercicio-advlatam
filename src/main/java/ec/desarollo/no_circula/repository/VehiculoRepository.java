package ec.desarollo.no_circula.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ec.desarollo.no_circula.jpa.Vehiculo;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {

	// consulta de vehículo por placa	
	public Vehiculo findByPlaca(String placa);
	
}
