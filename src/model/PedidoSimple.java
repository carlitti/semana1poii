package model;

public class PedidoSimple {

    private int id;
    private String direccionEntrega;
    private EstadoPedido estado;

    public PedidoSimple(
            int id,
            String direccionEntrega) {

        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(
            EstadoPedido estado) {

        this.estado = estado;
    }

    @Override
    public String toString() {

        return "Pedido #" +
                id +
                " - " +
                direccionEntrega;
    }
}