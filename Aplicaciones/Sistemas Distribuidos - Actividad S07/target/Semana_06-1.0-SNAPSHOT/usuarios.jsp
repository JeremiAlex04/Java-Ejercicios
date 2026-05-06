<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestion de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="row g-4">
        <div class="col-lg-5">
            <div class="card shadow-sm">
                <div class="card-header ${empty usuarioEdicion.itemAi ? 'bg-primary' : 'bg-warning text-dark'} text-white">
                    <h4 class="mb-0">${empty usuarioEdicion.itemAi ? 'Nuevo usuario' : 'Editar usuario'}</h4>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/UsuarioController" method="post">
                        <input type="hidden" name="accion" value="${empty usuarioEdicion.itemAi ? 'insertar' : 'actualizar'}">
                        <c:if test="${not empty usuarioEdicion.itemAi}">
                            <input type="hidden" name="id" value="${usuarioEdicion.itemAi}">
                        </c:if>

                        <div class="mb-3">
                            <label class="form-label">Codigo de usuario</label>
                            <input type="text" class="form-control" name="codUsuario" value="${usuarioEdicion.codUsuario}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Usuario</label>
                            <input type="text" class="form-control" name="usuario" value="${usuarioEdicion.usuario}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Nombres</label>
                            <input type="text" class="form-control" name="nombres" value="${usuarioEdicion.nombres}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Apellidos</label>
                            <input type="text" class="form-control" name="apellidos" value="${usuarioEdicion.apellidos}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" value="${usuarioEdicion.email}" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Permisos</label>
                            <select class="form-select" name="permisos">
                                <option value="Usuario Normal" ${usuarioEdicion.permisos == 'Usuario Normal' ? 'selected' : ''}>Usuario Normal</option>
                                <option value="Administrador" ${usuarioEdicion.permisos == 'Administrador' ? 'selected' : ''}>Administrador</option>
                            </select>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Estado</label>
                            <select class="form-select" name="estado">
                                <option value="1" ${usuarioEdicion.estado == 1 || empty usuarioEdicion.itemAi ? 'selected' : ''}>Activo</option>
                                <option value="2" ${usuarioEdicion.estado == 2 ? 'selected' : ''}>Bloqueado</option>
                                <option value="0" ${usuarioEdicion.estado == 0 && not empty usuarioEdicion.itemAi ? 'selected' : ''}>Anulado</option>
                            </select>
                        </div>

                        <div class="d-flex gap-2">
                            <button type="submit" class="btn ${empty usuarioEdicion.itemAi ? 'btn-primary' : 'btn-warning'}">
                                ${empty usuarioEdicion.itemAi ? 'Registrar' : 'Actualizar'}
                            </button>
                            <a href="${pageContext.request.contextPath}/UsuarioController?accion=listar" class="btn btn-secondary">Limpiar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-lg-7">
            <div class="card shadow-sm">
                <div class="card-header bg-dark text-white">
                    <h4 class="mb-0">Lista de usuarios</h4>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover align-middle mb-0">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Codigo</th>
                                <th>Usuario</th>
                                <th>Nombre completo</th>
                                <th>Permisos</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${empty usuarios}">
                                    <tr>
                                        <td colspan="7" class="text-center text-muted">No hay usuarios registrados.</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="usuario" items="${usuarios}">
                                        <tr>
                                            <td>${usuario.itemAi}</td>
                                            <td>${usuario.codUsuario}</td>
                                            <td>${usuario.usuario}</td>
                                            <td>${usuario.nombres} ${usuario.apellidos}</td>
                                            <td>${usuario.permisos}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${usuario.estado == 1}">Activo</c:when>
                                                    <c:when test="${usuario.estado == 2}">Bloqueado</c:when>
                                                    <c:otherwise>Anulado</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <a class="btn btn-sm btn-outline-warning"
                                                   href="${pageContext.request.contextPath}/UsuarioController?accion=editar&id=${usuario.itemAi}">
                                                    Editar
                                                </a>
                                                <a class="btn btn-sm btn-outline-danger"
                                                   href="${pageContext.request.contextPath}/UsuarioController?accion=eliminar&id=${usuario.itemAi}"
                                                   onclick="return confirm('¿Deseas eliminar este usuario?');">
                                                    Eliminar
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
