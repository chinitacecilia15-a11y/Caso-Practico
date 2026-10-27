
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Ordenamiento {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Actividad> actividades = new ArrayList<>();

    static class Actividad {
        String nombre;
        String tipo;
        LocalDate fechaEntrega;
        String descripcion;

        public Actividad(String nombre, String tipo,
                         LocalDate fechaEntrega, String descripcion) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.fechaEntrega = fechaEntrega;
            this.descripcion = descripcion;
        }
    }

  
    public static void registrarActividad() {
        try {
            System.out.println("\n--- REGISTRAR ACTIVIDAD ---");

            System.out.print("Nombre del proyecto, practica o tarea: ");
            String nombre = sc.nextLine();

            System.out.print("Tipo de actividad: ");
            String tipo = sc.nextLine();

            System.out.print("Fecha de entrega (AAAA-MM-DD): ");
            LocalDate fecha = LocalDate.parse(sc.nextLine());

            System.out.print("Descripcion: ");
            String descripcion = sc.nextLine();

            actividades.add(
                new Actividad(nombre, tipo, fecha, descripcion)
            );

            System.out.println("Actividad registrada correctamente.");

        } catch (DateTimeParseException e) {
            System.out.println("Fecha incorrecta. Usa AAAA-MM-DD.");
        }
    }

    public static void mostrarActividades() {
        System.out.println("\n--- LISTA DE ACTIVIDADES ---");

        if (actividades.isEmpty()) {
            System.out.println("No hay actividades registradas.");
            return;
        }

        for (int i = 0; i < actividades.size(); i++) {
            Actividad a = actividades.get(i);

            System.out.println("\nActividad " + (i + 1));
            System.out.println("Nombre: " + a.nombre);
            System.out.println("Tipo: " + a.tipo);
            System.out.println("Fecha de entrega: " + a.fechaEntrega);
            System.out.println("Descripcion: " + a.descripcion);
        }
    }


    public static void ordenarPorFecha() {
        if (actividades.isEmpty()) {
            System.out.println("\nNo hay actividades para ordenar.");
            return;
        }

        Collections.sort(actividades,
            Comparator.comparing(a -> a.fechaEntrega));

        System.out.println("\n--- ACTIVIDADES ORDENADAS POR FECHA ---");
        mostrarActividades();
    }

   
    public static void Busqueda() {
        System.out.print("\nNombre, tipo o descripcion a buscar: ");
        String busqueda = sc.nextLine().toLowerCase();

        boolean encontrado = false;

        for (Actividad a : actividades) {
            if (a.nombre.toLowerCase().contains(busqueda)
                || a.tipo.toLowerCase().contains(busqueda)
                || a.descripcion.toLowerCase().contains(busqueda)) {

                System.out.println("\nNombre: " + a.nombre);
                System.out.println("Tipo: " + a.tipo);
                System.out.println("Fecha: " + a.fechaEntrega);
                System.out.println("Descripcion: " + a.descripcion);

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron actividades.");
        }
    }

    
    public static void eliminarActividad() {
        if (actividades.isEmpty()) {
            System.out.println("\nNo hay actividades para eliminar.");
            return;
        }

        mostrarActividades();

        System.out.print("\nNumero de actividad a eliminar: ");

        try {
            int numero = Integer.parseInt(sc.nextLine());

            if (numero >= 1 && numero <= actividades.size()) {
                actividades.remove(numero - 1);
                System.out.println("Actividad eliminada correctamente.");
            } else {
                System.out.println("Numero de actividad no valido.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ingresa un numero valido.");
        }
    }

    
    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n===== ORDENAMIENTO ESCOLAR =====");
            System.out.println("1. Registrar actividad");
            System.out.println("2. Mostrar actividades");
            System.out.println("3. Ordenar por fecha de entrega");
            System.out.println("4. Buscar actividad");
            System.out.println("5. Eliminar actividad");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        registrarActividad();
                        break;
                    case 2:
                        mostrarActividades();
                        break;
                    case 3:
                        ordenarPorFecha();
                        break;
                    case 4:
                        Busqueda();
                        break;
                    case 5:
                        eliminarActividad();
                        break;
                    case 6:
                        System.out.println("Fin del programa.");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ingresa una opcion numerica.");
            }

        } while (opcion != 6);

        sc.close();
    }
}
