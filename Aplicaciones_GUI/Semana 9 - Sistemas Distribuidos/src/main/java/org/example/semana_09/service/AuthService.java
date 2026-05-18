package org.example.semana_09.service;

import org.example.semana_09.dao.UsuarioDao;
import org.example.semana_09.model.LoginResult;
import org.example.semana_09.model.Usuario;

import java.sql.SQLException;

public class AuthService {
    private final UsuarioDao usuarioDao = new UsuarioDao();

    public LoginResult autenticar(String codUsuario, String password, String ip, String nombrePc) throws SQLException {
        Usuario usuario = usuarioDao.buscarPorCodigo(codUsuario);
        if (usuario == null) {
            return new LoginResult(false, "El usuario es incorrecto.", null);
        }

        if (usuario.isBloqueado()) {
            return new LoginResult(false, "El usuario esta bloqueado. Solicite al administrador que lo desbloquee.", null);
        }

        if (!"ACTIVO".equalsIgnoreCase(usuario.getEstado())) {
            return new LoginResult(false, "El usuario no esta activo.", null);
        }

        if (!usuario.getPassword().equals(password)) {
            int intentos = usuarioDao.registrarIntentoFallido(usuario.getItemAi(), usuario.getCodUsuario());
            if (intentos >= 3) {
                usuarioDao.bloquearUsuario(usuario.getItemAi(), usuario.getCodUsuario());
                return new LoginResult(false, "Supero los 3 intentos permitidos. El usuario ha sido bloqueado.", null);
            }
            return new LoginResult(false, "La clave es incorrecta. Intento " + intentos + " de 3.", null);
        }

        usuarioDao.registrarIngresoExitoso(usuario.getItemAi(), ip, nombrePc, usuario.getCodUsuario());
        Usuario actualizado = usuarioDao.buscarPorCodigo(codUsuario);
        return new LoginResult(true, "Ingreso correcto.", actualizado);
    }

    public void cerrarSesion(Usuario usuario) throws SQLException {
        if (usuario != null) {
            usuarioDao.marcarFueraDeLinea(usuario.getItemAi());
        }
    }
}
