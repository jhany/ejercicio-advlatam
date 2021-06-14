import React, {  useState  } from "react";
import { Formik, Field, Form, ErrorMessage } from 'formik';
import {initialValues, validationSchema} from './variables';
import {ModalVehiculo} from './components/modalVehiculo';
import {ModalRegistro} from './components/modalRegistro';
import VehiculoDataService from "../../api/vehiculoApi";

const BuscarVehiculo = (props) => {


  const [vehiculo, setVehiculo] = useState({respuesta:{placa:'', color:'',modelo:'', chasis:''}, tieneRestriccion:true});
  const [showRegistro, setShowRegistro] = useState(false);
  const [showVehiculo, setShowVehiculo] = useState(false);
  const [isError, setIsError] = useState(false);

  function onSubmit(fields, { setStatus, setSubmitting, resetForm }) {

    setStatus();
    fields.placa = fields.placa.toUpperCase();
    
    VehiculoDataService.searchVehiculo(fields)
      .then(response => {

        resetForm({})
        setSubmitting(false);
        setIsError(false);
        console.log(response);

        switch (response.status) {
            case 200:
                setVehiculo(response.data);
                setShowVehiculo(true);
                break;
            case 204:
                setVehiculo({respuesta:{placa:fields.placa, color:'',modelo:'', chasis:''}, tieneRestriccion:true});
                setShowRegistro(true);
                break;
            default:
                setIsError(true);
                break;
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
    
    <ModalVehiculo show={showVehiculo} handleClose={()=>setShowVehiculo(false)} handleShow={()=>setShowVehiculo(true)} vehiculo={vehiculo}/>
    <ModalRegistro show={showRegistro} handleClose={()=>setShowRegistro(false)} handleShow={()=>setShowRegistro(true)} />


    <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={onSubmit}>
            {({ errors, touched, isSubmitting, setFieldValue }) => {
                
                return (
                    <Form>
                        <h1>Buscar por placa</h1>
                        <div className="form-row">
                            <div className="form-group col">
                                <label>Placa</label>
                                <Field name="placa" type="text"  placeholder="PKN3498" className={'form-control' + (errors.placa && touched.placa ? ' is-invalid' : '')} />
                                <ErrorMessage name="placa" component="div" className="invalid-feedback" />
                            </div>
                        </div>
                     

                        <div className="form-row">
                            <div className="form-group col">
                                <label>Fecha</label>
                                <Field name="fecha" type="date"  className={'form-control' + (errors.fecha && touched.fecha ? ' is-invalid' : '')} />
                                <ErrorMessage name="fecha" component="div" className="invalid-feedback" />
                            </div>
                        </div>

                        {isError &&
                        <span style={{color:'red'}}>No se pudo buscar placa</span> 
                        }

                        <div className="form-group" style={{paddingTop:10}}>
                            <button type="submit" disabled={isSubmitting} className="btn btn-primary">
                                {isSubmitting && <span className="spinner-border spinner-border-sm mr-1"></span>}
                                Buscar
                            </button>
                        </div>
                    
                    </Form>
                );
            }}
        </Formik>

    </div>
    </div>
  )
}

export default BuscarVehiculo;
