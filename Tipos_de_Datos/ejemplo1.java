/*
Perfil de Usuario: Declara variables para un perfil de usuario 
usando los tipos adecuados: 
- nombre (String)
- edad (int)
- estaActivo (boolean)
- hobbies 
(una lista de Strings ej: ArrayList<String>). 
Inicializa y muestra sus valores.
 */

import java.util.ArrayList;

public class ejemplo1 {

    public static void main(String[] args) {
        String nombre = "Jeremi";
        int edad = 22;
        boolean estaActivo = true; //o false
        ArrayList<String> hobbies = new ArrayList<>();
        
        hobbies.add("Jugar Videojuegos");
        hobbies.add("Jugar Futbol");
        hobbies.add("Programar");

        System.out.println("=== Perfil de Usuario ===");
        System.out.println("Mi nombre es: "+nombre);
        System.out.println("Tengo "+edad+" años");
        System.out.println("¿Esta activo? "+estaActivo);
        System.out.println("Mis pasatiempos son los siguientes: "+hobbies);
    }
}
