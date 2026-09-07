package model;

import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {

    private String nombre;
    private List<Pedido> pedidos;

    public Repartidor(String nombre, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pedidos = pedidos;
    }

    @Override
    public void run() {

        Random random = new Random();

        for (Pedido pedido : pedidos) {

            try {

                System.out.println(
                        "[Repartidor: " + nombre +
                                "] Entregando " +
                                pedido.getClass().getSimpleName() +
                                " #" + pedido.getIdPedido()
                );

                Thread.sleep(random.nextInt(2000) + 1000);

                System.out.println(
                        "[Repartidor: " + nombre +
                                "] Pedido #" +
                                pedido.getIdPedido() +
                                " entregado."
                );

            } catch (InterruptedException e) {

                System.out.println(
                        "[Repartidor: " + nombre +
                                "] Error durante la entrega."
                );

                Thread.currentThread().interrupt();
            }
        }

        System.out.println(
                "[Repartidor: " + nombre +
                        "] Finalizó sus entregas."
        );
    }
}