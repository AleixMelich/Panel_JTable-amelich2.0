package org.amelich.app;

import java.util.HashMap;
import java.util.Map;


/**
 * Classe que defineix una excepció personalitzada
 */
public class LaMevaExcepcio extends Exception {
    private static final Map<Integer, String> errorMessages = new HashMap<>();

    static {
        errorMessages.put(1, "Per modificar o borrar una fila l'has de seleccionar a la taula");
        errorMessages.put(2, "No pots introduir cap numero en aquest camp, nomes caracters.");
        errorMessages.put(3, "Aquest nom ja esta inscrit a la taula, canvial.");
        errorMessages.put(4, "Has d'introduir una nota correcta (0-10) i si te decimals separar-la per comes.");
        errorMessages.put(5, "Falta omplir alguna dada, revisa-ho.");
        errorMessages.put(6, "No pots introduir cap caràcter en aquest camp, només números.");
        errorMessages.put(7, "No hi han dades per exportar.");
        errorMessages.put(8, "Error al crear el fitxer de sortida.");
        errorMessages.put(9, "No s'ha trobat cap alumne amb el filtre introduit.");
        // Afegeix més missatges d'error segons sigui necessari pensa que tambe hauras de modificar el constructor setExceptio de la classe Controller.
    }

    private int errorCode;

    public LaMevaExcepcio(int errorCode) {
        super(errorMessages.getOrDefault(errorCode, "Error desconegut"));
        this.errorCode = errorCode;
    }

    public String retornaMissatge() {
        return super.getMessage();
    }

    public int retornaNumero() {
        return this.errorCode;
    }

}

