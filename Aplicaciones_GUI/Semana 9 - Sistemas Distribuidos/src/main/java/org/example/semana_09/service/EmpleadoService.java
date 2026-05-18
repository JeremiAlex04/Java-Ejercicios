package org.example.semana_09.service;

import org.example.semana_09.dao.EmpleadoDao;
import org.example.semana_09.model.Empleado;

import java.sql.SQLException;
import java.util.List;

public class EmpleadoService {
    private final EmpleadoDao empleadoDao = new EmpleadoDao();

    public List<Empleado> listarEmpleados() throws SQLException {
        return empleadoDao.listar();
    }

    public Empleado obtenerEmpleadoPorId(int id) throws SQLException {
        return empleadoDao.obtenerPorId(id);
    }

    public void guardarEmpleado(Empleado e) throws SQLException {
        if (e.getId() > 0) {
            empleadoDao.actualizar(e);
        } else {
            empleadoDao.insertar(e);
        }
    }

    public void eliminarEmpleado(int id) throws SQLException {
        empleadoDao.eliminar(id);
    }
}
