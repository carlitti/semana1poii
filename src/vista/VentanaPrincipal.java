package vista;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;

import modelo.Entrega;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private final PedidoDAO pedidoDAO;
    private final RepartidorDAO repartidorDAO;
    private final EntregaDAO entregaDAO;

    public VentanaPrincipal() {

        pedidoDAO = new PedidoDAO();
        repartidorDAO = new RepartidorDAO();
        entregaDAO = new EntregaDAO();

        setTitle(
                "SpeedFast - Gestión de Entregas"
        );

        setSize(500, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        inicializarComponentes();
    }

    private void inicializarComponentes() {

        JPanel panelPrincipal =
                new JPanel(
                        new BorderLayout(20, 20)
                );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        40,
                        30,
                        40
                )
        );

        JLabel titulo =
                new JLabel(
                        "SPEEDFAST",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        JLabel subtitulo =
                new JLabel(
                        "Gestión de Entregas - MySQL",
                        SwingConstants.CENTER
                );

        subtitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        JPanel encabezado =
                new JPanel(
                        new GridLayout(2, 1)
                );

        encabezado.add(titulo);
        encabezado.add(subtitulo);

        panelPrincipal.add(
                encabezado,
                BorderLayout.NORTH
        );

        JPanel panelBotones =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                10,
                                15
                        )
                );

        JButton btnRegistrar =
                new JButton(
                        "Registrar Pedido"
                );

        JButton btnListar =
                new JButton(
                        "Listar Pedidos"
                );

        JButton btnEntrega =
                new JButton(
                        "Asignar Repartidor / Iniciar Entrega"
                );

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnEntrega);

        panelPrincipal.add(
                panelBotones,
                BorderLayout.CENTER
        );

        btnRegistrar.addActionListener(
                e -> {

                    VentanaRegistroPedido ventana =
                            new VentanaRegistroPedido();

                    ventana.setVisible(true);
                }
        );

        btnListar.addActionListener(
                e -> {

                    VentanaListaPedidos ventana =
                            new VentanaListaPedidos();

                    ventana.setVisible(true);
                }
        );

        btnEntrega.addActionListener(
                e -> iniciarEntrega()
        );

        add(panelPrincipal);
    }

    private void iniciarEntrega() {

        List<Pedido> pedidos =
                pedidoDAO.listarTodos();

        if (pedidos.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No existen pedidos registrados en MySQL.",
                    "SpeedFast",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String[] opciones =
                new String[pedidos.size()];

        for (int i = 0;
             i < pedidos.size();
             i++) {

            Pedido pedido =
                    pedidos.get(i);

            opciones[i] =
                    "#"
                            + pedido.getIdPedido()
                            + " - "
                            + pedido.getTipo()
                            + " - "
                            + pedido.getDireccionEntrega();
        }

        String seleccion =
                (String)
                        JOptionPane.showInputDialog(
                                this,
                                "Seleccione el pedido:",
                                "Asignar Repartidor",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opciones,
                                opciones[0]
                        );

        if (seleccion == null) {
            return;
        }

        int indiceSeleccionado = -1;

        for (int i = 0;
             i < opciones.length;
             i++) {

            if (opciones[i].equals(seleccion)) {

                indiceSeleccionado = i;

                break;
            }
        }

        if (indiceSeleccionado == -1) {
            return;
        }

        Pedido pedidoSeleccionado =
                pedidos.get(
                        indiceSeleccionado
                );

        String nombre =
                JOptionPane.showInputDialog(
                        this,
                        "Nombre del repartidor:"
                );

        if (nombre == null
                || nombre.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nombre del repartidor.",
                    "Dato inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Repartidor repartidor =
                new Repartidor(
                        nombre.trim()
                );

        int idRepartidor =
                repartidorDAO.guardar(
                        repartidor
                );

        if (idRepartidor == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el repartidor.",
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Entrega entrega =
                new Entrega(
                        pedidoSeleccionado.getIdPedido(),
                        idRepartidor,
                        LocalDate.now(),
                        LocalTime.now()
                );

        boolean entregaGuardada =
                entregaDAO.guardar(entrega);

        if (!entregaGuardada) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar la entrega.",
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        pedidoSeleccionado
                .asignarRepartidor(
                        nombre.trim()
                );

        JOptionPane.showMessageDialog(
                this,
                "Pedido #"
                        + pedidoSeleccionado.getIdPedido()
                        + "\nTipo: "
                        + pedidoSeleccionado.getTipo()
                        + "\nRepartidor: "
                        + nombre.trim()
                        + "\n\nEntrega registrada en MySQL correctamente.",
                "SpeedFast",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}