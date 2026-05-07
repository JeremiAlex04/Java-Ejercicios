import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/*
 * EJERCICIO:
 * ¡Los JJOO de París 2024 han comenzado!
 * Crea un programa que simule la celebración de los juegos.
 *
 * El programa debe permitir al usuario registrar eventos y participantes,
 * realizar la simulación de los eventos asignando posiciones de manera aleatoria
 * y generar un informe final. Todo ello por terminal.
 *
 * Requisitos:
 * 1. Registrar eventos deportivos.
 * 2. Registrar participantes por nombre y país.
 * 3. Simular eventos de manera aleatoria en base a los participantes (mínimo 3).
 * 4. Asignar medallas (oro, plata y bronce).
 * 5. Mostrar los ganadores por cada evento.
 * 6. Mostrar el ranking de países según el número de medallas.
 *
 * Acciones:
 * 1. Registro de eventos.
 * 2. Registro de participantes.
 * 3. Simulación de eventos.
 * 4. Creación de informes.
 * 5. Salir del programa.
 */

class Participante {
    String nombre;
    String pais;

    public Participante(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return nombre + " - " + pais;
    }
}

class Evento {
    String nombreEvento;
    ArrayList<Participante> participantes;
    boolean simulado;

    Participante oro;
    Participante plata;
    Participante bronce;

    public Evento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
        this.participantes = new ArrayList<>();
        this.simulado = false;
    }

    public void agregarParticipante(Participante p) {
        participantes.add(p);
    }

    public void simularEvento() {

        if (participantes.size() < 3) {
            System.out.println("El evento necesita mínimo 3 participantes.");
            return;
        }

        Collections.shuffle(participantes);

        oro = participantes.get(0);
        plata = participantes.get(1);
        bronce = participantes.get(2);

        simulado = true;

        System.out.println("\n===== RESULTADOS DEL EVENTO =====");
        System.out.println("Evento: " + nombreEvento);
        System.out.println("🥇 Oro: " + oro);
        System.out.println("🥈 Plata: " + plata);
        System.out.println("🥉 Bronce: " + bronce);
    }

    public void mostrarGanadores() {

        if (!simulado) {
            System.out.println("El evento aún no ha sido simulado.");
            return;
        }

        System.out.println("\nEvento: " + nombreEvento);
        System.out.println("🥇 Oro: " + oro);
        System.out.println("🥈 Plata: " + plata);
        System.out.println("🥉 Bronce: " + bronce);
    }
}

public class JuegosOlimpicosCLI {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Evento> eventos = new ArrayList<>();

    static HashMap<String, Integer> rankingPaises = new HashMap<>();

    public static void main(String[] args) {

        int opcion;

        do {

            System.out.println("\n========= JJOO PARÍS 2024 =========");
            System.out.println("1. Registrar evento");
            System.out.println("2. Registrar participantes");
            System.out.println("3. Simular eventos");
            System.out.println("4. Generar informe");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    registrarEvento();
                    break;

                case 2:
                    registrarParticipantes();
                    break;

                case 3:
                    simularEventos();
                    break;

                case 4:
                    generarInforme();
                    break;

                case 5:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }

    public static void registrarEvento() {

        System.out.print("Ingrese el nombre del evento: ");
        String nombre = sc.nextLine();

        Evento evento = new Evento(nombre);

        eventos.add(evento);

        System.out.println("Evento registrado correctamente.");
    }

    public static void registrarParticipantes() {

        if (eventos.isEmpty()) {
            System.out.println("Primero debe registrar eventos.");
            return;
        }

        mostrarEventos();

        System.out.print("Seleccione el número del evento: ");
        int indice = sc.nextInt();
        sc.nextLine();

        if (indice < 1 || indice > eventos.size()) {
            System.out.println("Evento inválido.");
            return;
        }

        Evento evento = eventos.get(indice - 1);

        System.out.print("Cantidad de participantes: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {

            System.out.println("\nParticipante " + (i + 1));

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("País: ");
            String pais = sc.nextLine();

            Participante participante = new Participante(nombre, pais);

            evento.agregarParticipante(participante);
        }

        System.out.println("Participantes registrados correctamente.");
    }

    public static void simularEventos() {

        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados.");
            return;
        }

        for (Evento evento : eventos) {

            evento.simularEvento();

            if (evento.simulado) {

                sumarMedalla(evento.oro.pais, 3);
                sumarMedalla(evento.plata.pais, 2);
                sumarMedalla(evento.bronce.pais, 1);
            }
        }
    }

    public static void generarInforme() {

        if (eventos.isEmpty()) {
            System.out.println("No existen eventos.");
            return;
        }

        System.out.println("\n========= INFORME FINAL =========");

        for (Evento evento : eventos) {
            evento.mostrarGanadores();
        }

        System.out.println("\n========= RANKING DE PAÍSES =========");

        ArrayList<String> paises = new ArrayList<>(rankingPaises.keySet());

        paises.sort((a, b) -> rankingPaises.get(b) - rankingPaises.get(a));

        for (String pais : paises) {
            System.out.println(pais + " -> " + rankingPaises.get(pais) + " puntos");
        }
    }

    public static void sumarMedalla(String pais, int puntos) {

        rankingPaises.put(
                pais,
                rankingPaises.getOrDefault(pais, 0) + puntos
        );
    }

    public static void mostrarEventos() {

        System.out.println("\n===== EVENTOS DISPONIBLES =====");

        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i).nombreEvento);
        }
    }
}