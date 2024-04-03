package model;

import database.ConfigDB;
import entities.Cita;
import entities.Medico;
import entities.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD{
    @Override
    public Object create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;
        try {
            String sql = "INSERT INTO paciente (nombre,apellido,fecha_nacimiento,documento_identidad) VALUES (?,?,?,?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepared.setString(1, objPaciente.getNombre());
            objPrepared.setString(2, objPaciente.getApellidos());
            objPrepared.setDate(3, objPaciente.getFecha_nacimiento());
            objPrepared.setString(4,objPaciente.getDocumento_identidad());
            objPrepared.execute();
            ResultSet objResult = objPrepared.getGeneratedKeys();
            while (objResult.next()) {
                objPaciente.setId_paciente(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "El paciente fue agregado correctamente");
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listPacientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM paciente;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellido"));
                objPaciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPacientes.add(objPaciente);
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listPacientes;
    }
    public Paciente findByDocument(String documento) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = "SELECT * FROM paciente WHERE documento_identidad = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setString(1,documento);
            ResultSet objResult = objPrepared.executeQuery();
            if (objResult.next()){
                objPaciente = new Paciente();
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellido"));
                objPaciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }
    public Paciente findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = null;
        try {
            String sql = "SELECT * FROM paciente WHERE id_paciente = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,id);
            ResultSet objResult = objPrepared.executeQuery();
            if (objResult.next()){
                objPaciente = new Paciente();
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellido"));
                objPaciente.setFecha_nacimiento(objResult.getDate("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }
    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) obj;
        boolean delete = false;

        try {
            String sql = "DELETE FROM paciente WHERE id_paciente = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, objPaciente.getId_paciente());

            int arrowAffected = objPrepared.executeUpdate();
            if (arrowAffected>0){
                delete = true;
                JOptionPane.showMessageDialog(null, "El paciente se elimino correctamente");
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
        Paciente objPaciente = (Paciente) obj;
        boolean update = false;

        try {
            String sql = "UPDATE paciente SET nombre = ?, apellido = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE id_paciente = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setString(1, objPaciente.getNombre());
            objPrepared.setString(2, objPaciente.getApellidos());
            objPrepared.setDate(3, objPaciente.getFecha_nacimiento());
            objPrepared.setString(4,objPaciente.getDocumento_identidad());
            objPrepared.setInt(5, objPaciente.getId_paciente());

            int arrowAffected = objPrepared.executeUpdate();
            if (arrowAffected>0){
                update = true;
                JOptionPane.showMessageDialog(null, "el paciente se edito correctamente");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return update;
    }
}
