<%@page contentType="text/html" session="true" pageEncoding="UTF-8"%>
<%@include file="verificaSesion.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/segmentoEstilos.jspf" %>
        <title>Menu principal</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/segmentoSuperior2.jspf" %>
        <div class="hero-panel mb-4">
            <div>
                <p class="section-title mb-2">Panel principal</p>
                <h1 class="h3 mb-1">Bienvenido, <%=varNombresMostrar%></h1>
                <p class="text-muted mb-0">Rol actual: <strong><%=usuarioSesion.getPermisos()%></strong> | En linea: <strong><%=usuarioSesion.isEnLinea() ? "Si" : "No"%></strong></p>
            </div>
            <a href="sesionCerrarServlet" class="btn btn-outline-danger">Cerrar sesion</a>
        </div>

        <% String mensajePantalla = request.getParameter("error"); %>
        <% if (mensajePantalla != null && !mensajePantalla.isBlank()) { %>
        <div class="alert alert-warning" role="alert"><%=mensajePantalla%></div>
        <% } %>

        <section class="mb-4">
            <p class="section-title mb-3">Opciones del sistema</p>
            <div class="row g-3">
                <% if (usuarioSesion.esAdministrador()) { %>
                <div class="col-12 col-md-6">
                    <a href="mantenimientoUsuarioServlet" class="menu-link">
                        <div class="card content-card h-100">
                            <div class="card-body">
                                <h2 class="h5">Mantenimiento de usuarios</h2>
                                <p class="text-muted mb-0">Registrar, buscar y desbloquear usuarios del sistema.</p>
                            </div>
                        </div>
                    </a>
                </div>
                <% } %>
                <div class="col-12 col-md-6">
                    <div class="card content-card h-100">
                        <div class="card-body">
                            <h2 class="h5">Mi sesion</h2>
                            <p class="text-muted mb-0">Su ultimo acceso y el numero de ingresos se almacenan en la base de datos.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section>
            <div class="card content-card">
                <div class="card-body">
                    <h2 class="h5">Reglas aplicadas en esta actividad</h2>
                    <ul class="mb-0">
                        <li>Validacion del usuario y la clave.</li>
                        <li>Bloqueo automatico tras 3 intentos fallidos.</li>
                        <li>Control de permisos para administrador y usuario normal.</li>
                        <li>Actualizacion del estado <code>en_linea</code> al iniciar y cerrar sesion.</li>
                    </ul>
                </div>
            </div>
        </section>

        <%@include file="WEB-INF/jspf/segmentoInferior.jspf" %>
    </body>
</html>
