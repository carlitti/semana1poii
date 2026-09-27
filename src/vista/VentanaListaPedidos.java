package vista;

import dao.PedidoDAO;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;

    private final PedidoDAO pedidoDAO;

    public VentanaListaPedidos() {

        pedidoDAO = new PedidoDAO();

        setTitle(
                "SpeedFast - Lista de Pedidos"
        );

        setSize(650, 400);

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

        JLabel titulo = new JLabel(
                "PEDIDOS REGISTRADOS EN MYSQL",
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        add(
                titulo,
                BorderLayout.NORTH
        );

        String[] columnas = {

                "ID",
                "Dirección",
                "Tipo",
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
                            int column) {

                        return false;
                    }
                };

        tablaPedidos =
                new JTable(modeloTabla);

        tablaPedidos.setRowHeight(25);

        JScrollPane scroll =
                new JScrollPane(tablaPedidos);

        add(
                scroll,
                BorderLayout.CENTER
        );

        JButton btnActualizar =
                new JButton(
                        "Actualizar desde MySQL"
                );

        btnActualizar.addActionListener(
                e -> actualizarTabla()
        );

        JPanel panelBoton =
                new JPanel();

        panelBoton.add(btnActualizar);

        add(
                panelBoton,
                BorderLayout.SOUTH
        );
    }

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        List<Pedido> pedidos =
                pedidoDAO.listarTodos();

        for (Pedido pedido : pedidos) {

            Object[] fila = {

                    pedido.getIdPedido(),

                    pedido.getDireccionEntrega(),

                    pedido.getTipo(),

                    pedido.calcularTiempoEntrega()
                            + " min"
            };

            modeloTabla.addRow(fila);
        }
    }
}