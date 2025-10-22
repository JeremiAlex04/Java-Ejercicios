/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo2;

/**
 *
 * @author Jeremi Alexander
 */
public class CalculadoraLamba {
    public static void main(String[] args) {
        OperacionMatematica suma = (a,b)-> a + b;
        OperacionMatematica multiplicacion = (a,b) -> a * b;
        
        System.out.println("Resultado de la suma 12 + 12: "+suma.calcular(12, 12));
        System.out.println("Resultado de la multiplicacion 12 * 12: "+multiplicacion.calcular(12, 12));
        
    }
}
