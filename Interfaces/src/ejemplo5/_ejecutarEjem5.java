/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo5;

/**
 *
 * @author Jeremi Alexander
 */
public class _ejecutarEjem5 {
    public static void main(String[] args) {
        Libro libro1 = new LibroFisico("Ciudad y los perros", "Mario Vargas Llosa", "9788437638973");
        System.out.println("Informacion de libro");
        System.out.println("Titulo: "+libro1.getTitulo());
        System.out.println("Autor: "+libro1.getAutor());
        System.out.println("ISBN: "+libro1.getISBN());
        
        
    }
    
}
