import React, { useCallback } from "react";
import { Button, Modal } from 'react-bootstrap';
import {useHistory} from 'react-router-dom';

 export const ModalRegistro = ({show, handleClose, handleShow}) => {

  const history = useHistory();
  const handleOnClick = useCallback(() => history.push('/registro'), [history]);

    return (
      <>      
        <Modal show={show} onHide={handleClose}>
          <Modal.Header >
            <Modal.Title>Vehículo no registrado</Modal.Title>
          </Modal.Header>
          <Modal.Body>Para poder consultar debe registrar el vehículo.</Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Cerrar
            </Button>
            <Button variant="primary" onClick={handleOnClick}>
              Registrar
            </Button>
          </Modal.Footer>
        </Modal>
      </>
    );
  }