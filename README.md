# java_spring_react
Aplicación web con Java y React

## 1. Ejecutar el war con el comando
java -jar no_circula.war

copiar la carpeta database en la misma ubicación del war, este contiene la base de datos de pruebas o sino se puede realizar una nueva base de datos con los pasos # 3

## 2. Acceso web
Una vez levantada la aplicación se puede acceder al sitio

http://localhost:8080/

y seleccionar del menú la opción insertar vehículo o consultar

## 3. Acceso a base de datos interna
http://localhost:8080/h2-ui

Datos de conexión:

Driver class: org.h2.Driver
jdbc url: jdbc:h2:./database/testdb
Username: sa
Password: <dejar en blanco>

Poblar la data con el siguiente script

## 3.1. Script para creación de tablas
create table tbl_vehiculo (
id int not null primary key,
placa varchar not null,
color varchar,
modelo varchar,
chasis varchar);

create table tbl_restriccion( 
id int not null primary key,
digito int not null,
lunes boolean default false,
martes boolean default false,
miercoles boolean default false,
jueves boolean default false,
viernes boolean default false,
sabado boolean default false,
domingo boolean default false
);

create table tbl_vehiculo_restriccion( 
id int not null primary key,
id_vehiculo int not null,
id_restriccion int not null,
FOREIGN KEY (id_vehiculo) REFERENCES tbl_vehiculo(id),
FOREIGN KEY (id_restriccion) REFERENCES tbl_restriccion(id)
);


Insert into tbl_restriccion values (1,0,true,false,false,false,true,false,false);
Insert into tbl_restriccion values (2,1,true,false,false,false,true,false,false);
Insert into tbl_restriccion values (3,2,true,true,false,false,false,false,false);
Insert into tbl_restriccion values (4,3,true,true,false,false,false,false,false);
Insert into tbl_restriccion values (5,4,false,true,true,false,true,false,false);
Insert into tbl_restriccion values (6,5,false,true,true,false,true,false,false);
Insert into tbl_restriccion values (7,6,false,false,true,true,false,false,false);
Insert into tbl_restriccion values (8,7,false,false,true,true,false,false,false);
Insert into tbl_restriccion values (9,8,false,false,false,true,true,false,false);
Insert into tbl_restriccion values (10,9,false,false,false,true,true,false,false);

