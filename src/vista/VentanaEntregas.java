package vista;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;

import modelo.Entrega;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class VentanaEntregas extends JFrame {

    private final EntregaDAO entregaDAO;
    private final PedidoDAO pedidoDAO;
    private final RepartidorDAO repartidorDAO;

    private JComboBox<Pedido> comboPedido;
    private JComboBox<Repartidor> comboRepartidor;

    private JTextField txtFecha;
    private JTextField txtHora;

    private JTable tablaEntregas;
    private DefaultTableModel modeloTabla;

    private JComboBox<String> comboFiltroPedido;
    private JComboBox<String> comboFiltroRepartidor;

    private List<Pedido> pedidos;
    private List<Repartidor> repartidores;
    private List<Entrega> entregasMostradas;


    public VentanaEntregas() {

        entregaDAO = new EntregaDAO();
        pedidoDAO = new PedidoDAO();
        repartidorDAO = new RepartidorDAO();

        pedidos = new ArrayList<>();
        repartidores = new ArrayList<>();
        entregasMostradas = new ArrayList<>();

        setTitle(
                "SpeedFast - Gestión de Entregas"
        );

        setSize(
                1000,
                650
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        inicializarComponentes();

        cargarCombos();

        actualizarTabla();
    }


    // ==================================================
    // INTERFAZ
    // ==================================================

    private void inicializarComponentes() {

        setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );


        // ==================================================
        // PANEL SUPERIOR
        // ==================================================

        JPanel panelSuperior =
                new JPanel();

        panelSuperior.setLayout(
                new BoxLayout(
                        panelSuperior,
                        BoxLayout.Y_AXIS
                )
        );


        // ==================================================
        // TÍTULO
        // ==================================================

        JLabel titulo =
                new JLabel(
                        "GESTIÓN DE ENTREGAS",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        panelSuperior.add(
                titulo
        );


        // ==================================================
        // FORMULARIO
        // ==================================================

        JPanel panelFormulario =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );


        // PEDIDO

        panelFormulario.add(
                new JLabel("Pedido:")
        );

        comboPedido =
                new JComboBox<>();

        comboPedido.setPreferredSize(
                new Dimension(
                        260,
                        30
                )
        );

        panelFormulario.add(
                comboPedido
        );


        // REPARTIDOR

        panelFormulario.add(
                new JLabel("Repartidor:")
        );

        comboRepartidor =
                new JComboBox<>();

        comboRepartidor.setPreferredSize(
                new Dimension(
                        180,
                        30
                )
        );

        panelFormulario.add(
                comboRepartidor
        );


        // FECHA

        panelFormulario.add(
                new JLabel("Fecha:")
        );

        txtFecha =
                new JTextField(
                        10
                );

        panelFormulario.add(
                txtFecha
        );


        // HORA

        panelFormulario.add(
                new JLabel("Hora:")
        );

        txtHora =
                new JTextField(
                        6
                );

        panelFormulario.add(
                txtHora
        );


        panelSuperior.add(
                panelFormulario
        );


        // ==================================================
        // BOTÓN REGISTRAR
        // ==================================================

        JPanel panelRegistrar =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        JButton btnRegistrar =
                new JButton(
                        "Registrar Entrega"
                );

        panelRegistrar.add(
                btnRegistrar
        );

        panelSuperior.add(
                panelRegistrar
        );


        // ==================================================
        // FILTROS
        // ==================================================

        JPanel panelFiltros =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );

        panelFiltros.setBorder(
                BorderFactory.createTitledBorder(
                        "Filtros"
                )
        );


        // FILTRO PEDIDO

        panelFiltros.add(
                new JLabel(
                        "Pedido:"
                )
        );

        comboFiltroPedido =
                new JComboBox<>();

        comboFiltroPedido.setPreferredSize(
                new Dimension(
                        230,
                        30
                )
        );

        panelFiltros.add(
                comboFiltroPedido
        );


        // FILTRO REPARTIDOR

        panelFiltros.add(
                new JLabel(
                        "Repartidor:"
                )
        );

        comboFiltroRepartidor =
                new JComboBox<>();

        comboFiltroRepartidor.setPreferredSize(
                new Dimension(
                        190,
                        30
                )
        );

        panelFiltros.add(
                comboFiltroRepartidor
        );


        JButton btnFiltrar =
                new JButton(
                        "Filtrar"
                );

        JButton btnQuitarFiltros =
                new JButton(
                        "Quitar filtros"
                );

        panelFiltros.add(
                btnFiltrar
        );

        panelFiltros.add(
                btnQuitarFiltros
        );

        panelSuperior.add(
                panelFiltros
        );


        add(
                panelSuperior,
                BorderLayout.NORTH
        );


        // ==================================================
        // TABLA
        // ==================================================

        String[] columnas = {

                "ID",
                "Pedido",
                "Repartidor",
                "Fecha",
                "Hora"
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


        tablaEntregas =
                new JTable(
                        modeloTabla
                );

        tablaEntregas.setRowHeight(
                25
        );

        tablaEntregas.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        JScrollPane scroll =
                new JScrollPane(
                        tablaEntregas
                );


        add(
                scroll,
                BorderLayout.CENTER
        );


        // ==================================================
        // BOTONES INFERIORES
        // ==================================================

        JPanel panelBotones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                10
                        )
                );


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


        JButton btnLimpiar =
                new JButton(
                        "Limpiar"
                );


        panelBotones.add(
                btnActualizar
        );

        panelBotones.add(
                btnEditar
        );

        panelBotones.add(
                btnEliminar
        );

        panelBotones.add(
                btnLimpiar
        );


        add(
                panelBotones,
                BorderLayout.SOUTH
        );


        // ==================================================
        // EVENTOS
        // ==================================================

        btnRegistrar.addActionListener(
                e -> registrarEntrega()
        );


        btnActualizar.addActionListener(
                e -> {

                    cargarCombos();

                    actualizarTabla();
                }
        );


        btnEditar.addActionListener(
                e -> editarEntrega()
        );


        btnEliminar.addActionListener(
                e -> eliminarEntrega()
        );


        btnLimpiar.addActionListener(
                e -> limpiarFormulario()
        );


        btnFiltrar.addActionListener(
                e -> aplicarFiltros()
        );


        btnQuitarFiltros.addActionListener(
                e -> {

                    comboFiltroPedido.setSelectedIndex(
                            0
                    );

                    comboFiltroRepartidor.setSelectedIndex(
                            0
                    );

                    actualizarTabla();
                }
        );


        tablaEntregas
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                cargarEntregaSeleccionada();
                            }
                        }
                );
    }


    // ==================================================
    // CARGAR COMBOS
    // ==================================================

    private void cargarCombos() {

        pedidos =
                pedidoDAO.listarTodos();

        repartidores =
                repartidorDAO.listarTodos();


        // =========================
        // PEDIDOS
        // =========================

        comboPedido.removeAllItems();

        for (Pedido pedido : pedidos) {

            comboPedido.addItem(
                    pedido
            );
        }


        // =========================
        // REPARTIDORES
        // =========================

        comboRepartidor.removeAllItems();

        for (Repartidor repartidor : repartidores) {

            comboRepartidor.addItem(
                    repartidor
            );
        }


        // =========================
        // FILTRO DE PEDIDOS
        // =========================

        comboFiltroPedido.removeAllItems();

        comboFiltroPedido.addItem(
                "TODOS"
        );

        for (Pedido pedido : pedidos) {

            comboFiltroPedido.addItem(
                    pedido.toString()
            );
        }


        // =========================
        // FILTRO DE REPARTIDORES
        // =========================

        comboFiltroRepartidor.removeAllItems();

        comboFiltroRepartidor.addItem(
                "TODOS"
        );

        for (Repartidor repartidor : repartidores) {

            comboFiltroRepartidor.addItem(
                    repartidor.toString()
            );
        }


        limpiarFormulario();
    }


    // ==================================================
    // REGISTRAR
    // ==================================================

    private void registrarEntrega() {

        Entrega entrega =
                obtenerEntregaFormulario();


        if (entrega == null) {

            return;
        }


        boolean guardada =
                entregaDAO.guardar(
                        entrega
                );


        if (guardada) {

            JOptionPane.showMessageDialog(
                    this,
                    "Entrega registrada correctamente.\n"
                            + "ID generado: "
                            + entrega.getId(),
                    "SpeedFast",
                    JOptionPane.INFORMATION_MESSAGE
            );


            limpiarFormulario();

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar la entrega.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==================================================
    // OBTENER DATOS DEL FORMULARIO
    // ==================================================

    private Entrega obtenerEntregaFormulario() {

        Pedido pedido =
                (Pedido)
                        comboPedido
                                .getSelectedItem();


        Repartidor repartidor =
                (Repartidor)
                        comboRepartidor
                                .getSelectedItem();


        if (pedido == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido.",
                    "Dato faltante",
                    JOptionPane.WARNING_MESSAGE
            );

            return null;
        }


        if (repartidor == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un repartidor.",
                    "Dato faltante",
                    JOptionPane.WARNING_MESSAGE
            );

            return null;
        }


        String fechaTexto =
                txtFecha
                        .getText()
                        .trim();


        String horaTexto =
                txtHora
                        .getText()
                        .trim();


        if (
                fechaTexto.isEmpty()
                        ||
                        horaTexto.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Fecha y hora son obligatorias.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE
            );

            return null;
        }


        try {

            LocalDate fecha =
                    LocalDate.parse(
                            fechaTexto
                    );


            LocalTime hora =
                    LocalTime.parse(
                            horaTexto
                    );


            return new Entrega(
                    pedido.getIdPedido(),
                    repartidor.getId(),
                    fecha,
                    hora
            );


        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Formato inválido.\n\n"
                            + "Fecha: AAAA-MM-DD\n"
                            + "Ejemplo: 2026-10-01\n\n"
                            + "Hora: HH:MM\n"
                            + "Ejemplo: 19:30",
                    "Formato inválido",
                    JOptionPane.WARNING_MESSAGE
            );


            return null;
        }
    }


    // ==================================================
    // LISTAR
    // ==================================================

    private void actualizarTabla() {

        entregasMostradas =
                entregaDAO.listarTodos();


        mostrarEntregas(
                entregasMostradas
        );
    }


    private void mostrarEntregas(
            List<Entrega> entregas
    ) {

        modeloTabla.setRowCount(
                0
        );


        for (Entrega entrega : entregas) {

            String pedido =
                    obtenerTextoPedido(
                            entrega.getIdPedido()
                    );


            String repartidor =
                    obtenerTextoRepartidor(
                            entrega.getIdRepartidor()
                    );


            modeloTabla.addRow(
                    new Object[]{

                            entrega.getId(),

                            pedido,

                            repartidor,

                            entrega.getFecha(),

                            entrega.getHora()
                    }
            );
        }
    }


    // ==================================================
    // EDITAR
    // ==================================================

    private void editarEntrega() {

        int fila =
                tablaEntregas
                        .getSelectedRow();


        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una entrega.",
                    "Editar entrega",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        Entrega entregaOriginal =
                entregasMostradas.get(
                        fila
                );


        Entrega nuevosDatos =
                obtenerEntregaFormulario();


        if (nuevosDatos == null) {

            return;
        }


        nuevosDatos.setId(
                entregaOriginal.getId()
        );


        boolean actualizada =
                entregaDAO.actualizar(
                        nuevosDatos
                );


        if (actualizada) {

            JOptionPane.showMessageDialog(
                    this,
                    "Entrega actualizada correctamente.",
                    "SpeedFast",
                    JOptionPane.INFORMATION_MESSAGE
            );


            limpiarFormulario();

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar la entrega.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==================================================
    // ELIMINAR
    // ==================================================

    private void eliminarEntrega() {

        int fila =
                tablaEntregas
                        .getSelectedRow();


        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una entrega.",
                    "Eliminar entrega",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        Entrega entrega =
                entregasMostradas.get(
                        fila
                );


        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar la entrega #"
                                + entrega.getId()
                                + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );


        if (
                opcion
                        != JOptionPane.YES_OPTION
        ) {

            return;
        }


        boolean eliminada =
                entregaDAO.eliminar(
                        entrega.getId()
                );


        if (eliminada) {

            JOptionPane.showMessageDialog(
                    this,
                    "Entrega eliminada correctamente.",
                    "SpeedFast",
                    JOptionPane.INFORMATION_MESSAGE
            );


            limpiarFormulario();

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar la entrega.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==================================================
    // SELECCIONAR ENTREGA EN LA TABLA
    // ==================================================

    private void cargarEntregaSeleccionada() {

        int fila =
                tablaEntregas
                        .getSelectedRow();


        if (fila == -1) {

            return;
        }


        Entrega entrega =
                entregasMostradas.get(
                        fila
                );


        seleccionarPedido(
                entrega.getIdPedido()
        );


        seleccionarRepartidor(
                entrega.getIdRepartidor()
        );


        txtFecha.setText(
                entrega.getFecha()
                        .toString()
        );


        txtHora.setText(
                entrega.getHora()
                        .toString()
        );
    }


    // ==================================================
    // SELECCIONAR PEDIDO
    // ==================================================

    private void seleccionarPedido(
            int idPedido
    ) {

        for (
                int i = 0;
                i < comboPedido.getItemCount();
                i++
        ) {

            Pedido pedido =
                    comboPedido.getItemAt(
                            i
                    );


            if (
                    pedido.getIdPedido()
                            == idPedido
            ) {

                comboPedido.setSelectedIndex(
                        i
                );

                return;
            }
        }
    }


    // ==================================================
    // SELECCIONAR REPARTIDOR
    // ==================================================

    private void seleccionarRepartidor(
            int idRepartidor
    ) {

        for (
                int i = 0;
                i < comboRepartidor.getItemCount();
                i++
        ) {

            Repartidor repartidor =
                    comboRepartidor.getItemAt(
                            i
                    );


            if (
                    repartidor.getId()
                            == idRepartidor
            ) {

                comboRepartidor.setSelectedIndex(
                        i
                );

                return;
            }
        }
    }


    // ==================================================
    // FILTROS
    // ==================================================

    private void aplicarFiltros() {

        int indicePedido =
                comboFiltroPedido
                        .getSelectedIndex();


        int indiceRepartidor =
                comboFiltroRepartidor
                        .getSelectedIndex();


        // SIN FILTROS

        if (
                indicePedido == 0
                        &&
                        indiceRepartidor == 0
        ) {

            actualizarTabla();

            return;
        }


        // SOLO PEDIDO

        if (
                indicePedido > 0
                        &&
                        indiceRepartidor == 0
        ) {

            Pedido pedido =
                    pedidos.get(
                            indicePedido - 1
                    );


            entregasMostradas =
                    entregaDAO.listarPorPedido(
                            pedido.getIdPedido()
                    );


            mostrarEntregas(
                    entregasMostradas
            );


            return;
        }


        // SOLO REPARTIDOR

        if (
                indicePedido == 0
                        &&
                        indiceRepartidor > 0
        ) {

            Repartidor repartidor =
                    repartidores.get(
                            indiceRepartidor - 1
                    );


            entregasMostradas =
                    entregaDAO.listarPorRepartidor(
                            repartidor.getId()
                    );


            mostrarEntregas(
                    entregasMostradas
            );


            return;
        }


        // PEDIDO Y REPARTIDOR

        Pedido pedido =
                pedidos.get(
                        indicePedido - 1
                );


        Repartidor repartidor =
                repartidores.get(
                        indiceRepartidor - 1
                );


        List<Entrega> porPedido =
                entregaDAO.listarPorPedido(
                        pedido.getIdPedido()
                );


        entregasMostradas =
                new ArrayList<>();


        for (Entrega entrega : porPedido) {

            if (
                    entrega.getIdRepartidor()
                            ==
                            repartidor.getId()
            ) {

                entregasMostradas.add(
                        entrega
                );
            }
        }


        mostrarEntregas(
                entregasMostradas
        );
    }


    // ==================================================
    // TEXTO DEL PEDIDO
    // ==================================================

    private String obtenerTextoPedido(
            int idPedido
    ) {

        for (Pedido pedido : pedidos) {

            if (
                    pedido.getIdPedido()
                            == idPedido
            ) {

                return pedido.toString();
            }
        }


        return "Pedido #" + idPedido;
    }


    // ==================================================
    // TEXTO DEL REPARTIDOR
    // ==================================================

    private String obtenerTextoRepartidor(
            int idRepartidor
    ) {

        for (
                Repartidor repartidor
                : repartidores
        ) {

            if (
                    repartidor.getId()
                            == idRepartidor
            ) {

                return repartidor.toString();
            }
        }


        return "Repartidor #" + idRepartidor;
    }


    // ==================================================
    // LIMPIAR FORMULARIO
    // ==================================================

    private void limpiarFormulario() {

        tablaEntregas.clearSelection();


        if (
                comboPedido.getItemCount()
                        > 0
        ) {

            comboPedido.setSelectedIndex(
                    0
            );
        }


        if (
                comboRepartidor.getItemCount()
                        > 0
        ) {

            comboRepartidor.setSelectedIndex(
                    0
            );
        }


        txtFecha.setText(
                LocalDate.now()
                        .toString()
        );


        txtHora.setText(
                LocalTime.now()
                        .withSecond(0)
                        .withNano(0)
                        .toString()
        );
    }
}