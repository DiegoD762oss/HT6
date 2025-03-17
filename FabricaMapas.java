package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase que implementa el patrón de diseño Factory para crear diferentes
 * tipos de Map según la selección del usuario.
 */
public class FabricaMapas {
    /**
     * Crea una instancia de Map según el tipo especificado.
     * @param tipo 1 para HashMap, 2 para TreeMap, 3 para LinkedHashMap.
     * @return Instancia del Map correspondiente.
     */
    public static <K, V> Map<K, V> crearMap(int tipo) {
        switch (tipo) {
            case 1:
                return new HashMap<>();
            case 2:
                return new TreeMap<>();
            case 3:
                return new LinkedHashMap<>();
            default:
                return new HashMap<>();
        }
    }
}
