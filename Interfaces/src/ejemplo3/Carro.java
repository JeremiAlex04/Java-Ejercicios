/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo3;

/**
 *
 * @author Jeremi Alexander
 */
public class Carro implements Vehiculo{
    private String marca;
    private String modelo;
    private int anio;

    public Carro(String marca, String modelo, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    @Override
    public String getMarca() {
        return marca;
    }

    @Override
    public String getModelo() {
        return modelo;
    }
    
    public int getAnio(){
        return this.anio;
    }

    public static void main(String[] args) {
        Vehiculo medio = new Carro("Kia", "Rio", 2019);
        
        System.out.println("Marca: "+medio.getMarca());
        System.out.println("Modelo: "+medio.getModelo());
        
    }
    
    
}
