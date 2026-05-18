<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/segmentoEstilos.jspf" %>
        <title>Ingreso al sistema</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/segmentoSuperior.jspf" %>
        <div class="row justify-content-center align-items-center auth-shell">
            <div class="col-12 col-md-10 col-lg-6">
                <div class="card auth-card">
                    <div class="card-body p-4 p-lg-5">
                        <div class="text-center mb-4">
                            <span class="badge rounded-pill text-bg-primary mb-3">Actividad grupal</span>
                            <h1 class="h3 mb-2">Control de usuarios con sesiones y capas</h1>
                            <p class="text-muted mb-0">Ingrese sus credenciales. Tras 3 intentos fallidos el usuario se bloquea.</p>
                        </div>
                        <%
                            String mensajeError = (String) request.getAttribute("mensajeError");
                            if (mensajeError == null) {
                                mensajeError = request.getParameter("error");
                            }
                            String usuarioIngresado = (String) request.getAttribute("usuarioIngresado");
                            if (usuarioIngresado == null) {
                                usuarioIngresado = "";
                            }
                        %>
                        <% if (mensajeError != null && !mensajeError.isBlank()) { %>
                        <div class="alert alert-danger" role="alert">
                            <%=mensajeError%>
                        </div>
                        <% } %>
                        <form method="post" action="sesionVerificaServlet">
                            <div class="mb-3">
                                <label for="txtUsuario" class="form-label">Codigo de usuario</label>
                                <input type="text" id="txtUsuario" name="txtUsuario" class="form-control form-control-lg" placeholder="Ejemplo: admin" value="<%=usuarioIngresado%>" required autofocus/>
                            </div>
                            <div class="mb-4">
                                <label for="txtPassword" class="form-label">Password</label>
                                <input type="password" id="txtPassword" name="txtPassword" class="form-control form-control-lg" placeholder="Ingrese su password" required/>
                            </div>
                            <div class="d-grid">
                                <button class="btn btn-primary btn-lg" type="submit">Ingresar</button>
                            </div>
                        </form>
                        <div class="helper-panel mt-4">
                            <p class="mb-1"><strong>Usuarios de ejemplo</strong></p>
                            <p class="mb-0 text-muted">Administrador: <code>admin / Admin123*</code> | Usuario: <code>usuario / User123*</code></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="WEB-INF/jspf/segmentoInferior.jspf" %>
    </body>
</html>
