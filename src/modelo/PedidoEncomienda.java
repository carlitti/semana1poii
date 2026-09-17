package modelo;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(
            int idPedido,
            String direccionEntrega,
            double distanciaKm) {

        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (20 + (1.5 * getDistanciaKm()));
    }

    @Override
    public String getTipo() {
        return "Encomienda";
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor...");
        System.out.println("Validando peso y embalaje... OK");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Asignando repartidor...");
        System.out.println("Validando peso y embalaje... OK");
        System.out.println("Repartidor asignado: " + nombreRepartidor);
    }
}