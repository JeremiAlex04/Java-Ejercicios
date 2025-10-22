/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo4;

/**
 *
 * @author Jeremi Alexander
 */
public class ClienteSimple implements Cliente{
    private String nombre;
    private Direccion direccion;

    public ClienteSimple(String nombre, Direccion direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Direccion getDireccion() {
        return direccion;
    }
    
    
    
    
}
