package org.example.repository;

import org.example.config.DataBase;
import org.example.model.Tarjeta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TarjetaRepository {

    public List<Tarjeta> obtenerTodoTarjetas() {
        List<Tarjeta> lista = new LinkedList<>();
        String sql = "SELECT * FROM tarjetas";
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
        String sql = "SELECT * FROM  tarjetas WHERE categoria= LIKE?";
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
        String sql = "INSERT INTO tarjeta (texto, cita, categoria) VALUES (?, ?, ?)";
        try (Connection con = DataBase.getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tarjeta.getTexto());
            stmt.setString(2, tarjeta.getCita());
            stmt.setString(3, tarjeta.getCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void actualizarTarjeta(Tarjeta tarjeta) {
        String sql="UPDATE tarjetas set texto = ?, cita = ?, categoria = ? WHERE id = ?";
        try (Connection con = DataBase.getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tarjeta.getTexto());
            stmt.setString(2, tarjeta.getCita());
            stmt.setString(3, tarjeta.getCategoria());
            stmt.setInt(4,tarjeta.getIdTarjeta());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void eliminarTarjeta(int id) {
        String sql= "DELETE from tarjetas WHERE id = ?";
        try (Connection con = DataBase.getDataSource().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }


    private Tarjeta mapear(ResultSet rs)throws SQLException {
        Tarjeta s =new Tarjeta();
        s.setIdTarjeta(rs.getInt("id"));
        s.setTexto(rs.getString("texto"));
        s.setCita(rs.getString("cita"));
        s.setCategoria(rs.getString("categoria"));
        return s;
    }

}
