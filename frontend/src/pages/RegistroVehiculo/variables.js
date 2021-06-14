import * as Yup from 'yup';

export const initialValues = {
    placa: '',
    color: '',
    modelo: '',
    chasis:''
};

export const validationSchema = Yup.object().shape({
    placa: Yup.string()
        .required('Placa es requerido')
        .matches('^[a-zA-Z][a-zA-Z0-9]*[0-9]$', 'Formato AAA123 o AAA1234')
        .min(6,'Mínimo 6 dígitos')
        .max(7,'Máximo 7 dígitos'),
    color:Yup.string()
        .max(20,'Máximo 20 dígitos'),
    modelo:Yup.string()
        .max(30, 'Máximo 30 dígitos'),
    chasis:Yup.string()
        .max(30, 'Máximo 30 dígitos'),
});