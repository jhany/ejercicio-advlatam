package ec.desarollo.no_circula.logic.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.desarollo.no_circula.jpa.Restriccion;
import ec.desarollo.no_circula.jpa.Vehiculo;
import ec.desarollo.no_circula.jpa.VehiculoRestriccion;
import ec.desarollo.no_circula.jpa.dto.RequestConsultaVehiculoRestricionDto;
import ec.desarollo.no_circula.jpa.dto.RequestInsertVehiculoDto;
import ec.desarollo.no_circula.logic.VehiculoService;
import ec.desarollo.no_circula.repository.RestriccionRepository;
import ec.desarollo.no_circula.repository.VehiculoRepository;
import ec.desarollo.no_circula.repository.VehiculoRestriccionRepository;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	VehiculoRepository vehiculoRepository;

	@Autowired
	VehiculoRestriccionRepository vehiculoRestriccionRepository;

	@Autowired
	RestriccionRepository restriccionRepository;

	public Vehiculo consulta(String placa) {
		return this.vehiculoRepository.findByPlaca(placa.trim().toUpperCase());
	}

	public Vehiculo insertar(RequestInsertVehiculoDto dto) {

		Vehiculo vehiculo = null;
		try {
			vehiculo = new Vehiculo();
			vehiculo.setPlaca(dto.getPlaca().trim().toUpperCase());
			vehiculo.setChasis(dto.getChasis());
			vehiculo.setColor(dto.getColor());
			vehiculo.setModelo(dto.getModelo());

	        String digitos = dto.getPlaca().replaceAll("[^-?0-9]+", "");
	        
			int ultimoDigito = Integer.parseInt(digitos.substring(digitos.length()-1));

			Restriccion restriccion = this.restriccionRepository.findByDigito(ultimoDigito);

			this.vehiculoRepository.save(vehiculo);

			VehiculoRestriccion vehiculoRestriccion = new VehiculoRestriccion();
			vehiculoRestriccion.setVehiculo(vehiculo);
			vehiculoRestriccion.setRestriccion(restriccion);

			this.vehiculoRestriccionRepository.save(vehiculoRestriccion);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		

		return vehiculo;
	}

}
