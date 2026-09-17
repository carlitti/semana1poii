package modelo;

public class PedidoExpress extends Pedido {

    public PedidoExpress(
            int idPedido,
            String direccionEntrega,
            double distanciaKm) {

        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {

        int tiempo = 10;

        if (getDistanciaKm() > 5) {
            tiempo += 5;
        }

        return tiempo;
    }

    @Override
    public String getTipo() {
        return "Express";
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor...");
        System.out.println("Buscando repartidor más cercano...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Asignando repartidor...");
        System.out.println("Buscando repartidor más cercano...");
        System.out.println("Repartidor asignado: " + nombreRepartidor);
    }
}