import React, {useCallback } from "react";
import { Button, Modal } from 'react-bootstrap';
import {useHistory} from 'react-router-dom';

 export const ModalRegistro = ({show, handleClose, handleShow, vehiculo}) => {

  const history = useHistory();
  const handleOnClick = useCallback(() => history.push('/buscar'), [history]);

    return (
      <>      
        <Modal show={show} onHide={handleClose}>
          <Modal.Header >
            <Modal.Title>Vehículo registrado {vehiculo.placa}</Modal.Title>
          </Modal.Header>
          <Modal.Body>Puede consultar el día que tiene permitido circular.</Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Cerrar
            </Button>
            <Button variant="primary" onClick={handleOnClick}>
              Buscar
            </Button>
          </Modal.Footer>
        </Modal>
      </>
    );
  }