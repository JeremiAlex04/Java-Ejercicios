/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo4;

/**
 *
 * @author Jeremi Alexander
 */
public class DireccionSimple implements Direccion{

    private String calle;
    private String ciudad;

    public DireccionSimple(String calle, String ciudad) {
        this.calle = calle;
        this.ciudad = ciudad;
    }
    
    
    
    @Override
    public String getCalle() {
        return calle;
    }

    @Override
    public String getCiudad() {
        return ciudad;
    }
    
}
