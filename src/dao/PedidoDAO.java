package dao;

import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public boolean guardar(Pedido pedido) {

        String sql = """
                INSERT INTO pedido (id, direccion, tipo, estado)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement = conexion.prepareStatement(sql)
        ) {

            statement.setInt(1, pedido.getIdPedido());
            statement.setString(2, pedido.getDireccionEntrega());
            statement.setString(3, pedido.getTipo().toUpperCase());
            statement.setString(4, "PENDIENTE");

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar pedido: " + e.getMessage()
            );

            return false;
        }
    }

    public List<Pedido> listarTodos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = """
                SELECT id, direccion, tipo, estado
                FROM pedido
                ORDER BY id
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement = conexion.prepareStatement(sql);
                ResultSet resultado = statement.executeQuery()
        ) {

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String direccion = resultado.getString("direccion");
                String tipo = resultado.getString("tipo");

                // La tabla de Semana 7 no almacena distancia.
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
                                "Tipo de pedido desconocido: " + tipo
                        );

                        continue;
                }

                pedidos.add(pedido);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar pedidos: " + e.getMessage()
            );
        }

        return pedidos;
    }
}