/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo1;

/**
 *
 * @author Jeremi Alexander
 * Problema: Define una interfaz Producto que fuerce a cualquier clase 
 * que la implemente a tener métodos para obtener su nombre, precio y 
 * disponibilidad. Luego, crea una clase Articulo que cumpla con dicho contrato.
 */
public interface Productos {
    String getNombre();
    double getPrecio();
    boolean isDisponible();
}
