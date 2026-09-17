package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorPedidos {

    private final List<Pedido> pedidos;

    public GestorPedidos() {
        pedidos = new ArrayList<>();
    }

    public boolean agregarPedido(Pedido pedido) {

        if (buscarPedidoPorId(pedido.getIdPedido()) != null) {
            return false;
        }

        pedidos.add(pedido);
        return true;
    }

    public Pedido buscarPedidoPorId(int idPedido) {

        for (Pedido pedido : pedidos) {

            if (pedido.getIdPedido() == idPedido) {
                return pedido;
            }
        }

        return null;
    }

    public List<Pedido> getPedidos() {
        return Collections.unmodifiableList(pedidos);
    }

    public boolean estaVacio() {
        return pedidos.isEmpty();
    }
}