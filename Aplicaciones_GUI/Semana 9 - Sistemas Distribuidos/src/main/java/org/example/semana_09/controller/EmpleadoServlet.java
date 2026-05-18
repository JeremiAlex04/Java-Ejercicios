package org.example.semana_09.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.semana_09.model.Empleado;
import org.example.semana_09.service.EmpleadoService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/EmpleadoServlet"})
public class EmpleadoServlet extends HttpServlet {
    private final EmpleadoService empleadoService = new EmpleadoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        try {
            switch (accion) {
                case "listar":
                    listar(request, response);
                    break;
                case "nuevo":
                    nuevo(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
                    break;
                default:
                    listar(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("guardar".equalsIgnoreCase(accion)) {
            try {
                guardar(request, response);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("empleado", new Empleado());
        request.getRequestDispatcher("editar_empleado.jsp").forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empleado e = empleadoService.obtenerEmpleadoPorId(id);
        request.setAttribute("empleado", e);
        request.getRequestDispatcher("editar_empleado.jsp").forward(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        empleadoService.eliminarEmpleado(id);
        response.sendRedirect("EmpleadoServlet?accion=listar");
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = 0;
        String idStr = request.getParameter("txtId");
        if (idStr != null && !idStr.isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        String apellidos = request.getParameter("txtApellidos");
        String nombres = request.getParameter("txtNombres");
        String genero = request.getParameter("txtGenero");
        String direccion = request.getParameter("txtDireccion");
        String celular = request.getParameter("txtCelular");
        String fechaNacStr = request.getParameter("txtFechaNacimiento");
        String observacion = request.getParameter("txtObservacion");

        Empleado e = new Empleado();
        e.setId(id);
        e.setApellidos(apellidos);
        e.setNombres(nombres);
        e.setGenero(genero);
        e.setDireccion(direccion);
        e.setCelular(celular);
        if (fechaNacStr != null && !fechaNacStr.isEmpty()) {
            e.setFechaNacimiento(Date.valueOf(fechaNacStr));
        }
        e.setObservacion(observacion);

        empleadoService.guardarEmpleado(e);
        response.sendRedirect("EmpleadoServlet?accion=listar");
    }
}
