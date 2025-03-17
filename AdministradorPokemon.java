import java.io.*;
import java.util.*;

/**
 * Clase encargada de gestionar la información de los Pokémon con análisis de rendimiento.
 */
public class AdministradorPokemon {
    private Map<String, DatosPokemon> pokemonesDisponibles;
    private List<DatosPokemon> coleccionUsuario;
    private int tipoMap;

    public AdministradorPokemon() {
        this.tipoMap = 1;
        this.pokemonesDisponibles = FabricaMapas.crearMap(tipoMap);
        this.coleccionUsuario = new ArrayList<>();
    }

    public void setMapImplementacion(int tipo) {
        this.tipoMap = tipo;
        Map<String, DatosPokemon> nuevoMap = FabricaMapas.crearMap(tipo);
        nuevoMap.putAll(pokemonesDisponibles);
        this.pokemonesDisponibles = nuevoMap;
    }

    public void cargarPokemonesDesdeCSV(String rutaArchivo) throws IOException {
        long start = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] data = linea.split(",");
                if (data.length >= 4) {
                    pokemonesDisponibles.put(data[0], new DatosPokemon(data[0], data[1], data[2], data[3]));
                }
            }
        }
        long end = System.nanoTime();
        System.out.println("Tiempo de ejecución (cargarPokemonesDesdeCSV): " + (end - start) / 1e6 + " ms");
    }

    public void agregarPokemonAColeccion(String nombre) {
        long start = System.nanoTime();
        if (pokemonesDisponibles.containsKey(nombre)) {
            DatosPokemon pokemon = pokemonesDisponibles.get(nombre);
            if (!coleccionUsuario.contains(pokemon)) {
                coleccionUsuario.add(pokemon);
                System.out.println("Pokémon agregado a tu colección.");
            } else {
                System.out.println("Este Pokémon ya está en tu colección.");
            }
        } else {
            System.out.println("Error: Pokémon no encontrado.");
        }
        long end = System.nanoTime();
        System.out.println("Tiempo de ejecución (agregarPokemonAColeccion): " + (end - start) / 1e6 + " ms");
    }

    public void mostrarDatosPokemon(String nombre) {
        long start = System.nanoTime();
        System.out.println(pokemonesDisponibles.getOrDefault(nombre, new DatosPokemon("Desconocido", "-", "-", "-")));
        long end = System.nanoTime();
        System.out.println("Tiempo de ejecución (mostrarDatosPokemon): " + (end - start) / 1e6 + " ms");
    }

    public void mostrarColeccionPorTipo() {
        long start = System.nanoTime();
        coleccionUsuario.stream()
                .sorted(Comparator.comparing(DatosPokemon::getTipo1))
                .forEach(System.out::println);
        long end = System.nanoTime();
        System.out.println("Tiempo de ejecución (mostrarColeccionPorTipo): " + (end - start) / 1e6 + " ms");
    }
}
