import java.util.ArrayList;

public class ControladorDeEnvios
        implements Despachable, Cancelable, Rastreable {

    private ArrayList<String> historial = new ArrayList<>();

    public void agregarHistorial(String registro) {
        historial.add(registro);
    }

    @Override
    public void despachar() {
        System.out.println("Pedido despachado correctamente.");
    }

    @Override
    public void cancelar() {
        System.out.println("→ Pedido cancelado exitosamente.");
    }

    @Override
    public void verHistorial() {

        System.out.println("\n===== HISTORIAL =====");

        for (String registro : historial) {
            System.out.println("- " + registro);
        }
    }
}