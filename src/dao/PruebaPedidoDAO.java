package dao;

import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.PedidoComida;

public class PruebaPedidoDAO {

    public static void main(String[] args) {

        PedidoDAO dao = new PedidoDAO();

        // =========================
        // CREATE
        // =========================

        Pedido pedido = new PedidoComida(
                0,
                "Av. Providencia 1234",
                5.0
        );

        boolean guardado =
                dao.guardar(pedido);

        System.out.println(
                "Guardado: " + guardado
        );

        System.out.println(
                "ID generado: "
                        + pedido.getIdPedido()
        );


        // =========================
        // READ
        // =========================

        System.out.println(
                "\n--- LISTA DE PEDIDOS ---"
        );

        for (Pedido p : dao.listarTodos()) {

            System.out.println(
                    p.getIdPedido()
                            + " | "
                            + p.getDireccionEntrega()
                            + " | "
                            + p.getTipo()
                            + " | "
                            + p.getEstado()
            );
        }


        // =========================
        // UPDATE
        // =========================

        pedido.setDireccionEntrega(
                "Av. Providencia 9999"
        );

        pedido.setEstado(
                EstadoPedido.EN_REPARTO
        );

        boolean actualizado =
                dao.actualizar(pedido);

        System.out.println(
                "\nActualizado: "
                        + actualizado
        );


        // =========================
        // READ DESPUÉS DE UPDATE
        // =========================

        System.out.println(
                "\n--- DESPUÉS DE ACTUALIZAR ---"
        );

        for (Pedido p : dao.listarTodos()) {

            System.out.println(
                    p.getIdPedido()
                            + " | "
                            + p.getDireccionEntrega()
                            + " | "
                            + p.getTipo()
                            + " | "
                            + p.getEstado()
            );
        }


        // =========================
        // DELETE
        // =========================

        boolean eliminado =
                dao.eliminar(
                        pedido.getIdPedido()
                );

        System.out.println(
                "\nEliminado: "
                        + eliminado
        );


        // =========================
        // READ FINAL
        // =========================

        System.out.println(
                "\n--- LISTA FINAL ---"
        );

        for (Pedido p : dao.listarTodos()) {

            System.out.println(
                    p.getIdPedido()
                            + " | "
                            + p.getDireccionEntrega()
                            + " | "
                            + p.getTipo()
                            + " | "
                            + p.getEstado()
            );
        }
    }
}