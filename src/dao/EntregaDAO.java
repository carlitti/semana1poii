package dao;

import modelo.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {


    // =========================
    // CREATE
    // =========================

    public boolean guardar(Entrega entrega) {

        String sql = """
                INSERT INTO entrega
                (id_pedido, id_repartidor, fecha, hora)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement statement =
                        conexion.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            statement.setInt(
                    1,
                    entrega.getIdPedido()
            );

            statement.setInt(
                    2,
                    entrega.getIdRepartidor()
            );

            statement.setDate(
                    3,
                    Date.valueOf(
                            entrega.getFecha()
                    )
            );

            statement.setTime(
                    4,
                    Time.valueOf(
                            entrega.getHora()
                    )
            );

            int filasAfectadas =
                    statement.executeUpdate();


            // Recuperar ID generado
            try (ResultSet claves =
                         statement.getGeneratedKeys()) {

                if (claves.next()) {

                    entrega.setId(
                            claves.getInt(1)
                    );
                }
            }

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar entrega: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // =========================
    // READ
    // =========================

    public List<Entrega> listarTodos() {

        List<Entrega> entregas =
                new ArrayList<>();

        String sql = """
                SELECT id,
                       id_pedido,
                       id_repartidor,
                       fecha,
                       hora
                FROM entrega
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

                entregas.add(
                        crearEntregaDesdeResultado(
                                resultado
                        )
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar entregas: "
                            + e.getMessage()
            );
        }

        return entregas;
    }


    // =========================
    // READ POR PEDIDO
    // =========================

    public List<Entrega> listarPorPedido(
            int idPedido
    ) {

        List<Entrega> entregas =
                new ArrayList<>();

        String sql = """
                SELECT id,
                       id_pedido,
                       id_repartidor,
                       fecha,
                       hora
                FROM entrega
                WHERE id_pedido = ?
                ORDER BY id
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement statement =
                        conexion.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    idPedido
            );

            try (
                    ResultSet resultado =
                            statement.executeQuery()
            ) {

                while (resultado.next()) {

                    entregas.add(
                            crearEntregaDesdeResultado(
                                    resultado
                            )
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar entregas por pedido: "
                            + e.getMessage()
            );
        }

        return entregas;
    }


    // =========================
    // READ POR REPARTIDOR
    // =========================

    public List<Entrega> listarPorRepartidor(
            int idRepartidor
    ) {

        List<Entrega> entregas =
                new ArrayList<>();

        String sql = """
                SELECT id,
                       id_pedido,
                       id_repartidor,
                       fecha,
                       hora
                FROM entrega
                WHERE id_repartidor = ?
                ORDER BY id
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement statement =
                        conexion.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    idRepartidor
            );

            try (
                    ResultSet resultado =
                            statement.executeQuery()
            ) {

                while (resultado.next()) {

                    entregas.add(
                            crearEntregaDesdeResultado(
                                    resultado
                            )
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar entregas por repartidor: "
                            + e.getMessage()
            );
        }

        return entregas;
    }


    // =========================
    // UPDATE
    // =========================

    public boolean actualizar(
            Entrega entrega
    ) {

        String sql = """
                UPDATE entrega
                SET id_pedido = ?,
                    id_repartidor = ?,
                    fecha = ?,
                    hora = ?
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
                    entrega.getIdPedido()
            );

            statement.setInt(
                    2,
                    entrega.getIdRepartidor()
            );

            statement.setDate(
                    3,
                    Date.valueOf(
                            entrega.getFecha()
                    )
            );

            statement.setTime(
                    4,
                    Time.valueOf(
                            entrega.getHora()
                    )
            );

            statement.setInt(
                    5,
                    entrega.getId()
            );

            int filasAfectadas =
                    statement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar entrega: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // =========================
    // DELETE
    // =========================

    public boolean eliminar(
            int id
    ) {

        String sql = """
                DELETE FROM entrega
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
                    "Error al eliminar entrega: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // =========================
    // MÉTODO AUXILIAR
    // =========================

    private Entrega crearEntregaDesdeResultado(
            ResultSet resultado
    ) throws SQLException {

        return new Entrega(

                resultado.getInt("id"),

                resultado.getInt(
                        "id_pedido"
                ),

                resultado.getInt(
                        "id_repartidor"
                ),

                resultado.getDate(
                        "fecha"
                ).toLocalDate(),

                resultado.getTime(
                        "hora"
                ).toLocalTime()
        );
    }
}