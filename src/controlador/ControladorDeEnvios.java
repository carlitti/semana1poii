package controlador;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

import java.util.ArrayList;

public class ControladorDeEnvios
        implements Despachable, Cancelable, Rastreable {

    private ArrayList<String> historial = new ArrayList<>();

    public void agregarHistorial(String registro) {
        historial.add(registro);
    }

    @Override
    public void despachar() {
        System.out.println("model.Pedido despachado correctamente.");
    }

    @Override
    public void cancelar() {
        System.out.println("→ model.Pedido cancelado exitosamente.");
    }

    @Override
    public void verHistorial() {

        System.out.println("\n===== HISTORIAL =====");

        for (String registro : historial) {
            System.out.println("- " + registro);
        }
    }
}