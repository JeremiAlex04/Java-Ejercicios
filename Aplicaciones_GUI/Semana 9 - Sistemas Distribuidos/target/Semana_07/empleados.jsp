<%@page contentType="text/html" session="true" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="org.example.semana_09.model.Empleado" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/segmentoEstilos.jspf" %>
        <title>Listado de Empleados</title>
        <style>
            .btn-edit { background-color: #ffc107; color: #000; border: none; padding: 5px 10px; border-radius: 4px; text-decoration: none; font-size: 0.9em; }
            .btn-delete { background-color: #dc3545; color: #fff; border: none; padding: 5px 10px; border-radius: 4px; text-decoration: none; font-size: 0.9em; }
            .btn-new { background-color: #198754; color: #fff; border: none; padding: 10px 15px; border-radius: 4px; text-decoration: none; display: inline-block; margin-bottom: 20px; }
            .table-container { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
            table { width: 100%; border-collapse: collapse; margin-top: 10px; }
            th { border-bottom: 2px solid #dee2e6; padding: 12px; text-align: left; color: #495057; }
            td { padding: 12px; border-bottom: 1px solid #dee2e6; color: #212529; }
            .actions-cell { display: flex; gap: 5px; }
            h2 { color: #3f51b5; font-weight: 500; }
        </style>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/segmentoSuperior2.jspf" %>
        
        <div class="container mt-4">
            <div class="table-container">
                <h2>Listado de Empleados</h2>
                <hr>
                
                <a href="EmpleadoServlet?accion=nuevo" class="btn-new">
                    <i class="fas fa-user-plus"></i> Nuevo Registro
                </a>

                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Apellidos</th>
                            <th>Nombres</th>
                            <th>Genero</th>
                            <th>Direccion</th>
                            <th>Celular</th>
                            <th>F.Nacimiento</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
                            if (empleados != null) {
                                for (Empleado e : empleados) {
                        %>
                        <tr>
                            <td><%= e.getId() %></td>
                            <td><%= e.getApellidos() %></td>
                            <td><%= e.getNombres() %></td>
                            <td><%= "MASCULINO".equalsIgnoreCase(e.getGenero()) ? "M" : "F" %></td>
                            <td><%= e.getDireccion() != null ? e.getDireccion() : "" %></td>
                            <td><%= e.getCelular() != null ? e.getCelular() : "" %></td>
                            <td><%= e.getFechaNacimiento() != null ? e.getFechaNacimiento().toString() : "" %></td>
                            <td class="actions-cell">
                                <a href="EmpleadoServlet?accion=editar&id=<%= e.getId() %>" class="btn-edit">
                                    <i class="fas fa-edit"></i> Editar
                                </a>
                                <a href="EmpleadoServlet?accion=eliminar&id=<%= e.getId() %>" class="btn-delete" onclick="return confirm('¿Está seguro de eliminar este registro?')">
                                    <i class="fas fa-trash-alt"></i> Eliminar
                                </a>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

        <%@include file="WEB-INF/jspf/segmentoInferior.jspf" %>
    </body>
</html>
