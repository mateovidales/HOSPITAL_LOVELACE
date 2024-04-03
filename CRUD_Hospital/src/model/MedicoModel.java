package model;

import database.ConfigDB;
import entities.Especialidad;
import entities.Medico;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD{
    @Override
    public Object create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;
        try {
            String sql = "INSERT INTO medico (nombre,apellido,id_especialidad) VALUES (?,?,?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objMedico.getNombre());
            objPrepared.setString(2,objMedico.getApellidos());
            objPrepared.setInt(3,objMedico.getFk_id_especialidad());
            objPrepared.execute();
            ResultSet objResult = objPrepared.getGeneratedKeys();
            while (objResult.next()){
                objMedico.setId_medico(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "El medico fue agregado correctamente");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listMedico = new ArrayList<>();
        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id_especialidad = medico.id_especialidad;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()){
                Medico objMedico = new Medico();
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellido"));
                objMedico.setFk_id_especialidad(objResult.getInt("id_especialidad"));
                objMedico.setId_medico(objResult.getInt("id_medico"));
                objMedico.setNombre_Especialidad(objResult.getString("especialidad.nombre"));
                listMedico.add(objMedico);
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listMedico;
    }
    public List<Object> findByName(String nombre) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listMedico = new ArrayList<>();
        Medico objMedico = new Medico();
        try {
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id_especialidad = medico.id_especialidad WHERE medico.nombre like ? ;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1,"%"+nombre+"%");
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()){
                objMedico = new Medico();
                objMedico.setId_medico(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellido"));
                objMedico.setFk_id_especialidad(objResult.getInt("id_especialidad"));
                objMedico.setNombre_Especialidad(objResult.getString("especialidad.nombre"));

                listMedico.add(objMedico);
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listMedico;
    }

    public List<Object> findByMedicalSpeciality(String especialidad) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listMedico = new ArrayList<>();
        Medico objMedico = new Medico();
        try {
            String sql = "SELECT medico.nombre, medico.apellido, especialidad.nombre AS especialidad FROM medico INNER JOIN especialidad ON medico.id_especialidad = especialidad.id_especialidad WHERE especialidad.nombre LIKE ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1, "%" + especialidad + "%");
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                objMedico = new Medico();
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellido"));
                objMedico.setNombre_Especialidad(objResult.getString("especialidad"));
                listMedico.add(objMedico);
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listMedico;
    }
    public Medico findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = null;
        try {
            String sql = "SELECT * FROM medico WHERE id_medico = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,id);
            ResultSet objResult = objPrepared.executeQuery();
            if (objResult.next()){
                objMedico = new Medico();
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellidos(objResult.getString("apellido"));
                objMedico.setFk_id_especialidad(objResult.getInt("id_especialidad"));
                objMedico.setId_medico(objResult.getInt("id_medico"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return objMedico;
    }
    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;
        boolean delete = false;

        try {
            String sql = "DELETE FROM medico WHERE id_medico = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, objMedico.getId_medico());

            int arrowAffected = objPrepared.executeUpdate();
            if (arrowAffected>0){
                delete = true;
                JOptionPane.showMessageDialog(null, "El medico se elimino correctamente");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return false;
    }
    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) obj;
        boolean update = false;

        try {
            String sql = "UPDATE medico SET nombre = ?, apellido = ?, id_especialidad = ? WHERE id_medico = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1,objMedico.getNombre());
            objPrepared.setString(2, objMedico.getApellidos());
            objPrepared.setInt(3, objMedico.getFk_id_especialidad());
            objPrepared.setInt(4, objMedico.getId_medico());

            int arrowAffected = objPrepared.executeUpdate();
            if (arrowAffected>0){
                update = true;
                JOptionPane.showMessageDialog(null, "El medico se edito correctamente");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return update;
    }
}
