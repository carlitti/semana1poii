package dao;

import modelo.Entrega;

import java.time.LocalDate;
import java.time.LocalTime;

public class PruebaEntregaDAO {

    public static void main(String[] args) {

        EntregaDAO dao = new EntregaDAO();

        /*
         * Usamos los registros que ya comprobamos:
         *
         * Pedido 101 -> sucre
         * Repartidor 1 -> carlos
         */

        // =========================
        // CREATE
        // =========================

        Entrega entrega = new Entrega(
                101,
                1,
                LocalDate.of(2026, 10, 1),
                LocalTime.of(18, 30)
        );

        boolean guardada =
                dao.guardar(entrega);

        System.out.println(
                "Guardada: " + guardada
        );

        System.out.println(
                "ID generado: "
                        + entrega.getId()
        );


        // =========================
        // READ
        // =========================

        System.out.println(
                "\n--- LISTA DE ENTREGAS ---"
        );

        for (Entrega e : dao.listarTodos()) {

            System.out.println(
                    e.getId()
                            + " | Pedido: "
                            + e.getIdPedido()
                            + " | Repartidor: "
                            + e.getIdRepartidor()
                            + " | Fecha: "
                            + e.getFecha()
                            + " | Hora: "
                            + e.getHora()
            );
        }


        // =========================
        // READ POR PEDIDO
        // =========================

        System.out.println(
                "\n--- ENTREGAS DEL PEDIDO 101 ---"
        );

        for (Entrega e : dao.listarPorPedido(101)) {

            System.out.println(
                    e.getId()
                            + " | "
                            + e.getFecha()
                            + " | "
                            + e.getHora()
            );
        }


        // =========================
        // READ POR REPARTIDOR
        // =========================

        System.out.println(
                "\n--- ENTREGAS DEL REPARTIDOR 1 ---"
        );

        for (Entrega e : dao.listarPorRepartidor(1)) {

            System.out.println(
                    e.getId()
                            + " | Pedido: "
                            + e.getIdPedido()
                            + " | "
                            + e.getFecha()
                            + " | "
                            + e.getHora()
            );
        }


        // =========================
        // UPDATE
        // =========================

        entrega.setFecha(
                LocalDate.of(2026, 10, 2)
        );

        entrega.setHora(
                LocalTime.of(20, 15)
        );

        boolean actualizada =
                dao.actualizar(entrega);

        System.out.println(
                "\nActualizada: "
                        + actualizada
        );


        // =========================
        // READ DESPUÉS DEL UPDATE
        // =========================

        System.out.println(
                "\n--- DESPUÉS DE ACTUALIZAR ---"
        );

        for (Entrega e : dao.listarTodos()) {

            System.out.println(
                    e.getId()
                            + " | Pedido: "
                            + e.getIdPedido()
                            + " | Repartidor: "
                            + e.getIdRepartidor()
                            + " | Fecha: "
                            + e.getFecha()
                            + " | Hora: "
                            + e.getHora()
            );
        }


        // =========================
        // DELETE
        // =========================

        boolean eliminada =
                dao.eliminar(
                        entrega.getId()
                );

        System.out.println(
                "\nEliminada: "
                        + eliminada
        );


        // =========================
        // READ FINAL
        // =========================

        System.out.println(
                "\n--- LISTA FINAL ---"
        );

        for (Entrega e : dao.listarTodos()) {

            System.out.println(
                    e.getId()
                            + " | Pedido: "
                            + e.getIdPedido()
                            + " | Repartidor: "
                            + e.getIdRepartidor()
                            + " | Fecha: "
                            + e.getFecha()
                            + " | Hora: "
                            + e.getHora()
            );
        }
    }
}