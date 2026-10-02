package vista;

import dao.PedidoDAO;
import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private JTextField txtDireccion;

    private JComboBox<String> comboTipo;

    private JComboBox<EstadoPedido> comboEstado;

    private final PedidoDAO pedidoDAO;


    public VentanaRegistroPedido() {

        pedidoDAO = new PedidoDAO();

        setTitle(
                "SpeedFast - Registrar Pedido"
        );

        setSize(
                450,
                320
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        inicializarComponentes();
    }


    private void inicializarComponentes() {

        JPanel panelPrincipal =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );


        // =========================
        // TÍTULO
        // =========================

        JLabel titulo =
                new JLabel(
                        "REGISTRAR NUEVO PEDIDO",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        panelPrincipal.add(
                titulo,
                BorderLayout.NORTH
        );


        // =========================
        // FORMULARIO
        // =========================

        JPanel formulario =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                15
                        )
                );


        // Dirección

        formulario.add(
                new JLabel("Dirección:")
        );

        txtDireccion =
                new JTextField();

        formulario.add(
                txtDireccion
        );


        // Tipo

        formulario.add(
                new JLabel("Tipo:")
        );

        comboTipo =
                new JComboBox<>(
                        new String[]{
                                "Comida",
                                "Encomienda",
                                "Express"
                        }
                );

        formulario.add(
                comboTipo
        );


        // Estado

        formulario.add(
                new JLabel("Estado:")
        );

        comboEstado =
                new JComboBox<>(
                        EstadoPedido.values()
                );

        formulario.add(
                comboEstado
        );


        panelPrincipal.add(
                formulario,
                BorderLayout.CENTER
        );


        // =========================
        // BOTÓN
        // =========================

        JButton btnGuardar =
                new JButton(
                        "Guardar Pedido"
                );

        btnGuardar.addActionListener(
                e -> guardarPedido()
        );

        panelPrincipal.add(
                btnGuardar,
                BorderLayout.SOUTH
        );


        add(
                panelPrincipal
        );
    }


    private void guardarPedido() {

        String direccion =
                txtDireccion
                        .getText()
                        .trim();


        // =========================
        // VALIDACIÓN
        // =========================

        if (direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar una dirección.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            txtDireccion.requestFocus();

            return;
        }


        if (direccion.length() > 150) {

            JOptionPane.showMessageDialog(
                    this,
                    "La dirección no puede superar los 150 caracteres.",
                    "Dirección inválida",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        String tipo =
                (String)
                        comboTipo.getSelectedItem();


        EstadoPedido estado =
                (EstadoPedido)
                        comboEstado.getSelectedItem();


        /*
         * La base de datos no almacena distancia.
         * Se utiliza 0 para conservar la jerarquía
         * existente de Pedido.
         */

        double distanciaKm = 0;


        Pedido pedido;


        // =========================
        // CREAR OBJETO
        // =========================

        switch (tipo) {

            case "Comida":

                pedido =
                        new PedidoComida(
                                0,
                                direccion,
                                distanciaKm
                        );

                break;


            case "Encomienda":

                pedido =
                        new PedidoEncomienda(
                                0,
                                direccion,
                                distanciaKm
                        );

                break;


            case "Express":

                pedido =
                        new PedidoExpress(
                                0,
                                direccion,
                                distanciaKm
                        );

                break;


            default:

                JOptionPane.showMessageDialog(
                        this,
                        "Tipo de pedido inválido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
        }


        pedido.setEstado(
                estado
        );


        // =========================
        // GUARDAR EN MYSQL
        // =========================

        boolean guardado =
                pedidoDAO.guardar(
                        pedido
                );


        if (guardado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido registrado correctamente."
                            + "\nID generado: "
                            + pedido.getIdPedido(),
                    "SpeedFast",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el pedido.",
                    "Error de base de datos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void limpiarCampos() {

        txtDireccion.setText("");

        comboTipo.setSelectedIndex(0);

        comboEstado.setSelectedItem(
                EstadoPedido.PENDIENTE
        );

        txtDireccion.requestFocus();
    }
}