public class Main {

    public static void main(String[] args) {

        Pedido comida = new PedidoComida(
                101,
                "Av. Italia 456",
                4
        );

        Pedido encomienda = new PedidoEncomienda(
                102,
                "Av. Santa Rosa 567",
                7
        );

        Pedido express = new PedidoExpress(
                103,
                "Av. Apoquindo 1500",
                8
        );

        // PEDIDO COMIDA
        comida.mostrarResumen();

        System.out.println("\nAsignación automática:");
        comida.asignarRepartidor();

        System.out.println("\nAsignación manual:");
        comida.asignarRepartidor("Luis Díaz");

        System.out.println("\n----------------------------");

        // PEDIDO ENCOMIENDA
        encomienda.mostrarResumen();

        System.out.println("\nAsignación automática:");
        encomienda.asignarRepartidor();

        System.out.println("\nAsignación manual:");
        encomienda.asignarRepartidor("Daniela Tapia");

        System.out.println("\n----------------------------");

        // PEDIDO EXPRESS
        express.mostrarResumen();

        System.out.println("\nAsignación automática:");
        express.asignarRepartidor();

        System.out.println("\nAsignación manual:");
        express.asignarRepartidor("Pedro González");

        System.out.println("\n----------------------------");

        // CONTROLADOR DE ENVÍOS
        ControladorDeEnvios controlador = new ControladorDeEnvios();

        System.out.println("\nDESPACHO");
        controlador.despachar();

        System.out.println("\nCANCELACIÓN");
        System.out.println("Cancelando Pedido Express #103...");
        controlador.cancelar();

        // HISTORIAL
        controlador.agregarHistorial(
                "PedidoComida #101 - entregado por Luis Díaz");

        controlador.agregarHistorial(
                "PedidoEncomienda #102 - entregado por Daniela Tapia");

        System.out.println();
        controlador.verHistorial();
    }
}