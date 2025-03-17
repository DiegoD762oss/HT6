package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase encargada de gestionar la información de los Pokémon.
 * Permite cargar datos desde un archivo CSV, administrar una colección
 * de Pokémon del usuario y realizar consultas sobre la base de datos de Pokémon.
 */
public class AdministradorPokemon {
    /** Mapa que almacena todos los Pokémon disponibles, indexados por nombre. */
    private Map<String, Pokemon> pokemonesDisponibles;
    
    /** Lista que almacena la colección personal del usuario. */
    private List<Pokemon> coleccionUsuario;
    
    /** Tipo de estructura de Map seleccionada por el usuario. */
    private int tipoMap;

    /**
     * Constructor de la clase. Inicializa con HashMap por defecto.
     */
    public AdministradorPokemon() {
        this.tipoMap = 1;
        this.pokemonesDisponibles = FabricaMapas.crearMap(tipoMap);
        this.coleccionUsuario = new ArrayList<>();
    }

    /**
     * Cambia la implementación de Map en tiempo de ejecución.
     * @param tipo el tipo de Map a utilizar (1: HashMap, 2: TreeMap, 3: LinkedHashMap).
     */
    public void setMapImplementacion(int tipo) {
        this.tipoMap = tipo;
        Map<String, Pokemon> nuevoMap = FabricaMapas.crearMap(tipo);
        nuevoMap.putAll(pokemonesDisponibles);
        this.pokemonesDisponibles = nuevoMap;
    }

    /**
     * Carga datos de Pokémon desde un archivo CSV.
     * @param rutaArchivo Ruta del archivo CSV.
     * @throws IOException En caso de error de lectura del archivo.
     */
    public void cargarPokemonesDesdeCSV(String rutaArchivo) throws IOException {
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
                    pokemonesDisponibles.put(data[0], new Pokemon(data[0], data[1], data[2], data[3]));
                }
            }
        }
    }

    /**
     * Agrega un Pokémon a la colección del usuario si no está repetido.
     * @param nombre Nombre del Pokémon a agregar.
     */
    public void agregarPokemonAColeccion(String nombre) {
        if (pokemonesDisponibles.containsKey(nombre)) {
            Pokemon pokemon = pokemonesDisponibles.get(nombre);
            if (!coleccionUsuario.contains(pokemon)) {
                coleccionUsuario.add(pokemon);
                System.out.println("Pokémon agregado a tu colección.");
            } else {
                System.out.println("Este Pokémon ya está en tu colección.");
            }
        } else {
            System.out.println("Error: Pokémon no encontrado.");
        }
    }

    /**
     * Muestra la información de un Pokémon dado su nombre.
     * @param nombre Nombre del Pokémon a consultar.
     */
    public void mostrarDatosPokemon(String nombre) {
        System.out.println(pokemonesDisponibles.getOrDefault(nombre, new Pokemon("Desconocido", "-", "-", "-")));
    }

    /**
     * Muestra la colección de Pokémon del usuario ordenada por tipo.
     */
    public void mostrarColeccionPorTipo() {
        coleccionUsuario.stream()
                .sorted(Comparator.comparing(Pokemon::getTipo1))
                .forEach(System.out::println);
    }
}
