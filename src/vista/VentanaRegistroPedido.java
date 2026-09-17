package vista;

import modelo.GestorPedidos;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private final GestorPedidos gestorPedidos;

    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> comboTipo;

    public VentanaRegistroPedido(GestorPedidos gestorPedidos) {

        this.gestorPedidos = gestorPedidos;

        setTitle("SpeedFast - Registrar Pedido");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inicializarComponentes();
    }

    private void inicializarComponentes() {

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel titulo = new JLabel(
                "REGISTRAR NUEVO PEDIDO",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel formulario =
                new JPanel(new GridLayout(3, 2, 10, 15));

        formulario.add(new JLabel("ID:"));

        txtId = new JTextField();
        formulario.add(txtId);

        formulario.add(new JLabel("Dirección:"));

        txtDireccion = new JTextField();
        formulario.add(txtDireccion);

        formulario.add(new JLabel("Tipo:"));

        comboTipo = new JComboBox<>(
                new String[]{
                        "Comida",
                        "Encomienda",
                        "Express"
                }
        );

        formulario.add(comboTipo);

        panelPrincipal.add(formulario, BorderLayout.CENTER);

        JButton btnGuardar =
                new JButton("Guardar Pedido");

        btnGuardar.addActionListener(
                e -> guardarPedido()
        );

        panelPrincipal.add(
                btnGuardar,
                BorderLayout.SOUTH
        );

        add(panelPrincipal);
    }

    private void guardarPedido() {

        String textoId = txtId.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (textoId.isEmpty() || direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int id;

        try {

            id = Integer.parseInt(textoId);

            if (id <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El ID debe ser mayor que cero.",
                        "ID inválido",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un número entero.",
                    "ID inválido",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String tipo =
                (String) comboTipo.getSelectedItem();

        /*
         * La pauta de Semana 6 no solicita ingresar distancia,
         * por lo que se utiliza 0 km en los pedidos registrados
         * desde la interfaz gráfica.
         */
        double distanciaKm = 0;

        Pedido pedido;

        switch (tipo) {

            case "Comida":
                pedido = new PedidoComida(
                        id,
                        direccion,
                        distanciaKm
                );
                break;

            case "Encomienda":
                pedido = new PedidoEncomienda(
                        id,
                        direccion,
                        distanciaKm
                );
                break;

            case "Express":
                pedido = new PedidoExpress(
                        id,
                        direccion,
                        distanciaKm
                );
                break;

            default:
                JOptionPane.showMessageDialog(
                        this,
                        "Tipo de pedido inválido."
                );
                return;
        }

        boolean agregado =
                gestorPedidos.agregarPedido(pedido);

        if (!agregado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ya existe un pedido con el ID " + id + ".",
                    "ID duplicado",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Pedido registrado correctamente.",
                "SpeedFast",
                JOptionPane.INFORMATION_MESSAGE
        );

        limpiarCampos();
    }

    private void limpiarCampos() {

        txtId.setText("");
        txtDireccion.setText("");
        comboTipo.setSelectedIndex(0);
        txtId.requestFocus();
    }
}