public class Main {

    public static void main(String[] args) {

        Pedido pedido1 = new PedidoComida(
                101,
                "Av. Libertad 123",
                "Comida"
        );

        Pedido pedido2 = new PedidoEncomienda(
                102,
                "Calle Los Robles 456",
                "Encomienda"
        );

        Pedido pedido3 = new PedidoExpress(
                103,
                "Pasaje Central 789",
                "Express"
        );

        pedido1.asignarRepartidor();
        ((PedidoComida) pedido1).asignarRepartidor("Juan Pérez");

        pedido2.asignarRepartidor();
        ((PedidoEncomienda) pedido2).asignarRepartidor("Camila Soto");

        pedido3.asignarRepartidor();
        ((PedidoExpress) pedido3).asignarRepartidor("Luis Díaz");
    }
}