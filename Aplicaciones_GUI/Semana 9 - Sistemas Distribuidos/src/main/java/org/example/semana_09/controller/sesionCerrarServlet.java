package org.example.semana_09.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.semana_09.model.Usuario;
import org.example.semana_09.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

public class sesionCerrarServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuarioSesion");
            try {
                authService.cerrarSesion(usuario);
            } catch (SQLException ignored) {
            }
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
    }
}
