/*
 * Modelo de Contacto: Crea una clase simple (POJO - Objeto Java Sencillo y Clásico) 
 * llamada Contacto con dos atributos: nombre (String) 
 * y telefono (long). El objetivo es encapsular estos 
 * dos datos en un solo objeto.
 */
public class ejemplo4 {

    public class Contacto {

        String nombre;
        long telefono;

        public Contacto() {

        }

        public Contacto(String nombre, Long telefono) {
            this.nombre = nombre;
            this.telefono = telefono;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public long getTelefono() {
            return telefono;
        }

        public void setTelefono(long telefono) {
            this.telefono = telefono;
        }
    }
}

/*
 * En otra clase
    public class Main {
    public static void main(String[] args) {
        // Crear un objeto Contacto
        Contacto contacto1 = new Contacto("Jeremi Olivares", 987654321);

        // Mostrar datos
        System.out.println(contacto1);

        // Cambiar el número
        contacto1.setTelefono(912345678);
        System.out.println("Nuevo teléfono: " + contacto1.getTelefono());
        }
    }
 * 
*/
