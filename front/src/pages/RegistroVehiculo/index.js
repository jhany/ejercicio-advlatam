import React, {   useState  } from "react";
import { Formik, Field, Form, ErrorMessage } from 'formik';
import { Link } from 'react-router-dom';
import {initialValues, validationSchema} from './variables';
import VehiculoDataService from "../../api/vehiculoApi";
import {ModalRegistro} from "./components/modalRegistro";

const RegistroVehiculo = (props) => {


  const [vehiculo, setVehiculo] = useState({});
  const [isError, setIsError] = useState(false);
  const [showRegistro, setShowRegistro] = useState(false);


  function onSubmit(fields, { setStatus, setSubmitting , resetForm}) {

    setStatus();
    fields.placa = fields.placa.toUpperCase()
    
    VehiculoDataService.createVehiculo(fields)
      .then(response => {
        
        setIsError(false);
        setSubmitting(false);
        
        console.log(response);
        
        if(response.status===200){
            resetForm({})
            setVehiculo(response.data.respuesta);
            setShowRegistro(true);
        }else{
            setIsError(true);
        }

      }) 
      .catch(e => {
        setIsError(true);
        setSubmitting(false);
        console.log(e);
      });
    }

  return (
    <div className="list row">        
    <div className="col-md-8">
    <ModalRegistro show={showRegistro} handleClose={()=>setShowRegistro(false)} handleShow={()=>setShowRegistro(true)} vehiculo={vehiculo} />

    <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={onSubmit}>
            {({ errors, touched, isSubmitting, setFieldValue }) => {
                
                return (
                    <Form>
                        <h1>Registro Vehículo</h1>
                        <div className="form-row">
                            <div className="form-group col">
                                <label>Placa</label>
                                <Field name="placa" type="text" placeholder="PKN3498" className={'form-control' + (errors.placa && touched.placa ? ' is-invalid' : '')} />
                                <ErrorMessage name="placa" component="div" className="invalid-feedback" />
                            </div>
                            <div className="form-group col">
                                <label>Color</label>
                                <Field name="color" type="text" placeholder="Blanco" className={'form-control' + (errors.color && touched.color ? ' is-invalid' : '')} />
                                <ErrorMessage name="color" component="div" className="invalid-feedback" />
                            </div>
                            <div className="form-group col">
                                <label>Modelo</label>
                                <Field name="modelo" type="text" placeholder="Aveo" className={'form-control' + (errors.modelo && touched.modelo ? ' is-invalid' : '')} />
                                <ErrorMessage name="modelo" component="div" className="invalid-feedback" />
                            </div>
                            <div className="form-group col">
                                <label>Chasis</label>
                                <Field name="chasis" type="text"  className={'form-control' + (errors.chasis && touched.chasis ? ' is-invalid' : '')} />
                                <ErrorMessage name="chasis" component="div" className="invalid-feedback" />
                            </div>
                        </div>
                    
                        {isError &&
                        <span style={{color:'red'}}>No se pudo guardar vehículo</span> 
                        }
                        
                    
                      
                        <div className="form-group" style={{paddingTop:10}}>
                            <button type="submit" disabled={isSubmitting} className="btn btn-primary">
                                {isSubmitting && <span className="spinner-border spinner-border-sm mr-1"></span>}
                                Guardar
                            </button>
                            <Link to={'.'} className="btn btn-link">Cancelar</Link>
                        </div>
                    </Form>
                );
            }}
        </Formik>

    </div>
    </div>
  )
}

export default RegistroVehiculo;
