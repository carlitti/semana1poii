package model;

public class Repartidor implements Runnable {

    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {

        try {

            while (true) {

                PedidoSimple pedido =
                        zonaDeCarga.retirarPedido();

                if (pedido == null) {

                    System.out.println(
                            "[Zona de carga vacía]"
                    );

                    break;
                }

                System.out.println(
                        "[Repartidor - " +
                                nombre +
                                "] Retirando pedido #" +
                                pedido.getId()
                );

                pedido.setEstado(
                        EstadoPedido.EN_REPARTO
                );

                System.out.println(
                        "[Repartidor - " +
                                nombre +
                                "] Estado: " +
                                pedido.getEstado()
                );

                System.out.println(
                        "[Repartidor - " +
                                nombre +
                                "] Entregando pedido #" +
                                pedido.getId()
                );

                Thread.sleep(2000);

                pedido.setEstado(
                        EstadoPedido.ENTREGADO
                );

                System.out.println(
                        "[Repartidor - " +
                                nombre +
                                "] Estado: " +
                                pedido.getEstado()
                );
            }

        } catch (InterruptedException e) {

            System.out.println(
                    "[Repartidor - " +
                            nombre +
                            "] Error durante la entrega."
            );

            Thread.currentThread().interrupt();
        }
    }
}