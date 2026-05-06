package org.example.semana07.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.semana07.dao.UsuarioDAO;
import org.example.semana07.model.Usuario;

import java.io.IOException;
import java.util.List;

@WebServlet("/UsuarioController")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null || accion.isBlank()) {
            accion = "listar";
        }

        switch (accion) {
            case "nuevo":
                mostrarFormulario(request, response, new Usuario());
                break;
            case "editar":
                cargarUsuarioParaEdicion(request, response);
                break;
            case "eliminar":
                eliminarUsuario(request, response);
                break;
            case "listar":
            default:
                mostrarListado(request, response, new Usuario());
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if ("insertar".equals(accion)) {
            insertarUsuario(request, response);
        } else if ("actualizar".equals(accion)) {
            actualizarUsuario(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/UsuarioController?accion=listar");
        }
    }

    private void mostrarListado(HttpServletRequest request, HttpServletResponse response, Usuario usuarioEdicion)
            throws ServletException, IOException {
        List<Usuario> lista = dao.listarUsuarios();
        request.setAttribute("usuarios", lista);
        request.setAttribute("usuarioEdicion", usuarioEdicion);
        request.getRequestDispatcher("/usuarios.jsp").forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
            throws ServletException, IOException {
        mostrarListado(request, response, usuario);
    }

    private void cargarUsuarioParaEdicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = dao.obtenerUsuarioPorId(id);

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/UsuarioController?accion=listar");
            return;
        }

        mostrarFormulario(request, response, usuario);
    }

    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Usuario usuario = construirUsuarioDesdeRequest(request);
        dao.registrar(usuario);
        response.sendRedirect(request.getContextPath() + "/UsuarioController?accion=listar");
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Usuario usuario = construirUsuarioDesdeRequest(request);
        usuario.setItemAi(Integer.parseInt(request.getParameter("id")));
        dao.actualizar(usuario);
        response.sendRedirect(request.getContextPath() + "/UsuarioController?accion=listar");
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/UsuarioController?accion=listar");
    }

    private Usuario construirUsuarioDesdeRequest(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        usuario.setCodUsuario(request.getParameter("codUsuario"));
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setNombres(request.getParameter("nombres"));
        usuario.setApellidos(request.getParameter("apellidos"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setPermisos(request.getParameter("permisos"));

        String estado = request.getParameter("estado");
        usuario.setEstado(estado == null || estado.isBlank() ? 1 : Integer.parseInt(estado));

        return usuario;
    }
}
