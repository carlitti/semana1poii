public class PedidoComida extends Pedido {

    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        return 15 + (int)(2 * getDistanciaKm());
    }

    @Override
    public void mostrarResumen() {

        System.out.println("\n===== PEDIDO COMIDA =====");
        super.mostrarResumen();

        System.out.println("Tipo: Comida");
        System.out.println("Tiempo estimado de entrega: "
                + calcularTiempoEntrega() + " minutos");
    }
}