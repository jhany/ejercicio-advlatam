package ec.desarollo.no_circula.jpa.dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class ResponseRestriccionVehiculoDto {
	
	@JsonIgnore
	private HttpStatus httpStatus;
	
	private String mensaje;
	
	private Object respuesta;
	
	private boolean tieneRestriccion;
	
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Object getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}
	public boolean isTieneRestriccion() {
		return tieneRestriccion;
	}
	public void setTieneRestriccion(boolean tieneRestriccion) {
		this.tieneRestriccion = tieneRestriccion;
	}

}
