package vista;

import modelo.GestorPedidos;
import modelo.Pedido;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final GestorPedidos gestorPedidos;

    public VentanaPrincipal() {

        gestorPedidos = new GestorPedidos();

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(500, 400);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        inicializarComponentes();
    }

    private void inicializarComponentes() {

        JPanel panelPrincipal =
                new JPanel(new BorderLayout(20, 20));

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        40,
                        30,
                        40
                )
        );

        JLabel titulo = new JLabel(
                "SPEEDFAST",
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        JLabel subtitulo = new JLabel(
                "Gestión de Entregas",
                SwingConstants.CENTER
        );

        subtitulo.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        JPanel encabezado =
                new JPanel(new GridLayout(2, 1));

        encabezado.add(titulo);
        encabezado.add(subtitulo);

        panelPrincipal.add(
                encabezado,
                BorderLayout.NORTH
        );

        JPanel panelBotones =
                new JPanel(
                        new GridLayout(3, 1, 10, 15)
                );

        JButton btnRegistrar =
                new JButton("Registrar Pedido");

        JButton btnListar =
                new JButton("Listar Pedidos");

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

        btnRegistrar.addActionListener(e -> {

            VentanaRegistroPedido ventana =
                    new VentanaRegistroPedido(
                            gestorPedidos
                    );

            ventana.setVisible(true);
        });

        btnListar.addActionListener(e -> {

            VentanaListaPedidos ventana =
                    new VentanaListaPedidos(
                            gestorPedidos
                    );

            ventana.setVisible(true);
        });

        btnEntrega.addActionListener(
                e -> iniciarEntrega()
        );

        add(panelPrincipal);
    }

    private void iniciarEntrega() {

        if (gestorPedidos.estaVacio()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No existen pedidos registrados.",
                    "SpeedFast",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String[] pedidos =
                new String[
                        gestorPedidos.getPedidos().size()
                        ];

        for (int i = 0;
             i < gestorPedidos.getPedidos().size();
             i++) {

            Pedido pedido =
                    gestorPedidos.getPedidos().get(i);

            pedidos[i] =
                    "#" + pedido.getIdPedido()
                            + " - "
                            + pedido.getTipo()
                            + " - "
                            + pedido.getDireccionEntrega();
        }

        String seleccion =
                (String) JOptionPane.showInputDialog(
                        this,
                        "Seleccione el pedido:",
                        "Asignar Repartidor",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        pedidos,
                        pedidos[0]
                );

        if (seleccion == null) {
            return;
        }

        String nombre =
                JOptionPane.showInputDialog(
                        this,
                        "Nombre del repartidor:"
                );

        if (nombre == null ||
                nombre.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nombre del repartidor.",
                    "Dato inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int indice = 0;

        for (int i = 0;
             i < pedidos.length;
             i++) {

            if (pedidos[i].equals(seleccion)) {

                indice = i;
                break;
            }
        }

        Pedido pedidoSeleccionado =
                gestorPedidos
                        .getPedidos()
                        .get(indice);

        pedidoSeleccionado
                .asignarRepartidor(nombre.trim());

        JOptionPane.showMessageDialog(
                this,
                "Pedido #" +
                        pedidoSeleccionado.getIdPedido()
                        + "\nTipo: "
                        + pedidoSeleccionado.getTipo()
                        + "\nRepartidor: "
                        + nombre.trim()
                        + "\n\nEntrega iniciada correctamente.",
                "SpeedFast",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}