import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

public class AdministradorPokemonTest {
    private AdministradorPokemon admin;

    @BeforeEach
    void setUp() {
        admin = new AdministradorPokemon();
    }

    @Test
    void testCargarPokemonesDesdeCSV() {
        try {
            admin.cargarPokemonesDesdeCSV("POK.csv");
            assertFalse(admin.pokemonesDisponibles.isEmpty(), "La lista no debe estar vacía después de cargar el CSV.");
        } catch (IOException e) {
            fail("Error al cargar el archivo CSV: " + e.getMessage());
        }
    }

    @Test
    void testCargarPokemonesDesdeCSV_ArchivoNoEncontrado() {
        assertThrows(IOException.class, () -> admin.cargarPokemonesDesdeCSV("archivo_inexistente.csv"));
    }

    @Test
    void testAgregarPokemonAColeccion() {
        admin.pokemonesDisponibles.put("Pikachu", new DatosPokemon("Pikachu", "Electrico", "None", "Static"));
        admin.agregarPokemonAColeccion("Pikachu");
        assertEquals(1, admin.coleccionUsuario.size(), "Debe haber un Pokémon en la colección.");
    }

    @Test
    void testAgregarPokemonAColeccion_Duplicado() {
        admin.pokemonesDisponibles.put("Pikachu", new DatosPokemon("Pikachu", "Electrico", "None", "Static"));
        admin.agregarPokemonAColeccion("Pikachu");
        admin.agregarPokemonAColeccion("Pikachu");
        assertEquals(1, admin.coleccionUsuario.size(), "No debe agregarse dos veces el mismo Pokémon.");
    }

    @Test
    void testMostrarDatosPokemon() {
        admin.pokemonesDisponibles.put("Charmander", new DatosPokemon("Charmander", "Fuego", "None", "Blaze"));
        assertNotNull(admin.pokemonesDisponibles.get("Charmander"), "Debe existir el Pokémon.");
    }

    @Test
    void testMostrarDatosPokemon_NoExistente() {
        assertEquals("Pokémon: Desconocido | Tipo1: - | Tipo2: - | Habilidad: -",
                admin.pokemonesDisponibles.getOrDefault("NoExiste", new DatosPokemon("Desconocido", "-", "-", "-")).toString());
    }

    @Test
    void testMostrarColeccionPorTipo() {
        admin.pokemonesDisponibles.put("Bulbasaur", new DatosPokemon("Bulbasaur", "Planta", "Veneno", "Overgrow"));
        admin.pokemonesDisponibles.put("Charmander", new DatosPokemon("Charmander", "Fuego", "None", "Blaze"));
        admin.agregarPokemonAColeccion("Bulbasaur");
        admin.agregarPokemonAColeccion("Charmander");

        List<DatosPokemon> sortedCollection = admin.coleccionUsuario.stream()
                .sorted((p1, p2) -> p1.getTipo1().compareTo(p2.getTipo1()))
                .toList();
        
        assertEquals("Bulbasaur", sortedCollection.get(0).getNombre(), "Bulbasaur debe estar antes de Charmander.");
        assertEquals("Charmander", sortedCollection.get(1).getNombre(), "Charmander debe estar después de Bulbasaur.");
    }
}
