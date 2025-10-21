/*
 * Polimorfismo con Object: Declara una variable de tipo 
 * Object llamada datoVariable. Asígnale sucesivamente 
 * un Integer, luego un String y finalmente un Boolean. 
 * Después de cada asignación, imprime el nombre de su 
 * clase usando datoVariable.getClass().getName().
 * 
 */

public class ejemplo5 {

    public static void main(String[] args) {
        //Declaremos una variable de tipo Object
        Object datoVariable;

        //Asignamos un Integer
        datoVariable = 25;
        System.out.println("Valor: " + datoVariable);
        System.out.println("Tipo: " + datoVariable.getClass().getName());

        //Asignamos un String
        datoVariable = "Hola Mundo";
        System.out.println("Valor: " + datoVariable);
        System.out.println("Tipo: " + datoVariable.getClass().getName());

        //Asignamos un boolean
        datoVariable = true;
        System.out.println("Valor: "+datoVariable);
        System.out.println("Tipo: "+datoVariable.getClass().getName());

    }
}
