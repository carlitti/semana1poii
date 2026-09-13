package model;

import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {

    private Queue<PedidoSimple> pedidos =
            new LinkedList<>();

    public synchronized void agregarPedido(
            PedidoSimple pedido) {

        pedidos.add(pedido);

        System.out.println(
                "Pedido #" +
                        pedido.getId() +
                        " agregado. Destino: " +
                        pedido.getDireccionEntrega()
        );
    }

    public synchronized PedidoSimple retirarPedido() {

        if (pedidos.isEmpty()) {

            return null;
        }

        return pedidos.poll();
    }
}