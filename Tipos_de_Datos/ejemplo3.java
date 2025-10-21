/*
 * Lista de Compras Segura: Define un ArrayList que 
 * solo pueda contener objetos de tipo String. 
 * Agrega cinco productos a la lista y luego 
 * intenta agregar un número entero para observar 
 * el error que detecta el compilador.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ejemplo3 {

    public static void main(String[] args) {

        Scanner texto = new Scanner(System.in);

        ArrayList<String> productos = new ArrayList<>();

        System.out.println("=== Agrega productos ===");

        int c = 0;

        String product = "";

        //Recorrido para obtener los datos
        for (int i = 0; i < 5; i++) {
            System.out.println("Producto " + (i + 1));
            product = texto.nextLine();
            if (product.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                productos.add(product);
            } else {
                System.out.println("No es un caracter");
            }

        }

        //Recorrido para mostrar
        System.out.println("Mi lista de productos:");
        for (String producto : productos) {
            System.out.println("- " + producto);
        }

    }
}
