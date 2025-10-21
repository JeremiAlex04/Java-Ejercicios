/*
 * Círculo Geométrico: Crea dos variables, radio (double) 
 * y PI (una constante final double con valor 3.1416). 
 * Calcula el área de un círculo y almacena el resultado 
 * en una variable area.
 * 
*/

import java.util.Scanner;

public class ejemplo2 {
    public static void main(String[] args) {
        //Circulo geometrico
        Scanner texto = new Scanner(System.in);
        
        double radio;
        double resultado;
 
        System.out.println("=== Circulo Geometrico ===");
        System.out.println("Ingresa el radio: ");
        radio = texto.nextInt();

        resultado = Math.PI*Math.pow(radio,2);

        System.out.println(String.format("%.2f", resultado));
    }
}
