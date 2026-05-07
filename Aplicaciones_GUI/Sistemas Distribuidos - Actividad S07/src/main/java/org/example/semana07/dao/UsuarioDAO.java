package org.example.semana07.dao;

import org.example.semana07.model.Usuario;
import org.example.semana07.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT ItemAi, CodUsuario, Usuario, Nombres, Apellidos, Email, Permisos, Estado "
                + "FROM usuarios ORDER BY ItemAi";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT ItemAi, CodUsuario, Usuario, Nombres, Apellidos, Email, Permisos, Estado "
                + "FROM usuarios WHERE ItemAi = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registrar(Usuario u) {
        String sql = "INSERT INTO usuarios (CodUsuario, Usuario, Nombres, Apellidos, Email, Permisos, Estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getCodUsuario());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getNombres());
            ps.setString(4, u.getApellidos());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPermisos());
            ps.setInt(7, u.getEstado());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Usuario u) {
        String sql = "UPDATE usuarios SET CodUsuario = ?, Usuario = ?, Nombres = ?, Apellidos = ?, "
                + "Email = ?, Permisos = ?, Estado = ? WHERE ItemAi = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getCodUsuario());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getNombres());
            ps.setString(4, u.getApellidos());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getPermisos());
            ps.setInt(7, u.getEstado());
            ps.setInt(8, u.getItemAi());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE ItemAi = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setItemAi(rs.getInt("ItemAi"));
        u.setCodUsuario(rs.getString("CodUsuario"));
        u.setUsuario(rs.getString("Usuario"));
        u.setNombres(rs.getString("Nombres"));
        u.setApellidos(rs.getString("Apellidos"));
        u.setEmail(rs.getString("Email"));
        u.setPermisos(rs.getString("Permisos"));
        u.setEstado(rs.getInt("Estado"));
        return u;
    }
}
