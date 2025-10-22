/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo1;

/**
 *
 * @author Jeremi Alexander
 */
public class Articulo implements Productos{
    private String nombre;
    private double precio;
    private boolean disponible;

    public Articulo(String nombre, double precio, boolean disponible) {
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
    }
    
    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public double getPrecio() {
        return this.precio;
    }

    @Override
    public boolean isDisponible() {
        return this.disponible;
    }
    
    public static void main(String[] args) {
        Productos laptop = new Articulo("Laptop Gamer MSI", 1250.99, true);
        System.out.println("Producto: "+laptop.getNombre());
        System.out.println("Precio: "+laptop.getPrecio());
        System.out.println("Disponible: "+(laptop.isDisponible() ? "Si":"No"));
        
    }
    
}
