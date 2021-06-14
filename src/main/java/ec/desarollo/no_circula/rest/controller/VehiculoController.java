package ec.desarollo.no_circula.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.desarollo.no_circula.jpa.Restriccion;
import ec.desarollo.no_circula.jpa.Vehiculo;
import ec.desarollo.no_circula.jpa.dto.RequestConsultaVehiculoRestricionDto;
import ec.desarollo.no_circula.jpa.dto.RequestInsertVehiculoDto;
import ec.desarollo.no_circula.jpa.dto.ResponseDto;
import ec.desarollo.no_circula.jpa.dto.ResponseRestriccionVehiculoDto;
import ec.desarollo.no_circula.logic.VehiculoRestriccionService;
import ec.desarollo.no_circula.logic.VehiculoService;

@RestController
@RequestMapping({"/vehiculo"})
public class VehiculoController {
	
	@Autowired
	VehiculoService vehiculoService;
	
	@Autowired
	VehiculoRestriccionService vehiculoRestriccionService;
	
	@InitBinder
	public void populateRequest(WebDataBinder binder) { 
		binder.setAllowedFields(new String[0]);
	}
	
	@GetMapping("/saludos")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "saludos";
	}

	@CrossOrigin
	@PostMapping("/consultar")
	public ResponseEntity<ResponseRestriccionVehiculoDto> consultar(@RequestBody RequestConsultaVehiculoRestricionDto dataVehiculo, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
		ResponseRestriccionVehiculoDto respuesta = new ResponseRestriccionVehiculoDto();
		
		respuesta = this.vehiculoRestriccionService.consultaNoCircula(dataVehiculo);
		
		return new ResponseEntity<ResponseRestriccionVehiculoDto>(respuesta,respuesta.getHttpStatus());
	}
	
	@CrossOrigin
	@PostMapping("/insertar")
	public ResponseEntity<ResponseDto> insertar(@RequestBody RequestInsertVehiculoDto dataVehiculo, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
		ResponseDto respuesta = new ResponseDto();
		Vehiculo insertado = null;
		Vehiculo consulta = this.vehiculoService.consulta(dataVehiculo.getPlaca());
		if(consulta == null) {
			insertado = this.vehiculoService.insertar(dataVehiculo);
			
			if(insertado != null) {
				respuesta.setHttpStatus(HttpStatus.OK);
				respuesta.setMensaje("Registro almacenado correctamente");
			} else {
				respuesta.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				respuesta.setMensaje("Ocurrió un error, por favor vuelva a intentarlo");
			}
		}else {
			respuesta.setHttpStatus(HttpStatus.BAD_REQUEST);
			respuesta.setMensaje("El vehículo ya existe");
		}
		
		
		respuesta.setRespuesta(insertado);
		
		return new ResponseEntity<ResponseDto>(respuesta,respuesta.getHttpStatus());
	} 
}
