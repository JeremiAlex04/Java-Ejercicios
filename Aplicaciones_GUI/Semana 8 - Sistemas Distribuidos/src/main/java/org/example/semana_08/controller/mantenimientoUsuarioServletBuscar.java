package org.example.semana_08.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class mantenimientoUsuarioServletBuscar extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String criterio = request.getParameter("txtUsuarioB");
        String destino = "mantenimientoUsuarioServlet";
        if (criterio != null && !criterio.isBlank()) {
            destino += "?txtUsuarioB=" + java.net.URLEncoder.encode(criterio, java.nio.charset.StandardCharsets.UTF_8);
        }
        response.sendRedirect(destino);
    }
}
