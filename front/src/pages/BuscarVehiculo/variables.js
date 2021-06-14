import * as Yup from 'yup';

export const initialValues = {
    placa: '',
    fecha: ''
};

export const validationSchema = Yup.object().shape({
    placa: Yup.string()
        .required('Placa es requerido')
        .matches('^[a-zA-Z][a-zA-Z0-9]*[0-9]$', 'Formato AAA1234')
        .min(6,'Mínimo 6 dígitos')
        .max(7,'Máximo 7 dígitos'),
    fecha: Yup.date()
        .required('Fecha es requerido')
        .min(new Date(new Date().setDate(new Date().getDate()-1)),'Debe ser mayor ')
});