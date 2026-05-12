<%@ include file="verificaSesion.jsp" %>
<%
    if (!usuarioSesion.esAdministrador()) {
%>
<jsp:forward page="menuPrincipal.jsp">
    <jsp:param name="error" value="No tiene permisos para acceder al mantenimiento de usuarios."/>
</jsp:forward>
<%
    }
%>
