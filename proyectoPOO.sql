create database proyectoPOO;
use proyectoPOO;

-- debo tener 3 tablas principales 
-- primera tabla ADMINISTRADOR
create table administrador(
id_administrador int auto_increment primary key not null,
usuario varchar(50) not null,
contrasenia varchar(50) not null

);

insert into administrador(id_administrador,usuario,contrasenia) values (2,"Mishell Diaz","MD123*_*"),
(3,"Jimena Diaz","JD456*_*"),(4,"Fausto Pulig","FP789*_*");
-- segunda tabla CLIENTE
create table cliente(
id_cliente int auto_increment primary key not null,
usuario varchar(50) not null,
contrasenia varchar(50) not null


);
insert into cliente(id_cliente,usuario,contrasenia) values (2,"Rosa Pulig","RS_CL_*-*"),
(3,"Segundo Melba","SM_CL_*-*"),(4,"Carmen Diaz","CD_CL_*-*");

-- tercer tabla PERSONAL
create table personal(
id_personal int auto_increment primary key not null,
usuario varchar(50) not null,
contrasenia varchar(50) not null


);
insert into personal (usuario, contrasenia) values
('jdoe', 'password123'),
('asmith', 'mypassword'),
('bjones', 'securepass');


-- cuarta tabla habitaciones
-- deberia haber una fore
create table habitaciones (
    id_habitacion int primary key auto_increment,
    tipo varchar(50) not null,
    descripcion text,
    precio decimal(10, 2) not null,
    estado varchar(20) default 'disponible',
    numero_camas int not null,
    capacidad int not null,
    tiene_wifi varchar(4) not null,
    tiene_tv varchar(4) not null,
    tiene_aire_acondicionado varchar(4) not null,
    tiene_minibar varchar(4) not null,
    vista varchar(50)
);
insert into habitaciones (tipo, descripcion, precio, estado, numero_camas, capacidad, tiene_wifi, tiene_tv, tiene_aire_acondicionado, tiene_minibar, vista) values
("individual", "habitacion sencilla con una cama individual", 50.00, "disponible", 1, 1, "sí", "no", "no", "no", "ciudad"),
("doble", "habitacion con dos camas individuales", 75.00, "disponible", 2, 2, "sí", "sí", "sí", "sí", "mar"),
("suite", "suite de lujo con cama king size y todas las comodidades", 150.00, "ocupada", 1, 2, "sí", "sí", "sí", "sí", "montaña");



-- la tabla de reservas que dependeria de la tabla habitaciones y cliente
create table reservas (
    id_reserva int primary key auto_increment,
    id_cliente int not null,
    id_habitacion int not null,
    fecha_inicio date not null,
    fecha_fin date not null,
    estado varchar(20) default 'pendiente',
    foreign key (id_cliente) references clientes(id_cliente),
    foreign key (id_habitacion) references habitaciones(id_habitacion)
);
insert into reservas (id_cliente, id_habitacion, fecha_inicio, fecha_fin, estado) values
(1, 2, "2024-08-01", "2024-08-07", "confirmada"),
(2, 1, "2024-08-10", "2024-08-12", "pendiente"),
(3, 3, "2024-08-15", "2024-08-20", "cancelada");

 select * from Administrador;
 
 