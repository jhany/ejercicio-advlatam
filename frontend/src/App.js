import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";


import RegistroVehiculo from "./pages/RegistroVehiculo/index";
import BuscarVehiculo from "./pages/BuscarVehiculo/index";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/buscar"} className="navbar-brand">
            Hoy no circula
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/registro"} className="nav-link">
                Registro Vehículo
              </Link>
            </li>

            <li className="nav-item">
              <Link to={"/buscar"} className="nav-link">
                Buscar Vehículo
              </Link>
            </li>
          
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>  
            <Route exact path={["/", "/buscar"]} component={BuscarVehiculo} />
            <Route path="/registro" component={RegistroVehiculo} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
