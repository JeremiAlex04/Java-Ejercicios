package org.example.semana_09.model;

public class LoginResult {
    private final boolean autenticado;
    private final String mensaje;
    private final Usuario usuario;

    public LoginResult(boolean autenticado, String mensaje, Usuario usuario) {
        this.autenticado = autenticado;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
