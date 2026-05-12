CREATE DATABASE IF NOT EXISTS mibase1;
USE mibase1;

DROP TABLE IF EXISTS tbusuario2;

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
