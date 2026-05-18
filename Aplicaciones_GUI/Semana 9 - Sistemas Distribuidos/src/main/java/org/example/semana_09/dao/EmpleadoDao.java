package org.example.semana_09.dao;

import org.example.semana_09.model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {
    private final miClaseConexion conexion = new miClaseConexion();

    public List<Empleado> listar() throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_empleado";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapEmpleado(rs));
            }
        }
        return lista;
    }

    public Empleado obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tb_empleado WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEmpleado(rs);
                }
            }
        }
        return null;
    }

    public void insertar(Empleado e) throws SQLException {
        String sql = "INSERT INTO tb_empleado (apellidos, nombres, genero, direccion, celular, fecha_nacimiento, observacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getApellidos());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getGenero());
            ps.setString(4, e.getDireccion());
            ps.setString(5, e.getCelular());
            ps.setDate(6, e.getFechaNacimiento());
            ps.setString(7, e.getObservacion());
            ps.executeUpdate();
        }
    }

    public void actualizar(Empleado e) throws SQLException {
        String sql = "UPDATE tb_empleado SET apellidos = ?, nombres = ?, genero = ?, direccion = ?, celular = ?, fecha_nacimiento = ?, observacion = ? WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getApellidos());
            ps.setString(2, e.getNombres());
            ps.setString(3, e.getGenero());
            ps.setString(4, e.getDireccion());
            ps.setString(5, e.getCelular());
            ps.setDate(6, e.getFechaNacimiento());
            ps.setString(7, e.getObservacion());
            ps.setInt(8, e.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM tb_empleado WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Empleado mapEmpleado(ResultSet rs) throws SQLException {
        Empleado e = new Empleado();
        e.setId(rs.getInt("id"));
        e.setApellidos(rs.getString("apellidos"));
        e.setNombres(rs.getString("nombres"));
        e.setGenero(rs.getString("genero"));
        e.setDireccion(rs.getString("direccion"));
        e.setCelular(rs.getString("celular"));
        e.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        e.setObservacion(rs.getString("observacion"));
        return e;
    }
}
