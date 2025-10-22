/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo4;

/**
 *
 * @author Jeremi Alexander
 */
public class _ejecutarEjem4 {
    public static void main(String[] args) {
        
        Direccion direccion1 = new DireccionSimple("Torre Blanca", "Lima");
        Cliente cliente1 = new ClienteSimple("Jeremi",direccion1);
        
        System.out.println("=== Informacion del cliente ===");
        System.out.println("Nombre: "+cliente1.getNombre());
        System.out.println("Calle: "+cliente1.getDireccion().getCalle());
        System.out.println("Ciudad: "+cliente1.getDireccion().getCiudad());
        
        
    }
    
}
