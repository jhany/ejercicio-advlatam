package ec.desarollo.no_circula.logic;

import org.springframework.stereotype.Service;

import ec.desarollo.no_circula.jpa.Restriccion;
import ec.desarollo.no_circula.jpa.Vehiculo;
import ec.desarollo.no_circula.jpa.dto.RequestConsultaVehiculoRestricionDto;
import ec.desarollo.no_circula.jpa.dto.RequestInsertVehiculoDto;

@Service
public interface VehiculoService {

	
	Vehiculo consulta(String placa);
	
	Vehiculo insertar(RequestInsertVehiculoDto dto);
	
}
