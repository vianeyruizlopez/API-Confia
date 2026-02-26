package org.example.repository;

import org.example.config.DataBase;
import org.example.model.Tarjeta;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TarjetaRepository {

    public List<Tarjeta> obtenerTodoTarjetas() {
        List<Tarjeta> lista = new LinkedList<>();
        String sql = "SELECT t.id, t.texto, t.cita, t.categoria_id, c.nombre as nombreCategoria " +
                "FROM tarjetas t JOIN categorias c ON t.categoria_id = c.id";
        try (Connection conn = DataBase.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Tarjeta> buscarPorCategoria(String categoria) {
        List<Tarjeta> lista = new LinkedList<>();
        String sql = "SELECT t.id, t.texto, t.cita, t.categoria_id, c.nombre as nombreCategoria " +
                "FROM tarjetas t JOIN categorias c ON t.categoria_id = c.id WHERE c.nombre LIKE ?";
        try (Connection conn = DataBase.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + categoria + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void agregarTarjeta(Tarjeta tarjeta) {
        String sql = "INSERT INTO tarjetas (texto, cita, categoria_id) VALUES (?, ?, ?)";
        try (Connection con = DataBase.getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tarjeta.getTexto());
            stmt.setString(2, tarjeta.getCita());
            stmt.setInt(3, tarjeta.getCategoriaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error en BD al agregar: " + e.getMessage());
        }
    }

    public void actualizarTarjeta(Tarjeta tarjeta) {
        String sql = "UPDATE tarjetas SET texto = ?, cita = ?, categoria_id = ? WHERE id = ?";
        try (Connection con = DataBase.getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tarjeta.getTexto());
            stmt.setString(2, tarjeta.getCita());
            stmt.setInt(3, tarjeta.getCategoriaId());
            stmt.setInt(4, tarjeta.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error en BD al actualizar: " + e.getMessage());
        }
    }

    public void eliminarTarjeta(int id) {
        String sql = "DELETE FROM tarjetas WHERE id = ?";
        try (Connection con = DataBase.getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Tarjeta mapear(ResultSet rs) throws SQLException {
        Tarjeta t = new Tarjeta();
        t.setId(rs.getInt("id"));
        t.setTexto(rs.getString("texto"));
        t.setCita(rs.getString("cita"));
        t.setCategoriaId(rs.getInt("categoria_id"));
        t.setNombreCategoria(rs.getString("nombreCategoria"));
        return t;
    }
}