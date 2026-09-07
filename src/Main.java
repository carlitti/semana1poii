import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;
import model.Repartidor;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Pedido pedido1 =
                new PedidoComida(
                        101,
                        "Av. Italia 456",
                        4);

        Pedido pedido2 =
                new PedidoEncomienda(
                        102,
                        "Av. Santa Rosa 567",
                        7);

        Pedido pedido3 =
                new PedidoExpress(
                        103,
                        "Av. Apoquindo 1500",
                        8);

        Pedido pedido4 =
                new PedidoComida(
                        104,
                        "Av. Libertad 200",
                        3);

        Pedido pedido5 =
                new PedidoEncomienda(
                        105,
                        "Los Carrera 500",
                        10);

        Pedido pedido6 =
                new PedidoExpress(
                        106,
                        "O'Higgins 350",
                        5);

        Repartidor camila =
                new Repartidor(
                        "Camila",
                        Arrays.asList(
                                pedido1,
                                pedido2
                        ));

        Repartidor luis =
                new Repartidor(
                        "Luis",
                        Arrays.asList(
                                pedido3,
                                pedido4
                        ));

        Repartidor daniela =
                new Repartidor(
                        "Daniela",
                        Arrays.asList(
                                pedido5,
                                pedido6
                        ));

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        executor.execute(camila);
        executor.execute(luis);
        executor.execute(daniela);

        executor.shutdown();

        try {

            executor.awaitTermination(
                    1,
                    TimeUnit.MINUTES
            );

        } catch (InterruptedException e) {

            System.out.println(
                    "Error al esperar los hilos."
            );

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "\n===== SIMULACIÓN FINALIZADA ====="
        );
    }
}