import React from "react";
import { Button, Modal , ListGroup} from 'react-bootstrap';

 export const ModalVehiculo = ({show, handleClose, handleShow, vehiculo}) => {

    return (
      <>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header >
            <Modal.Title>Consulta de vehículo</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            
          <ListGroup>
            <ListGroup.Item><samp style={{fontWeight:700}}>Placa: </samp>{vehiculo.respuesta.placa}</ListGroup.Item>
            <ListGroup.Item><samp style={{fontWeight:700}}>Color: </samp>{vehiculo.respuesta.color}</ListGroup.Item>
            <ListGroup.Item><samp style={{fontWeight:700}}>Modelo: </samp>{vehiculo.respuesta.modelo}</ListGroup.Item>
            <ListGroup.Item><samp style={{fontWeight:700}}>Clasis: </samp>{vehiculo.respuesta.chasis}</ListGroup.Item>
            {vehiculo.tieneRestriccion ?
            <ListGroup.Item variant="warning"><samp style={{fontWeight:700}}>No puede circular</samp></ListGroup.Item>  
            :<ListGroup.Item variant="success"><samp style={{fontWeight:700}}>Circulación libre</samp></ListGroup.Item>
            }
            
          </ListGroup>
          
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Cerrar
            </Button>
          </Modal.Footer>
        </Modal>
      </>
    );
  }