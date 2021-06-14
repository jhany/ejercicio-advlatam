package ec.desarollo.no_circula.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ec.desarollo.no_circula.jpa.Vehiculo;
import ec.desarollo.no_circula.jpa.VehiculoRestriccion;

@Repository
public interface VehiculoRestriccionRepository extends CrudRepository<VehiculoRestriccion, Long> {

	VehiculoRestriccion findByVehiculo(Vehiculo vehiculo);
}
