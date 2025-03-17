
import java.io.Serializable;

/**
 * Clase que representa un Pokémon con sus atributos básicos.
 */
public class DatosPokemon implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String tipo1;
    private String tipo2;
    private String habilidad;

    /**
     * Constructor de la clase Pokémon.
     * @param nombre Nombre del Pokémon.
     * @param tipo1 Tipo primario del Pokémon.
     * @param tipo2 Tipo secundario del Pokémon.
     * @param habilidad Habilidad del Pokémon.
     */
    public DatosPokemon(String nombre, String tipo1, String tipo2, String habilidad) {
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.habilidad = habilidad;
    }

    public String getNombre() { return nombre; }
    public String getTipo1() { return tipo1; }
    public String getTipo2() { return tipo2; }
    public String getHabilidad() { return habilidad; }

    @Override
    public String toString() {
        return "Pokémon: " + nombre + " | Tipo1: " + tipo1 + " | Tipo2: " + tipo2 + " | Habilidad: " + habilidad;
    }

    
}
