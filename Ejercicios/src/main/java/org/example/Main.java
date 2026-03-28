package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        System.out.println("EVALUANDO NOTAS");
        System.out.println("=== Alumno 1 === ");
        estudiante est1 = new estudiante(12,18,"Jeremi","U001");
        System.out.println("Codigo: "+est1.getCodigo());
        System.out.println("Nombres: "+est1.getNombres());
        System.out.println("Nota 1: "+est1.getNota1());
        System.out.println("Nota 2: "+est1.getNota2());
        System.out.println("Condicion: "+ est1.condicionestudiante(est1.promedios(est1.getNota1(), est1.getNota2())));

        System.out.println("=== Alumno 2 === ");
        estudiante est2 = new estudiante(9,12,"Jeremi","U001");
        System.out.println("Codigo: "+est2.getCodigo());
        System.out.println("Nombres: "+est2.getNombres());
        System.out.println("Nota 1: "+est2.getNota1());
        System.out.println("Nota 2: "+est2.getNota2());
        System.out.println("Condicion: "+est2.condicionestudiante(est2.promedios(est2.getNota1(),est2.getNota2())));


    }
}
