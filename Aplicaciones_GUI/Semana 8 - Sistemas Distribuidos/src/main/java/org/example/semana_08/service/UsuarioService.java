package org.example.semana_08.service;

import org.example.semana_08.dao.UsuarioDao;
import org.example.semana_08.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
    private final UsuarioDao usuarioDao = new UsuarioDao();

    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioDao.listarUsuarios();
    }

    public List<Usuario> buscarUsuarios(String criterio) throws SQLException {
        if (criterio == null || criterio.isBlank()) {
            return listarUsuarios();
        }
        return usuarioDao.buscarPorTexto(criterio.trim());
    }

    public void registrarUsuario(Usuario usuario, String creador) throws SQLException {
        validarUsuario(usuario);
        if (usuarioDao.existeCodigo(usuario.getCodUsuario())) {
            throw new IllegalArgumentException("El codigo de usuario ya existe.");
        }

        usuario.setIdUsuario(generarIdUsuario());
        usuario.setEstado("ACTIVO");
        usuario.setBloqueado(false);
        usuario.setEnLinea(false);
        usuario.setNumIngresos(0);
        usuario.setIntentosFallidos(0);
        usuario.setCreadoPor(creador);
        usuario.setModificadoPor(creador);
        usuarioDao.insertar(usuario);
    }

    public void desbloquearUsuario(long itemAi, String administrador) throws SQLException {
        usuarioDao.desbloquearUsuario(itemAi, administrador);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getCodUsuario() == null || usuario.getCodUsuario().isBlank()
                || usuario.getPassword() == null || usuario.getPassword().isBlank()
                || usuario.getNombres() == null || usuario.getNombres().isBlank()
                || usuario.getApellidos() == null || usuario.getApellidos().isBlank()
                || usuario.getEmail() == null || usuario.getEmail().isBlank()
                || usuario.getPermisos() == null || usuario.getPermisos().isBlank()) {
            throw new IllegalArgumentException("Todos los campos del usuario son obligatorios.");
        }
    }

    private String generarIdUsuario() throws SQLException {
        int total = usuarioDao.listarUsuarios().size();
        return String.valueOf(100000 + total + 1);
    }
}
