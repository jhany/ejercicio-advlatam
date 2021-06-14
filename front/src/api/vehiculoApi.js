import http from "../http-common";

class VehiculoDataService {

  createVehiculo(data) {
    return http.post("/vehiculo/insertar", data);
  }

  searchVehiculo(data) {
    return http.post("/vehiculo/consultar", data);
  }

}

export default new VehiculoDataService();