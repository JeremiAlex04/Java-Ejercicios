package org.example.semana_08.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.semana_08.model.LoginResult;
import org.example.semana_08.model.Usuario;
import org.example.semana_08.service.AuthService;

import java.io.IOException;
import java.sql.SQLException;

public class sesionVerificaServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codUsuario = request.getParameter("txtUsuario");
        String password = request.getParameter("txtPassword");
        String nombrePc = request.getHeader("User-Agent");

        try {
            LoginResult resultado = authService.autenticar(codUsuario, password, request.getRemoteAddr(), nombrePc);
            if (!resultado.isAutenticado()) {
                request.setAttribute("mensajeError", resultado.getMensaje());
                request.setAttribute("usuarioIngresado", codUsuario);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            Usuario usuario = resultado.getUsuario();
            HttpSession session = request.getSession(true);
            session.setAttribute("usuarioSesion", usuario);
            response.sendRedirect("menuPrincipal.jsp");
        } catch (SQLException e) {
            request.setAttribute("mensajeError", "No fue posible validar el usuario. Revise la conexion a la base de datos.");
            request.setAttribute("usuarioIngresado", codUsuario);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
