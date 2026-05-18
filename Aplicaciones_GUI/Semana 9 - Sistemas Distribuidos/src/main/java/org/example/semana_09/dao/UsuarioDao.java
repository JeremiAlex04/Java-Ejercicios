package org.example.semana_09.dao;

import org.example.semana_09.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private final miClaseConexion conexion = new miClaseConexion();

    public Usuario buscarPorCodigo(String codUsuario) throws SQLException {
        String sql = "SELECT * FROM tbusuario2 WHERE cod_usuario = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapUsuario(rs) : null;
            }
        }
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM tbusuario2 WHERE estado <> 'ELIMINADO' ORDER BY item_ai DESC";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(mapUsuario(rs));
            }
        }
        return usuarios;
    }

    public List<Usuario> buscarPorTexto(String texto) throws SQLException {
        String sql = """
                SELECT * FROM tbusuario2
                WHERE estado <> 'ELIMINADO'
                  AND (cod_usuario LIKE ? OR nombres LIKE ? OR apellidos LIKE ? OR email LIKE ?)
                ORDER BY item_ai DESC
                """;
        List<Usuario> usuarios = new ArrayList<>();
        String criterio = "%" + texto + "%";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, criterio);
            ps.setString(2, criterio);
            ps.setString(3, criterio);
            ps.setString(4, criterio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapUsuario(rs));
                }
            }
        }
        return usuarios;
    }

    public void registrarIngresoExitoso(long itemAi, String ip, String nombrePc, String modificadoPor) throws SQLException {
        String sql = """
                UPDATE tbusuario2
                SET en_linea = 1,
                    bloqueado = 0,
                    intentos_fallidos = 0,
                    num_ingresos = num_ingresos + 1,
                    fecha_ultimo_acceso = CURRENT_TIMESTAMP,
                    fecha_modificacion = CURRENT_TIMESTAMP,
                    ip_acceso = ?,
                    nombre_pc = ?,
                    modificado_por = ?
                WHERE item_ai = ?
                """;
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ip);
            ps.setString(2, nombrePc);
            ps.setString(3, modificadoPor);
            ps.setLong(4, itemAi);
            ps.executeUpdate();
        }
    }

    public int registrarIntentoFallido(long itemAi, String modificadoPor) throws SQLException {
        String updateSql = """
                UPDATE tbusuario2
                SET intentos_fallidos = intentos_fallidos + 1,
                    fecha_modificacion = CURRENT_TIMESTAMP,
                    modificado_por = ?
                WHERE item_ai = ?
                """;
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(updateSql)) {
            ps.setString(1, modificadoPor);
            ps.setLong(2, itemAi);
            ps.executeUpdate();
        }
        return obtenerIntentosFallidos(itemAi);
    }

    public void bloquearUsuario(long itemAi, String modificadoPor) throws SQLException {
        String sql = """
                UPDATE tbusuario2
                SET bloqueado = 1,
                    en_linea = 0,
                    estado = 'BLOQUEADO',
                    fecha_modificacion = CURRENT_TIMESTAMP,
                    modificado_por = ?
                WHERE item_ai = ?
                """;
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, modificadoPor);
            ps.setLong(2, itemAi);
            ps.executeUpdate();
        }
    }

    public void desbloquearUsuario(long itemAi, String modificadoPor) throws SQLException {
        String sql = """
                UPDATE tbusuario2
                SET bloqueado = 0,
                    intentos_fallidos = 0,
                    estado = 'ACTIVO',
                    fecha_modificacion = CURRENT_TIMESTAMP,
                    modificado_por = ?
                WHERE item_ai = ?
                """;
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, modificadoPor);
            ps.setLong(2, itemAi);
            ps.executeUpdate();
        }
    }

    public void marcarFueraDeLinea(long itemAi) throws SQLException {
        String sql = """
                UPDATE tbusuario2
                SET en_linea = 0,
                    fecha_modificacion = CURRENT_TIMESTAMP
                WHERE item_ai = ?
                """;
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, itemAi);
            ps.executeUpdate();
        }
    }

    public boolean existeCodigo(String codUsuario) throws SQLException {
        String sql = "SELECT 1 FROM tbusuario2 WHERE cod_usuario = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void insertar(Usuario usuario) throws SQLException {
        String sql = """
                INSERT INTO tbusuario2 (
                    id_usuario, cod_usuario, password, nombres, apellidos, email, permisos, estado,
                    bloqueado, en_linea, num_ingresos, intentos_fallidos, nombre_pc, ip_acceso, lugar, ciudad,
                    creado_por, modificado_por, eliminado_por, fecha_creacion, fecha_modificacion,
                    fecha_eliminacion, fecha_ultimo_acceso
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL)
                """;
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getCodUsuario());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getNombres());
            ps.setString(5, usuario.getApellidos());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getPermisos());
            ps.setString(8, usuario.getEstado());
            ps.setBoolean(9, usuario.isBloqueado());
            ps.setBoolean(10, usuario.isEnLinea());
            ps.setInt(11, usuario.getNumIngresos());
            ps.setInt(12, usuario.getIntentosFallidos());
            ps.setString(13, usuario.getNombrePc());
            ps.setString(14, usuario.getIpAcceso());
            ps.setString(15, usuario.getLugar());
            ps.setString(16, usuario.getCiudad());
            ps.setString(17, usuario.getCreadoPor());
            ps.setString(18, usuario.getModificadoPor());
            ps.setString(19, usuario.getEliminadoPor());
            ps.executeUpdate();
        }
    }

    private int obtenerIntentosFallidos(long itemAi) throws SQLException {
        String sql = "SELECT intentos_fallidos FROM tbusuario2 WHERE item_ai = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, itemAi);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("intentos_fallidos") : 0;
            }
        }
    }

    private Usuario mapUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setItemAi(rs.getLong("item_ai"));
        usuario.setIdUsuario(rs.getString("id_usuario"));
        usuario.setCodUsuario(rs.getString("cod_usuario"));
        usuario.setPassword(rs.getString("password"));
        usuario.setNombres(rs.getString("nombres"));
        usuario.setApellidos(rs.getString("apellidos"));
        usuario.setEmail(rs.getString("email"));
        usuario.setPermisos(rs.getString("permisos"));
        usuario.setEstado(rs.getString("estado"));
        usuario.setBloqueado(rs.getBoolean("bloqueado"));
        usuario.setEnLinea(rs.getBoolean("en_linea"));
        usuario.setNumIngresos(rs.getInt("num_ingresos"));
        usuario.setIntentosFallidos(rs.getInt("intentos_fallidos"));
        usuario.setNombrePc(rs.getString("nombre_pc"));
        usuario.setIpAcceso(rs.getString("ip_acceso"));
        usuario.setLugar(rs.getString("lugar"));
        usuario.setCiudad(rs.getString("ciudad"));
        usuario.setCreadoPor(rs.getString("creado_por"));
        usuario.setModificadoPor(rs.getString("modificado_por"));
        usuario.setEliminadoPor(rs.getString("eliminado_por"));
        usuario.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        usuario.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
        usuario.setFechaEliminacion(rs.getTimestamp("fecha_eliminacion"));
        usuario.setFechaUltimoAcceso(rs.getTimestamp("fecha_ultimo_acceso"));
        return usuario;
    }
}
