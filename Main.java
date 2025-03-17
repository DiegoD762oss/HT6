

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal que ejecuta el programa e interactúa con el usuario.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdministradorPokemon administrador = new AdministradorPokemon();

        System.out.println("Ingrese la ruta del archivo CSV:");
        String rutaArchivo = scanner.nextLine();

        try {
            administrador.cargarPokemonesDesdeCSV(rutaArchivo);
            System.out.println("Datos cargados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            return;
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar un Pokémon a la colección");
            System.out.println("2. Mostrar información de un Pokémon");
            System.out.println("3. Mostrar colección ordenada por tipo");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String nombre = scanner.nextLine();
                    administrador.agregarPokemonAColeccion(nombre);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String consulta = scanner.nextLine();
                    administrador.mostrarDatosPokemon(consulta);
                    break;
                case 3:
                    administrador.mostrarColeccionPorTipo();
                    break;
                case 4:
                    continuar = false;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}

        scanner.close();
    }
}
