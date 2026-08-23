public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido,
                            String direccionEntrega,
                            double distanciaKm) {

        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int)(20 + (1.5 * getDistanciaKm()));
    }

    @Override
    public void mostrarResumen() {

        System.out.println("\n===== PEDIDO ENCOMIENDA =====");
        super.mostrarResumen();

        System.out.println("Tipo: Encomienda");
        System.out.println("Tiempo estimado de entrega: "
                + calcularTiempoEntrega() + " minutos");
    }
}