package vista;

import dao.PedidoDAO;
import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;

    private JComboBox<String> comboFiltroTipo;
    private JComboBox<String> comboFiltroEstado;

    private final PedidoDAO pedidoDAO;

    private List<Pedido> pedidosMostrados =
            new ArrayList<>();


    public VentanaListaPedidos() {

        pedidoDAO = new PedidoDAO();

        setTitle(
                "SpeedFast - Gestión de Pedidos"
        );

        setSize(
                850,
                500
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        inicializarComponentes();

        actualizarTabla();
    }


    private void inicializarComponentes() {

        setLayout(
                new BorderLayout(10, 10)
        );


        // =========================
        // PARTE SUPERIOR
        // =========================

        JPanel panelSuperior =
                new JPanel(
                        new BorderLayout()
                );


        JLabel titulo =
                new JLabel(
                        "GESTIÓN DE PEDIDOS",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        panelSuperior.add(
                titulo,
                BorderLayout.NORTH
        );


        // =========================
        // FILTROS
        // =========================

        JPanel panelFiltros =
                new JPanel();

        panelFiltros.add(
                new JLabel("Tipo:")
        );

        comboFiltroTipo =
                new JComboBox<>(
                        new String[]{
                                "TODOS",
                                "COMIDA",
                                "ENCOMIENDA",
                                "EXPRESS"
                        }
                );

        panelFiltros.add(
                comboFiltroTipo
        );


        panelFiltros.add(
                new JLabel("Estado:")
        );

        comboFiltroEstado =
                new JComboBox<>(
                        new String[]{
                                "TODOS",
                                "PENDIENTE",
                                "EN_REPARTO",
                                "ENTREGADO"
                        }
                );

        panelFiltros.add(
                comboFiltroEstado
        );


        JButton btnFiltrar =
                new JButton("Filtrar");

        JButton btnQuitarFiltros =
                new JButton("Quitar filtros");


        panelFiltros.add(
                btnFiltrar
        );

        panelFiltros.add(
                btnQuitarFiltros
        );


        panelSuperior.add(
                panelFiltros,
                BorderLayout.SOUTH
        );


        add(
                panelSuperior,
                BorderLayout.NORTH
        );


        // =========================
        // TABLA
        // =========================

        String[] columnas = {

                "ID",
                "Dirección",
                "Tipo",
                "Estado",
                "Tiempo estimado"
        };


        modeloTabla =
                new DefaultTableModel(
                        columnas,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };


        tablaPedidos =
                new JTable(
                        modeloTabla
                );

        tablaPedidos.setRowHeight(
                25
        );

        tablaPedidos.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        JScrollPane scroll =
                new JScrollPane(
                        tablaPedidos
                );


        add(
                scroll,
                BorderLayout.CENTER
        );


        // =========================
        // BOTONES
        // =========================

        JButton btnActualizar =
                new JButton(
                        "Actualizar"
                );


        JButton btnEditar =
                new JButton(
                        "Editar"
                );


        JButton btnEliminar =
                new JButton(
                        "Eliminar"
                );


        JPanel panelBotones =
                new JPanel();


        panelBotones.add(
                btnActualizar
        );

        panelBotones.add(
                btnEditar
        );

        panelBotones.add(
                btnEliminar
        );


        add(
                panelBotones,
                BorderLayout.SOUTH
        );


        // =========================
        // EVENTOS
        // =========================

        btnActualizar.addActionListener(
                e -> actualizarTabla()
        );


        btnEditar.addActionListener(
                e -> editarPedido()
        );


        btnEliminar.addActionListener(
                e -> eliminarPedido()
        );


        btnFiltrar.addActionListener(
                e -> aplicarFiltros()
        );


        btnQuitarFiltros.addActionListener(
                e -> {

                    comboFiltroTipo.setSelectedIndex(0);

                    comboFiltroEstado.setSelectedIndex(0);

                    actualizarTabla();
                }
        );
    }


    // =========================
    // CARGAR TODOS
    // =========================

    private void actualizarTabla() {

        pedidosMostrados =
                pedidoDAO.listarTodos();

        mostrarPedidos(
                pedidosMostrados
        );
    }


    // =========================
    // MOSTRAR EN JTABLE
    // =========================

    private void mostrarPedidos(
            List<Pedido> pedidos
    ) {

        modeloTabla.setRowCount(0);


        for (Pedido pedido : pedidos) {

            Object[] fila = {

                    pedido.getIdPedido(),

                    pedido.getDireccionEntrega(),

                    pedido.getTipo(),

                    pedido.getEstado(),

                    pedido.calcularTiempoEntrega()
                            + " min"
            };


            modeloTabla.addRow(
                    fila
            );
        }
    }


    // =========================
    // FILTROS
    // =========================

    private void aplicarFiltros() {

        String tipoSeleccionado =
                comboFiltroTipo
                        .getSelectedItem()
                        .toString();


        String estadoSeleccionado =
                comboFiltroEstado
                        .getSelectedItem()
                        .toString();


        List<Pedido> todos =
                pedidoDAO.listarTodos();


        pedidosMostrados =
                new ArrayList<>();


        for (Pedido pedido : todos) {

            boolean coincideTipo =
                    tipoSeleccionado.equals("TODOS")
                            ||
                            pedido.getTipo()
                                    .equalsIgnoreCase(
                                            tipoSeleccionado
                                    );


            boolean coincideEstado =
                    estadoSeleccionado.equals("TODOS")
                            ||
                            pedido.getEstado()
                                    .name()
                                    .equals(
                                            estadoSeleccionado
                                    );


            if (coincideTipo
                    && coincideEstado) {

                pedidosMostrados.add(
                        pedido
                );
            }
        }


        mostrarPedidos(
                pedidosMostrados
        );
    }


    // =========================
    // EDITAR
    // =========================

    private void editarPedido() {

        int filaSeleccionada =
                tablaPedidos.getSelectedRow();


        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido.",
                    "Editar pedido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        Pedido pedidoActual =
                pedidosMostrados.get(
                        filaSeleccionada
                );


        JTextField txtDireccion =
                new JTextField(
                        pedidoActual
                                .getDireccionEntrega()
                );


        JComboBox<String> comboTipo =
                new JComboBox<>(
                        new String[]{
                                "Comida",
                                "Encomienda",
                                "Express"
                        }
                );


        comboTipo.setSelectedItem(
                pedidoActual.getTipo()
        );


        JComboBox<EstadoPedido> comboEstado =
                new JComboBox<>(
                        EstadoPedido.values()
                );


        comboEstado.setSelectedItem(
                pedidoActual.getEstado()
        );


        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );


        panel.add(
                new JLabel("Dirección:")
        );

        panel.add(
                txtDireccion
        );


        panel.add(
                new JLabel("Tipo:")
        );

        panel.add(
                comboTipo
        );


        panel.add(
                new JLabel("Estado:")
        );

        panel.add(
                comboEstado
        );


        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Editar pedido #"
                                + pedidoActual.getIdPedido(),
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );


        if (opcion
                != JOptionPane.OK_OPTION) {

            return;
        }


        String direccion =
                txtDireccion
                        .getText()
                        .trim();


        if (direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "La dirección no puede estar vacía.",
                    "Dato inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        if (direccion.length() > 150) {

            JOptionPane.showMessageDialog(
                    this,
                    "La dirección no puede superar 150 caracteres.",
                    "Dato inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        String tipo =
                comboTipo
                        .getSelectedItem()
                        .toString();


        EstadoPedido estado =
                (EstadoPedido)
                        comboEstado
                                .getSelectedItem();


        Pedido pedidoModificado;


        switch (tipo) {

            case "Comida":

                pedidoModificado =
                        new PedidoComida(
                                pedidoActual.getIdPedido(),
                                direccion,
                                pedidoActual.getDistanciaKm()
                        );

                break;


            case "Encomienda":

                pedidoModificado =
                        new PedidoEncomienda(
                                pedidoActual.getIdPedido(),
                                direccion,
                                pedidoActual.getDistanciaKm()
                        );

                break;


            case "Express":

                pedidoModificado =
                        new PedidoExpress(
                                pedidoActual.getIdPedido(),
                                direccion,
                                pedidoActual.getDistanciaKm()
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


        pedidoModificado.setEstado(
                estado
        );


        boolean actualizado =
                pedidoDAO.actualizar(
                        pedidoModificado
                );


        if (actualizado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido actualizado correctamente.",
                    "SpeedFast",
                    JOptionPane.INFORMATION_MESSAGE
            );

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar el pedido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================
    // ELIMINAR
    // =========================

    private void eliminarPedido() {

        int filaSeleccionada =
                tablaPedidos.getSelectedRow();


        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido.",
                    "Eliminar pedido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        Pedido pedido =
                pedidosMostrados.get(
                        filaSeleccionada
                );


        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar el pedido #"
                                + pedido.getIdPedido()
                                + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );


        if (opcion
                != JOptionPane.YES_OPTION) {

            return;
        }


        boolean eliminado =
                pedidoDAO.eliminar(
                        pedido.getIdPedido()
                );


        if (eliminado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido eliminado correctamente.",
                    "SpeedFast",
                    JOptionPane.INFORMATION_MESSAGE
            );

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar el pedido.\n"
                            + "Puede estar asociado a una entrega.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}