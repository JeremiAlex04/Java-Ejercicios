CREATE DATABASE IF NOT EXISTS semana9;
USE semana9;

-- DROP TABLE IF EXISTS tbusuario2;

CREATE TABLE tbusuario2 (
    item_ai BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_usuario VARCHAR(20) NOT NULL UNIQUE,
    cod_usuario VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL,
    permisos VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
    bloqueado TINYINT(1) NOT NULL DEFAULT 0,
    en_linea TINYINT(1) NOT NULL DEFAULT 0,
    num_ingresos INT NOT NULL DEFAULT 0,
    intentos_fallidos INT NOT NULL DEFAULT 0,
    nombre_pc VARCHAR(120) NULL,
    ip_acceso VARCHAR(50) NULL,
    lugar VARCHAR(120) NULL,
    ciudad VARCHAR(120) NULL,
    creado_por VARCHAR(50) NULL,
    modificado_por VARCHAR(50) NULL,
    eliminado_por VARCHAR(50) NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_eliminacion TIMESTAMP NULL,
    fecha_ultimo_acceso TIMESTAMP NULL
);

INSERT INTO tbusuario2 (
    id_usuario, cod_usuario, password, nombres, apellidos, email, permisos, estado,
    bloqueado, en_linea, num_ingresos, intentos_fallidos, nombre_pc, ip_acceso, lugar, ciudad,
    creado_por, modificado_por, eliminado_por
) VALUES
('100001', 'admin', 'Admin123*', 'Administrador', 'Principal', 'admin@demo.local', 'ADMINISTRADOR', 'ACTIVO', 0, 0, 0, 0, 'SERVIDOR-01', '127.0.0.1', 'Laboratorio', 'Lima', 'seed', 'seed', NULL),
('100002', 'usuario', 'User123*', 'Usuario', 'Invitado', 'usuario@demo.local', 'USUARIO', 'ACTIVO', 0, 0, 0, 0, 'CLIENTE-01', '127.0.0.1', 'Laboratorio', 'Lima', 'seed', 'seed', NULL);

DROP TABLE IF EXISTS tb_empleado;

CREATE TABLE tb_empleado (
    id INT PRIMARY KEY AUTO_INCREMENT,
    apellidos VARCHAR(100) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    genero VARCHAR(20) NOT NULL,
    direccion VARCHAR(255) NULL,
    celular VARCHAR(20) NULL,
    fecha_nacimiento DATE NULL,
    observacion VARCHAR(255) NULL
);

INSERT INTO tb_empleado (apellidos, nombres, genero, direccion, celular, fecha_nacimiento, observacion) VALUES
('CASTRO TORRES', 'JUAN', 'MASCULINO', 'AV. MARGINAL N° 1234', '987564322', '2011-04-19', 'NINGUNA'),
('FERNANDEZ CAHUANA', 'LILIANA', 'FEMENINO', 'ASOC. VIV. LOS ALAMOS', '967345876', '2002-02-19', 'NINGUNA'),
('DE LA CRUZ FERNANDEZ', 'ROBERTO', 'MASCULINO', 'AV. LAS PALMAS S/N', '943566433', '2010-09-23', 'NINGUNA'),
('MENDEZ TORRES', 'GABRIELA', 'FEMENINO', 'AV. LOS GIRASOLES N° 2450 - PICHANAQUI', '967543232', '2012-10-11', 'NINGUNA'),
('LAPA CAMPOS', 'JUAN', 'MASCULINO', 'JR. LOS INCAS S/N', '999456788', '1980-07-08', 'NINGUNA'),
('HUANCAS PEREZ', 'EDILBERTO', 'MASCULINO', 'AV. FERROCARRIL N° 2345', '964333212', '2000-04-04', 'NINGUNA'),
('BARTOLOME PEREZ', 'HECTOR', 'MASCULINO', 'AV. PERU N° 3434', '967888444', '2022-09-13', 'NINGUNA'),
('QUISPE HUANUCO', 'PRISCILA', 'FEMENINO', 'JR. LOS ALAMOS N° 22323', '979793456', '2022-09-03', 'NINGUNA'),
('LAPA CASTRO', 'BEATRIZ', 'FEMENINO', 'AV. TARAPACA S/N', '987234762', '2022-01-03', 'NINGUNA'),
('PALOMINO CAMPOS', 'JULIAN', 'MASCULINO', 'AV. SANTA ROSA', '987999456', '2022-09-17', 'NINGUNA');
