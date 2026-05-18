package org.example.semana_09.model;

import java.sql.Date;

public class Empleado {
    private int id;
    private String apellidos;
    private String nombres;
    private String genero;
    private String direccion;
    private String celular;
    private Date fechaNacimiento;
    private String observacion;

    public Empleado() {
    }

    public Empleado(int id, String apellidos, String nombres, String genero, String direccion, String celular, Date fechaNacimiento, String observacion) {
        this.id = id;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.genero = genero;
        this.direccion = direccion;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
