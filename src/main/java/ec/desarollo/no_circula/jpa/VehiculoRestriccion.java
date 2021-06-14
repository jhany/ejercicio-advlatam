package ec.desarollo.no_circula.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "tbl_vehiculo_restriccion")
public class VehiculoRestriccion {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	
	@JoinColumn(name = "id_vehiculo")
	@OneToOne
	private Vehiculo vehiculo;
	
	@JoinColumn(name = "id_restriccion")
	@OneToOne
	private Restriccion restriccion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Restriccion getRestriccion() {
		return restriccion;
	}

	public void setRestriccion(Restriccion restriccion) {
		this.restriccion = restriccion;
	}

}
