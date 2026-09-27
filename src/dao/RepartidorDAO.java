package dao;

import modelo.Repartidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

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
                    return claves.getInt(1);
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

                String nombre =
                        resultado.getString("nombre");

                repartidores.add(
                        new Repartidor(nombre)
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
}