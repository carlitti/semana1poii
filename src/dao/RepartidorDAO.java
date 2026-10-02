package dao;

import modelo.Repartidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    // CREATE
    public int guardar(Repartidor repartidor) {

        String sql = """
                INSERT INTO repartidor (nombre)
                VALUES (?)
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
                    repartidor.getNombre()
            );

            statement.executeUpdate();

            try (ResultSet claves = statement.getGeneratedKeys()) {

                if (claves.next()) {

                    int idGenerado = claves.getInt(1);

                    repartidor.setId(idGenerado);

                    return idGenerado;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al guardar repartidor: "
                            + e.getMessage()
            );
        }

        return -1;
    }


    // READ
    public List<Repartidor> listarTodos() {

        List<Repartidor> repartidores =
                new ArrayList<>();

        String sql = """
                SELECT id, nombre
                FROM repartidor
                ORDER BY id
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement =
                        conexion.prepareStatement(sql);
                ResultSet resultado =
                        statement.executeQuery()
        ) {

            while (resultado.next()) {

                int id =
                        resultado.getInt("id");

                String nombre =
                        resultado.getString("nombre");

                repartidores.add(
                        new Repartidor(id, nombre)
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar repartidores: "
                            + e.getMessage()
            );
        }

        return repartidores;
    }


    // UPDATE
    public boolean actualizar(Repartidor repartidor) {

        String sql = """
                UPDATE repartidor
                SET nombre = ?
                WHERE id = ?
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement statement =
                        conexion.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    repartidor.getNombre()
            );

            statement.setInt(
                    2,
                    repartidor.getId()
            );

            int filasAfectadas =
                    statement.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar repartidor: "
                            + e.getMessage()
            );

            return false;
        }
    }


    // DELETE
    public boolean eliminar(int id) {

        String sql = """
                DELETE FROM repartidor
                WHERE id = ?
                """;

        try (
                Connection conexion = ConexionBD.conectar();
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
                    "Error al eliminar repartidor: "
                            + e.getMessage()
            );

            return false;
        }
    }
}