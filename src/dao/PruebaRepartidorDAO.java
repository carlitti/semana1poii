package dao;

import modelo.Repartidor;

public class PruebaRepartidorDAO {

    public static void main(String[] args) {

        RepartidorDAO dao = new RepartidorDAO();

        // CREATE
        Repartidor repartidor =
                new Repartidor("Carlos");

        int id = dao.guardar(repartidor);

        System.out.println(
                "ID generado: " + id
        );


        // READ
        System.out.println("\n--- LISTA ---");

        for (Repartidor r : dao.listarTodos()) {

            System.out.println(
                    r.getId()
                            + " - "
                            + r.getNombre()
            );
        }


        // UPDATE
        if (id != -1) {

            repartidor.setNombre(
                    "Carlos Actualizado"
            );

            boolean actualizado =
                    dao.actualizar(repartidor);

            System.out.println(
                    "\nActualizado: "
                            + actualizado
            );
        }


        // READ DE NUEVO
        System.out.println(
                "\n--- DESPUÉS DE ACTUALIZAR ---"
        );

        for (Repartidor r : dao.listarTodos()) {

            System.out.println(
                    r.getId()
                            + " - "
                            + r.getNombre()
            );
        }


        // DELETE
        if (id != -1) {

            boolean eliminado =
                    dao.eliminar(id);

            System.out.println(
                    "\nEliminado: "
                            + eliminado
            );
        }


        // READ FINAL
        System.out.println(
                "\n--- LISTA FINAL ---"
        );

        for (Repartidor r : dao.listarTodos()) {

            System.out.println(
                    r.getId()
                            + " - "
                            + r.getNombre()
            );
        }
    }
}