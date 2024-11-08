import java.util.HashMap;
import java.util.Scanner;
public class AgendaTelefonos {
    private static HashMap<String, Long> agenda = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1 -> cargarDatos();
                case 2 -> buscarTelefono();
                case 3 -> listarAgenda();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (opcion != 4);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú Agenda ---");
        System.out.println("1- Cargar Datos en la Agenda");
        System.out.println("2- Buscar Teléfono por Nombre");
        System.out.println("3- Listar Agenda");
        System.out.println("4- Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void cargarDatos() {
        String nombre;
        Long telefono;
        do {
            System.out.print("Ingrese el nombre: ");
            nombre = scanner.nextLine();
            if (agenda.containsKey(nombre)) {
                System.out.println("El nombre ya existe en la agenda.");
            } else {
                System.out.print("Ingrese el teléfono: ");
                telefono = scanner.nextLong();
                scanner.nextLine(); // Limpiar buffer
                agenda.put(nombre, telefono);
                System.out.println("Contacto agregado.");
            }
            System.out.print("¿Desea agregar otro contacto? (S/N): ");
        } while (scanner.nextLine().equalsIgnoreCase("S"));
    }

    private static void buscarTelefono() {
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = scanner.nextLine();
        if (agenda.containsKey(nombre)) {
            System.out.println("Teléfono de " + nombre + ": " + agenda.get(nombre));
        } else {
            System.out.println("La persona buscada no se encuentra en la agenda.");
        }
    }

    private static void listarAgenda() {
        System.out.println("\n--- Listado de la Agenda ---");
        agenda.forEach((nombre, telefono) -> System.out.println("Nombre: " + nombre + ", Teléfono: " + telefono));
    }
}
