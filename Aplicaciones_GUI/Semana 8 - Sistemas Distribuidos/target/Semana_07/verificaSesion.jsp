<%@ page session="true" %>
<%@ page import="org.example.semana_08.model.Usuario" %>
<%
    Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSesion");
    if (usuarioSesion == null) {
%>
<jsp:forward page="index.jsp">
    <jsp:param name="error" value="Ingrese sus credenciales para continuar."/>
</jsp:forward>
<%
    }
    String varNombresMostrar = usuarioSesion == null ? "" : usuarioSesion.getNombreCompleto();
%>
