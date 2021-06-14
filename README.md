# java_spring_react
Aplicación web con Java y React

## Script para creación de tablas
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
insert into tbl_restriccion (id,digito,
