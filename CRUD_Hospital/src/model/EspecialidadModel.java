package model;

import database.ConfigDB;
import entities.Especialidad;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EspecialidadModel implements CRUD{
    @Override
    public Object create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;
        try {
            String sql = "INSERT INTO especialidad (nombre,descripcion) VALUES (?,?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objEspecialidad.getNombre());
            objPrepared.setString(2,objEspecialidad.getDescripcion());
            objPrepared.execute();
            ResultSet objResult = objPrepared.getGeneratedKeys();
            while (objResult.next()){
                objEspecialidad.setId_especialidad(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "La especialidad fue agregada correctamente");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }
    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listEspecialidad = new ArrayList<>();
        try {
            String sql = "SELECT * FROM especialidad;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()){
                Especialidad objEspecialidad = new Especialidad();
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                listEspecialidad.add(objEspecialidad);
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listEspecialidad;
    }
    public List<Object> findByName(String nombre) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listEspecialidad = new ArrayList<>();
        Especialidad objEspecialidad = new Especialidad();
        try {
            String sql = "SELECT * FROM especialidad WHERE nombre like ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1,"%"+nombre+"%");
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()){
                objEspecialidad = new Especialidad();
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                listEspecialidad.add(objEspecialidad);
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listEspecialidad;
    }
    public Especialidad findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = null;
        try {
            String sql = "SELECT * FROM especialidad WHERE id_especialidad = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,id);
            ResultSet objResult = objPrepared.executeQuery();
            if (objResult.next()){
                objEspecialidad = new Especialidad();
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }
    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = (Especialidad) obj;
        boolean update = false;

        try {
            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id_especialidad = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,objEspecialidad.getNombre());
            objPrepared.setString(2,objEspecialidad.getDescripcion());
            objPrepared.setInt(3,objEspecialidad.getId_especialidad());

            int arrowAffected = objPrepared.executeUpdate();
            if (arrowAffected>0){
                update = true;
                JOptionPane.showMessageDialog(null, "La especialidad se edito correctamente");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return update;
    }
    @Override
    public boolean delete(Object obj) {
    Connection objConnection = ConfigDB.openConnection();
    Especialidad objEspecialidad = (Especialidad) obj;
    boolean delete = false;
    try {
        String sql = "DELETE FROM especialidad WHERE id_especialidad = ?;";
        PreparedStatement objPrepared = objConnection.prepareStatement(sql);

        objPrepared.setInt(1, objEspecialidad.getId_especialidad());

        int arrowAffected = objPrepared.executeUpdate();
        if (arrowAffected > 0){
            delete = true;
            JOptionPane.showMessageDialog(null, "La especialidad fue borrada correctamente");
        }
    }catch (Exception error){
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
    ConfigDB.closeConnection();
    return false;
    }
}
