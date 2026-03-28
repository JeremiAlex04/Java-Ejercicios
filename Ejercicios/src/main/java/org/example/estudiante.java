package org.example;

public class estudiante {
    private String codigo;
    private String nombres;
    private int nota1;
    private int nota2;

    public estudiante() {
    }

    public estudiante(int nota1, int nota2, String nombres, String codigo) {
        this.nombres = nombres;
        this.codigo = codigo;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    //Metodo de Condicion del estudiante

    public double promedios(int n1, int n2) {

        double promedio = (nota1 * 0.35) + (nota2 * 0.65);

        return promedio;
    }

    public String condicionestudiante(double prom){
        String respuesta = "";

        if (prom >= 12) {
            respuesta = "APROBADO";
        } else {
            respuesta = "DESAPROBADO";
        }

        return respuesta;
    }

}
