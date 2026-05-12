package org.example.semana_08.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class miClaseConexion {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mibase1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Lima";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "200319";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver de MySQL.", e);
        }
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }
}
