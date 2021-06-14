package ec.desarollo.no_circula.logic.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ec.desarollo.no_circula.jpa.Restriccion;
import ec.desarollo.no_circula.jpa.Vehiculo;
import ec.desarollo.no_circula.jpa.VehiculoRestriccion;
import ec.desarollo.no_circula.jpa.dto.RequestConsultaVehiculoRestricionDto;
import ec.desarollo.no_circula.jpa.dto.ResponseRestriccionVehiculoDto;
import ec.desarollo.no_circula.logic.VehiculoRestriccionService;
import ec.desarollo.no_circula.repository.VehiculoRepository;
import ec.desarollo.no_circula.repository.VehiculoRestriccionRepository;

@Service
public class VehiculoRestriccionServiceImpl implements VehiculoRestriccionService {

	@Autowired
	VehiculoRepository vehiculoRepository;

	@Autowired
	VehiculoRestriccionRepository vehiculoRestriccionRepository;
	
	private boolean noCircula(LocalDate fecha, Restriccion restriccion) {
		boolean noCircula = false;
		switch (fecha.getDayOfWeek()) {
		case MONDAY:
			noCircula = restriccion.isLunes();
			break;
		case TUESDAY:
			noCircula = restriccion.isMartes();
			break;
		case WEDNESDAY:
			noCircula = restriccion.isMiercoles();
			break;
		case THURSDAY:
			noCircula = restriccion.isJueves();
			break;
		case FRIDAY:
			noCircula = restriccion.isViernes();
			break;
		case SATURDAY:
			noCircula = restriccion.isSabado();
			break;
		case SUNDAY:
			noCircula = restriccion.isDomingo();
			break;
		default:
			noCircula = false;
			break;
		}
		
		return noCircula;
	}

	public ResponseRestriccionVehiculoDto consultaNoCircula(RequestConsultaVehiculoRestricionDto dto) {
		ResponseRestriccionVehiculoDto respuesta = new ResponseRestriccionVehiculoDto();

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fecha = LocalDate.parse(dto.getFecha().trim(), formatter);

			Vehiculo consulta = this.vehiculoRepository.findByPlaca(dto.getPlaca().trim().toUpperCase());

			if (consulta != null) {
				VehiculoRestriccion vehiculoRestriccion = this.vehiculoRestriccionRepository.findByVehiculo(consulta);
				if (vehiculoRestriccion.getRestriccion() != null) {
					respuesta.setHttpStatus(HttpStatus.OK);
					if(noCircula(fecha, vehiculoRestriccion.getRestriccion())) {
						respuesta.setMensaje("El vehículo no puede circular");
						respuesta.setRespuesta(consulta);
						respuesta.setTieneRestriccion(true);
					}else {
						respuesta.setMensaje("El vehículo puede circular");
						respuesta.setRespuesta(consulta);
						respuesta.setTieneRestriccion(false);
					}

				} else {
					respuesta.setHttpStatus(HttpStatus.NO_CONTENT);
					respuesta.setMensaje("El vehículo no tiene datos de restricción");
				}

			} else {
				respuesta.setHttpStatus(HttpStatus.NO_CONTENT);
				respuesta.setMensaje("El vehículo no existe");
			}
		} catch (DateTimeParseException de) {
			// TODO: handle exception
			respuesta.setHttpStatus(HttpStatus.BAD_REQUEST);
			respuesta.setMensaje("El formato de fecha no es válido");
		} catch (Exception e) {
			// TODO: handle exception
			respuesta.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			respuesta.setMensaje("Ocurrio un error al procesar la solicitud");
		}
		
		return respuesta;

	}

}
