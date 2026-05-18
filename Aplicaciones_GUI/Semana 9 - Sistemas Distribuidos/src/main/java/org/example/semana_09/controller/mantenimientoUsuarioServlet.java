package org.example.semana_09.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.semana_09.model.Usuario;
import org.example.semana_09.service.UsuarioService;

import java.io.IOException;
import java.sql.SQLException;

public class mantenimientoUsuarioServlet extends HttpServlet {
    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cargarPantalla(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario administrador = session == null ? null : (Usuario) session.getAttribute("usuarioSesion");
        if (administrador == null || !administrador.esAdministrador()) {
            response.sendRedirect("menuPrincipal.jsp");
            return;
        }

        String accion = request.getParameter("accion");
        try {
            if ("crear".equalsIgnoreCase(accion)) {
                Usuario usuario = new Usuario();
                usuario.setCodUsuario(request.getParameter("txtCodUsuario"));
                usuario.setPassword(request.getParameter("txtPassword"));
                usuario.setNombres(request.getParameter("txtNombres"));
                usuario.setApellidos(request.getParameter("txtApellidos"));
                usuario.setEmail(request.getParameter("txtEmail"));
                usuario.setPermisos(request.getParameter("txtPermisos"));
                usuario.setNombrePc(request.getParameter("txtNombrePc"));
                usuario.setIpAcceso(request.getParameter("txtIpAcceso"));
                usuario.setLugar(request.getParameter("txtLugar"));
                usuario.setCiudad(request.getParameter("txtCiudad"));
                usuarioService.registrarUsuario(usuario, administrador.getCodUsuario());
                request.setAttribute("mensajeExito", "Usuario registrado correctamente.");
            } else if ("desbloquear".equalsIgnoreCase(accion)) {
                long itemAi = Long.parseLong(request.getParameter("itemAi"));
                usuarioService.desbloquearUsuario(itemAi, administrador.getCodUsuario());
                request.setAttribute("mensajeExito", "Usuario desbloqueado correctamente.");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("mensajeError", e.getMessage());
        } catch (SQLException e) {
            request.setAttribute("mensajeError", "No se pudo completar la operacion sobre los usuarios.");
        }

        cargarPantalla(request, response);
    }

    private void cargarPantalla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario usuario = session == null ? null : (Usuario) session.getAttribute("usuarioSesion");
        if (usuario == null || !usuario.esAdministrador()) {
            response.sendRedirect("menuPrincipal.jsp");
            return;
        }

        String criterio = request.getParameter("txtUsuarioB");
        try {
            request.setAttribute("usuarios", usuarioService.buscarUsuarios(criterio));
            request.setAttribute("criterioBusqueda", criterio == null ? "" : criterio);
        } catch (SQLException e) {
            request.setAttribute("mensajeError", "No se pudo consultar la lista de usuarios.");
        }
        request.getRequestDispatcher("mantenimientoUsuario.jsp").forward(request, response);
    }
}
