package dao;

import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // CREATE
    public boolean guardar(Pedido pedido) {

        String sql = """
                INSERT INTO pedido (direccion, tipo, estado)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement =
                        conexion.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            statement.setString(
                    1,
                    pedido.getDireccionEntrega()
            );

            statement.setString(
                    2,
                    pedido.getTipo().toUpperCase()
            );

            statement.setString(
                    3,
                    pedido.getEstado().name()
            );

            int filasAfectadas =
                    statement.executeUpdate();

            // Recuperamos el ID generado por MySQL
            try (ResultSet claves = statement.getGeneratedKeys()) {

                if (claves.next()) {

                    int idGenerado =
                            claves.getInt(1);

                    pedido.setIdPedido(
                            idGenerado
                    );
                }
            }

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar pedido: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // READ
    public List<Pedido> listarTodos() {

        List<Pedido> pedidos =
                new ArrayList<>();

        String sql = """
                SELECT id, direccion, tipo, estado
                FROM pedido
                ORDER BY id
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement statement =
                        conexion.prepareStatement(sql);

                ResultSet resultado =
                        statement.executeQuery()
        ) {

            while (resultado.next()) {

                int id =
                        resultado.getInt("id");

                String direccion =
                        resultado.getString("direccion");

                String tipo =
                        resultado.getString("tipo");

                String estado =
                        resultado.getString("estado");

                /*
                 * La tabla entregada para esta actividad
                 * no almacena distancia.
                 */
                double distanciaKm = 0;

                Pedido pedido;

                switch (tipo.toUpperCase()) {

                    case "COMIDA":

                        pedido = new PedidoComida(
                                id,
                                direccion,
                                distanciaKm
                        );

                        break;


                    case "ENCOMIENDA":

                        pedido = new PedidoEncomienda(
                                id,
                                direccion,
                                distanciaKm
                        );

                        break;


                    case "EXPRESS":

                        pedido = new PedidoExpress(
                                id,
                                direccion,
                                distanciaKm
                        );

                        break;


                    default:

                        System.out.println(
                                "Tipo de pedido desconocido: "
                                        + tipo
                        );

                        continue;
                }

                // Recuperamos también el estado guardado en MySQL
                pedido.setEstado(
                        EstadoPedido.valueOf(
                                estado.toUpperCase()
                        )
                );

                pedidos.add(pedido);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar pedidos: "
                            + e.getMessage()
            );
        }

        return pedidos;
    }


    // UPDATE
    public boolean actualizar(Pedido pedido) {

        String sql = """
                UPDATE pedido
                SET direccion = ?,
                    tipo = ?,
                    estado = ?
                WHERE id = ?
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement statement =
                        conexion.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    pedido.getDireccionEntrega()
            );

            statement.setString(
                    2,
                    pedido.getTipo().toUpperCase()
            );

            statement.setString(
                    3,
                    pedido.getEstado().name()
            );

            statement.setInt(
                    4,
                    pedido.getIdPedido()
            );

            int filasAfectadas =
                    statement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar pedido: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // DELETE
    public boolean eliminar(int id) {

        String sql = """
                DELETE FROM pedido
                WHERE id = ?
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement statement =
                        conexion.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    id
            );

            int filasAfectadas =
                    statement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al eliminar pedido: "
                            + e.getMessage()
            );

            return false;
        }
    }
}