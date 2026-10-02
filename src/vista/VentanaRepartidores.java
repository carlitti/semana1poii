package vista;

import dao.RepartidorDAO;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaRepartidores extends JFrame {

    private JTextField txtNombre;
    private JTable tablaRepartidores;
    private DefaultTableModel modeloTabla;

    private final RepartidorDAO repartidorDAO;

    private List<Repartidor> repartidores;

    public VentanaRepartidores() {

        repartidorDAO = new RepartidorDAO();

        setTitle("SpeedFast - Gestión de Repartidores");

        setSize(650, 450);

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
        // TÍTULO
        // =========================

        JLabel titulo =
                new JLabel(
                        "GESTIÓN DE REPARTIDORES",
                        SwingConstants.CENTER
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        add(
                titulo,
                BorderLayout.NORTH
        );


        // =========================
        // CENTRO
        // =========================

        JPanel panelCentro =
                new JPanel(
                        new BorderLayout(10, 10)
                );


        // Formulario

        JPanel panelFormulario =
                new JPanel();

        panelFormulario.add(
                new JLabel("Nombre:")
        );

        txtNombre =
                new JTextField(20);

        panelFormulario.add(
                txtNombre
        );


        JButton btnRegistrar =
                new JButton(
                        "Registrar"
                );

        panelFormulario.add(
                btnRegistrar
        );


        panelCentro.add(
                panelFormulario,
                BorderLayout.NORTH
        );


        // =========================
        // TABLA
        // =========================

        modeloTabla =
                new DefaultTableModel(
                        new String[]{
                                "ID",
                                "Nombre"
                        },
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


        tablaRepartidores =
                new JTable(
                        modeloTabla
                );

        tablaRepartidores.setRowHeight(
                25
        );

        tablaRepartidores.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        panelCentro.add(
                new JScrollPane(
                        tablaRepartidores
                ),
                BorderLayout.CENTER
        );


        add(
                panelCentro,
                BorderLayout.CENTER
        );


        // =========================
        // BOTONES INFERIORES
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

        btnRegistrar.addActionListener(
                e -> registrarRepartidor()
        );


        btnActualizar.addActionListener(
                e -> actualizarTabla()
        );


        btnEditar.addActionListener(
                e -> editarRepartidor()
        );


        btnEliminar.addActionListener(
                e -> eliminarRepartidor()
        );
    }


    // =========================
    // REGISTRAR
    // =========================

    private void registrarRepartidor() {

        String nombre =
                txtNombre
                        .getText()
                        .trim();


        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un nombre.",
                    "Dato inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        if (nombre.length() > 100) {

            JOptionPane.showMessageDialog(
                    this,
                    "El nombre no puede superar 100 caracteres.",
                    "Dato inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        Repartidor repartidor =
                new Repartidor(
                        nombre
                );


        int idGenerado =
                repartidorDAO.guardar(
                        repartidor
                );


        if (idGenerado != -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor registrado correctamente.\n"
                            + "ID generado: "
                            + idGenerado,
                    "SpeedFast",
                    JOptionPane.INFORMATION_MESSAGE
            );

            txtNombre.setText("");

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================
    // LISTAR
    // =========================

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        repartidores =
                repartidorDAO.listarTodos();


        for (Repartidor repartidor : repartidores) {

            modeloTabla.addRow(
                    new Object[]{
                            repartidor.getId(),
                            repartidor.getNombre()
                    }
            );
        }
    }


    // =========================
    // EDITAR
    // =========================

    private void editarRepartidor() {

        int fila =
                tablaRepartidores
                        .getSelectedRow();


        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un repartidor.",
                    "Editar",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        Repartidor repartidor =
                repartidores.get(
                        fila
                );


        String nuevoNombre =
                JOptionPane.showInputDialog(
                        this,
                        "Nuevo nombre:",
                        repartidor.getNombre()
                );


        if (nuevoNombre == null) {

            return;
        }


        nuevoNombre =
                nuevoNombre.trim();


        if (nuevoNombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "El nombre no puede estar vacío.",
                    "Dato inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        if (nuevoNombre.length() > 100) {

            JOptionPane.showMessageDialog(
                    this,
                    "El nombre no puede superar 100 caracteres.",
                    "Dato inválido",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        repartidor.setNombre(
                nuevoNombre
        );


        boolean actualizado =
                repartidorDAO.actualizar(
                        repartidor
                );


        if (actualizado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor actualizado correctamente.",
                    "SpeedFast",
                    JOptionPane.INFORMATION_MESSAGE
            );

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar el repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================
    // ELIMINAR
    // =========================

    private void eliminarRepartidor() {

        int fila =
                tablaRepartidores
                        .getSelectedRow();


        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un repartidor.",
                    "Eliminar",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        Repartidor repartidor =
                repartidores.get(
                        fila
                );


        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de eliminar al repartidor "
                                + repartidor.getNombre()
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
                repartidorDAO.eliminar(
                        repartidor.getId()
                );


        if (eliminado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor eliminado correctamente.",
                    "SpeedFast",
                    JOptionPane.INFORMATION_MESSAGE
            );

            actualizarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar el repartidor.\n"
                            + "Puede estar asociado a una entrega.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}