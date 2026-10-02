package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {

        setTitle(
                "SpeedFast - Sistema de Gestión"
        );

        setSize(
                520,
                450
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        inicializarComponentes();
    }


    private void inicializarComponentes() {

        JPanel panelPrincipal =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        40,
                        30,
                        40
                )
        );


        // =========================
        // ENCABEZADO
        // =========================

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
                        "Sistema de Gestión - MySQL",
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
                        new GridLayout(
                                2,
                                1
                        )
                );

        encabezado.add(titulo);
        encabezado.add(subtitulo);


        panelPrincipal.add(
                encabezado,
                BorderLayout.NORTH
        );


        // =========================
        // BOTONES
        // =========================

        JPanel panelBotones =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                10,
                                15
                        )
                );


        JButton btnRegistrarPedido =
                new JButton(
                        "Registrar Pedido"
                );


        JButton btnPedidos =
                new JButton(
                        "Gestionar Pedidos"
                );


        JButton btnRepartidores =
                new JButton(
                        "Gestionar Repartidores"
                );


        JButton btnEntregas =
                new JButton(
                        "Gestionar Entregas"
                );


        panelBotones.add(
                btnRegistrarPedido
        );

        panelBotones.add(
                btnPedidos
        );

        panelBotones.add(
                btnRepartidores
        );

        panelBotones.add(
                btnEntregas
        );


        panelPrincipal.add(
                panelBotones,
                BorderLayout.CENTER
        );


        // =========================
        // EVENTOS
        // =========================

        btnRegistrarPedido.addActionListener(
                e -> {

                    VentanaRegistroPedido ventana =
                            new VentanaRegistroPedido();

                    ventana.setVisible(true);
                }
        );


        btnPedidos.addActionListener(
                e -> {

                    VentanaListaPedidos ventana =
                            new VentanaListaPedidos();

                    ventana.setVisible(true);
                }
        );


        btnRepartidores.addActionListener(
                e -> {

                    VentanaRepartidores ventana =
                            new VentanaRepartidores();

                    ventana.setVisible(true);
                }
        );


        btnEntregas.addActionListener(
                e -> {

                    VentanaEntregas ventana =
                            new VentanaEntregas();

                    ventana.setVisible(true);
                }
        );


        add(panelPrincipal);
    }
}