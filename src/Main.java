import model.EstadoPedido;
import model.PedidoSimple;
import model.Repartidor;
import model.ZonaDeCarga;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ZonaDeCarga zona =
                new ZonaDeCarga();

        System.out.println(
                "[Zona de carga inicializada]"
        );

        zona.agregarPedido(
                new PedidoSimple(
                        1,
                        "Santiago Centro"));

        zona.agregarPedido(
                new PedidoSimple(
                        2,
                        "Providencia"));

        zona.agregarPedido(
                new PedidoSimple(
                        3,
                        "Ñuñoa"));

        zona.agregarPedido(
                new PedidoSimple(
                        4,
                        "Recoleta"));

        zona.agregarPedido(
                new PedidoSimple(
                        5,
                        "Las Condes"));

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        executor.execute(
                new Repartidor(
                        "Juan",
                        zona));

        executor.execute(
                new Repartidor(
                        "Camila",
                        zona));

        executor.execute(
                new Repartidor(
                        "Pedro",
                        zona));

        executor.shutdown();

        try {

            executor.awaitTermination(
                    1,
                    TimeUnit.MINUTES);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "\nTodos los pedidos han sido entregados correctamente"
        );
    }
}