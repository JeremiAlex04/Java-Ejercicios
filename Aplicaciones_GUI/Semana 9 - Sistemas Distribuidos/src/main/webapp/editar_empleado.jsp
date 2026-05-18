<%@page contentType="text/html" session="true" pageEncoding="UTF-8"%>
<%@page import="org.example.semana_09.model.Empleado" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/segmentoEstilos.jspf" %>
        <title>Editar Empleado</title>
        <style>
            .form-container { 
                max-width: 600px; 
                margin: 40px auto; 
                background: #e3f2fd; 
                padding: 0; 
                border-radius: 8px; 
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                border: 1px solid #bbdefb;
            }
            .form-header { 
                background: #bbdefb; 
                padding: 15px 20px; 
                border-radius: 8px 8px 0 0;
                color: #1976d2;
                font-size: 1.25em;
                font-weight: 500;
                border-bottom: 1px solid #90caf9;
            }
            .form-body { padding: 20px; }
            .form-group { margin-bottom: 15px; }
            .form-group label { display: block; margin-bottom: 5px; color: #1976d2; font-size: 0.9em; }
            .form-control { width: 100%; padding: 8px; border: 1px solid #90caf9; border-radius: 4px; box-sizing: border-box; }
            .form-control:focus { outline: none; border-color: #2196f3; box-shadow: 0 0 5px rgba(33, 150, 243, 0.3); }
            .form-actions { display: flex; justify-content: center; gap: 10px; margin-top: 20px; }
            .btn-save { background-color: #0d6efd; color: #fff; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; display: flex; align-items: center; gap: 5px; }
            .btn-cancel { background-color: #198754; color: #fff; border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; text-decoration: none; display: flex; align-items: center; gap: 5px; }
            .btn-save:hover { background-color: #0b5ed7; }
            .btn-cancel:hover { background-color: #157347; }
        </style>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/segmentoSuperior2.jspf" %>
        
        <%
            Empleado e = (Empleado) request.getAttribute("empleado");
            if (e == null) e = new Empleado();
        %>

        <div class="form-container">
            <div class="form-header">
                Editar empleado
            </div>
            <div class="form-body">
                <form action="EmpleadoServlet" method="post">
                    <input type="hidden" name="accion" value="guardar">
                    
                    <div class="form-group">
                        <label for="txtId">Id</label>
                        <input type="text" id="txtId" name="txtId" class="form-control" value="<%= e.getId() > 0 ? e.getId() : "" %>" readonly>
                    </div>

                    <div class="form-group">
                        <label for="txtApellidos">Apellidos</label>
                        <input type="text" id="txtApellidos" name="txtApellidos" class="form-control" value="<%= e.getApellidos() != null ? e.getApellidos() : "" %>" required>
                    </div>

                    <div class="form-group">
                        <label for="txtNombres">Nombres</label>
                        <input type="text" id="txtNombres" name="txtNombres" class="form-control" value="<%= e.getNombres() != null ? e.getNombres() : "" %>" required>
                    </div>

                    <div class="form-group">
                        <label for="txtGenero">Genero</label>
                        <select id="txtGenero" name="txtGenero" class="form-control" required>
                            <option value="MASCULINO" <%= "MASCULINO".equalsIgnoreCase(e.getGenero()) ? "selected" : "" %>>MASCULINO</option>
                            <option value="FEMENINO" <%= "FEMENINO".equalsIgnoreCase(e.getGenero()) ? "selected" : "" %>>FEMENINO</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="txtDireccion">Direccion</label>
                        <input type="text" id="txtDireccion" name="txtDireccion" class="form-control" value="<%= e.getDireccion() != null ? e.getDireccion() : "" %>">
                    </div>

                    <div class="form-group">
                        <label for="txtCelular">Celular</label>
                        <input type="text" id="txtCelular" name="txtCelular" class="form-control" value="<%= e.getCelular() != null ? e.getCelular() : "" %>">
                    </div>

                    <div class="form-group">
                        <label for="txtFechaNacimiento">Fecha de nacimiento</label>
                        <input type="date" id="txtFechaNacimiento" name="txtFechaNacimiento" class="form-control" value="<%= e.getFechaNacimiento() != null ? e.getFechaNacimiento().toString() : "" %>">
                    </div>

                    <div class="form-group">
                        <label for="txtObservacion">Observacion</label>
                        <input type="text" id="txtObservacion" name="txtObservacion" class="form-control" value="<%= e.getObservacion() != null ? e.getObservacion() : "" %>" placeholder="Observaciones">
                    </div>

                    <div class="form-actions">
                        <a href="EmpleadoServlet?accion=listar" class="btn-cancel">
                            <i class="fas fa-times-circle"></i> Cancelar
                        </a>
                        <button type="submit" class="btn-save">
                            <i class="fas fa-save"></i> Guardar
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="WEB-INF/jspf/segmentoInferior.jspf" %>
    </body>
</html>
