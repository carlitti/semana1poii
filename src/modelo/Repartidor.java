package modelo;

public class Repartidor implements Runnable {

    private int id;
    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    // Constructor utilizado para la lógica concurrente
    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    // Constructor utilizado al registrar un repartidor en MySQL
    public Repartidor(String nombre) {
        this.nombre = nombre;
        this.zonaDeCarga = null;
    }

    // Constructor utilizado al leer repartidores desde MySQL
    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.zonaDeCarga = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }

    @Override
    public void run() {

        if (zonaDeCarga == null) {
            return;
        }

        try {

            while (true) {

                PedidoSimple pedido = zonaDeCarga.retirarPedido();

                if (pedido == null) {

                    System.out.println("[Zona de carga vacía]");
                    break;
                }

                System.out.println(
                        "[Repartidor - " +
                                nombre +
                                "] Retirando pedido #" +
                                pedido.getId()
                );

                pedido.setEstado(EstadoPedido.EN_REPARTO);

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

                pedido.setEstado(EstadoPedido.ENTREGADO);

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