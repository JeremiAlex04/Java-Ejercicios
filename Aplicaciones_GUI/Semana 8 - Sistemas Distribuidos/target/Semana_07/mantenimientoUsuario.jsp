<%@page contentType="text/html" session="true" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="org.example.semana_08.model.Usuario" %>
<%@include file="verificaAdmin.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/segmentoEstilos.jspf" %>
        <title>Mantenimiento de usuarios</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/segmentoSuperior2.jspf" %>
        <div class="hero-panel mb-4">
            <div>
                <p class="section-title mb-2">Administrador</p>
                <h1 class="h3 mb-1">Mantenimiento de usuarios</h1>
                <p class="text-muted mb-0">Solo el administrador puede registrar y desbloquear usuarios.</p>
            </div>
            <div class="d-flex page-actions">
                <a href="menuPrincipal.jsp" class="btn btn-outline-secondary">Volver al menu</a>
                <a href="sesionCerrarServlet" class="btn btn-outline-danger">Cerrar sesion</a>
            </div>
        </div>

        <%
            String mensajeError = (String) request.getAttribute("mensajeError");
            String mensajeExito = (String) request.getAttribute("mensajeExito");
            String criterioBusqueda = (String) request.getAttribute("criterioBusqueda");
            if (criterioBusqueda == null) {
                criterioBusqueda = "";
            }
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
        %>

        <% if (mensajeError != null && !mensajeError.isBlank()) { %>
        <div class="alert alert-danger" role="alert"><%=mensajeError%></div>
        <% } %>
        <% if (mensajeExito != null && !mensajeExito.isBlank()) { %>
        <div class="alert alert-success" role="alert"><%=mensajeExito%></div>
        <% } %>

        <div class="row g-4">
            <div class="col-12 col-xl-5">
                <div class="card content-card h-100">
                    <div class="card-body p-4">
                        <h2 class="h5 mb-3">Registrar nuevo usuario</h2>
                        <form method="post" action="mantenimientoUsuarioServlet" class="row g-3">
                            <input type="hidden" name="accion" value="crear"/>
                            <div class="col-12 col-md-6">
                                <label for="txtCodUsuario" class="form-label">Codigo de usuario</label>
                                <input type="text" id="txtCodUsuario" name="txtCodUsuario" class="form-control" required/>
                            </div>
                            <div class="col-12 col-md-6">
                                <label for="txtPassword" class="form-label">Password</label>
                                <input type="text" id="txtPassword" name="txtPassword" class="form-control" required/>
                            </div>
                            <div class="col-12 col-md-6">
                                <label for="txtNombres" class="form-label">Nombres</label>
                                <input type="text" id="txtNombres" name="txtNombres" class="form-control" required/>
                            </div>
                            <div class="col-12 col-md-6">
                                <label for="txtApellidos" class="form-label">Apellidos</label>
                                <input type="text" id="txtApellidos" name="txtApellidos" class="form-control" required/>
                            </div>
                            <div class="col-12">
                                <label for="txtEmail" class="form-label">Email</label>
                                <input type="email" id="txtEmail" name="txtEmail" class="form-control" required/>
                            </div>
                            <div class="col-12 col-md-6">
                                <label for="txtPermisos" class="form-label">Permisos</label>
                                <select id="txtPermisos" name="txtPermisos" class="form-select" required>
                                    <option value="ADMINISTRADOR">Administrador</option>
                                    <option value="USUARIO">Usuario normal</option>
                                </select>
                            </div>
                            <div class="col-12 col-md-6">
                                <label for="txtNombrePc" class="form-label">Nombre de PC</label>
                                <input type="text" id="txtNombrePc" name="txtNombrePc" class="form-control" placeholder="LAB-PC-01"/>
                            </div>
                            <div class="col-12 col-md-6">
                                <label for="txtIpAcceso" class="form-label">IP</label>
                                <input type="text" id="txtIpAcceso" name="txtIpAcceso" class="form-control" placeholder="192.168.1.15"/>
                            </div>
                            <div class="col-12 col-md-6">
                                <label for="txtLugar" class="form-label">Lugar</label>
                                <input type="text" id="txtLugar" name="txtLugar" class="form-control" placeholder="Campus principal"/>
                            </div>
                            <div class="col-12">
                                <label for="txtCiudad" class="form-label">Ciudad</label>
                                <input type="text" id="txtCiudad" name="txtCiudad" class="form-control" placeholder="Lima"/>
                            </div>
                            <div class="col-12 d-grid">
                                <button type="submit" class="btn btn-primary">Guardar usuario</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-12 col-xl-7">
                <div class="card content-card h-100">
                    <div class="card-body p-4">
                        <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-3">
                            <div>
                                <h2 class="h5 mb-1">Buscar y administrar usuarios</h2>
                                <p class="text-muted mb-0">Puede filtrar por codigo, nombres, apellidos o email.</p>
                            </div>
                        </div>

                        <form method="post" action="mantenimientoUsuarioServletBuscar" class="row g-3 mb-4">
                            <div class="col-12 col-md-9">
                                <input type="text" id="txtUsuarioB" name="txtUsuarioB" class="form-control" value="<%=criterioBusqueda%>" placeholder="Buscar usuario"/>
                            </div>
                            <div class="col-12 col-md-3 d-grid">
                                <button type="submit" class="btn btn-outline-primary">Buscar</button>
                            </div>
                        </form>

                        <div class="table-responsive">
                            <table class="table align-middle">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Rol</th>
                                        <th>Estado</th>
                                        <th>Linea</th>
                                        <th>Ingresos</th>
                                        <th>Accion</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (usuarios != null && !usuarios.isEmpty()) { %>
                                    <% for (Usuario item : usuarios) { %>
                                    <tr>
                                        <td><%=item.getIdUsuario()%></td>
                                        <td><%=item.getCodUsuario()%></td>
                                        <td>
                                            <strong><%=item.getNombreCompleto()%></strong><br/>
                                            <span class="text-muted small"><%=item.getEmail()%></span>
                                        </td>
                                        <td><%=item.getPermisos()%></td>
                                        <td>
                                            <% if (item.isBloqueado()) { %>
                                            <span class="badge text-bg-danger">BLOQUEADO</span>
                                            <% } else { %>
                                            <span class="badge text-bg-success"><%=item.getEstado()%></span>
                                            <% } %>
                                        </td>
                                        <td><%=item.isEnLinea() ? "1" : "0"%></td>
                                        <td><%=item.getNumIngresos()%></td>
                                        <td>
                                            <% if (item.isBloqueado()) { %>
                                            <form method="post" action="mantenimientoUsuarioServlet">
                                                <input type="hidden" name="accion" value="desbloquear"/>
                                                <input type="hidden" name="itemAi" value="<%=item.getItemAi()%>"/>
                                                <button type="submit" class="btn btn-sm btn-warning">Desbloquear</button>
                                            </form>
                                            <% } else { %>
                                            <span class="text-muted small">Sin accion</span>
                                            <% } %>
                                        </td>
                                    </tr>
                                    <% } %>
                                    <% } else { %>
                                    <tr>
                                        <td colspan="8" class="text-center text-muted">No se encontraron usuarios para el criterio indicado.</td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="WEB-INF/jspf/segmentoInferior.jspf" %>
    </body>
</html>
