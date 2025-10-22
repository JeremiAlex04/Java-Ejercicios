/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo4;

/**
 *
 * @author Jeremi Alexander
*   Composición de Objetos: Crea una interfaz 
*   Direccion con métodos como getCalle() y getCiudad().
*   Después, crea una interfaz Cliente que tenga un 
*   método getDireccion(), el cual debe devolver 
*   un objeto de tipo Direccion.
 */

public interface Cliente {
    String getNombre();
    Direccion getDireccion();
}
