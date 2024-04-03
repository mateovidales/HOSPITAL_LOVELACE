package model;

import database.ConfigDB;
import entities.Cita;
import entities.Medico;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class CitaModel implements CRUD{
    @Override
    public Object create(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        try {
            String sql = "INSERT INTO cita (fecha_cita,hora_cita,motivo,id_paciente,id_medico) VALUES (?,?,?,?,?);";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objPrepared.setDate(1,objCita.getFecha_cita());
            objPrepared.setTime(2,objCita.getHora());
            objPrepared.setString(3,objCita.getMotivo());
            objPrepared.setInt(4,objCita.getFk_id_paciente());
            objPrepared.setInt(5,objCita.getFk_id_medico());
            objPrepared.execute();
            ResultSet objResult = objPrepared.getGeneratedKeys();
            while (objResult.next()){
                objCita.setId_cita(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "la cita fue agregada correctamente");
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return objCita;
    }
    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listCitas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cita INNER JOIN paciente ON paciente.id_paciente = cita.id_paciente INNER JOIN medico ON medico.id_medico = cita.id_medico;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()){
                Cita objCita = new Cita();
                objCita.setId_cita(objResult.getInt("id_cita"));
                objCita.setFecha_cita(objResult.getDate("fecha_cita"));
                objCita.setHora(objResult.getTime("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));
                objCita.setFk_id_paciente(objResult.getInt("id_paciente"));
                objCita.setFk_id_medico(objResult.getInt("id_medico"));
                objCita.setPaciente(objResult.getString("paciente.nombre"));
                objCita.setMedico(objResult.getString("medico.nombre"));

                listCitas.add(objCita);
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listCitas;
    }
    public List<Object> findByDate(Date fecha) {
        Connection objConnection = ConfigDB.openConnection();
        List<Object>listCitas = new ArrayList<>();
        Cita objCita = new Cita();
        try {
            String sql = "SELECT * FROM cita INNER JOIN paciente ON paciente.id_paciente = cita.id_paciente INNER JOIN medico ON medico.id_medico = cita.id_medico WHERE cita.fecha_cita = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setDate(1,fecha);
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()){
                objCita.setId_cita(objResult.getInt("id_cita"));
                objCita.setFecha_cita(objResult.getDate("fecha_cita"));
                objCita.setHora(objResult.getTime("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));
                objCita.setFk_id_paciente(objResult.getInt("id_paciente"));
                objCita.setFk_id_medico(objResult.getInt("id_medico"));
                objCita.setPaciente(objResult.getString("paciente.nombre"));
                objCita.setMedico(objResult.getString("medico.nombre"));
                listCitas.add(objCita);
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return listCitas;
    }
    public Cita findById(int id){
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = null;
        try {
            String sql = "SELECT * FROM cita WHERE id_cita = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            objPrepared.setInt(1,id);
            ResultSet objResult = objPrepared.executeQuery();
            if (objResult.next()){
                objCita = new Cita();
                objCita.setFecha_cita(objResult.getDate("fecha_cita"));
                objCita.setHora(objResult.getTime("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));
                objCita.setFk_id_paciente(objResult.getInt("id_paciente"));
                objCita.setFk_id_medico(objResult.getInt("id_medico"));
                objCita.setId_cita(objResult.getInt("id_cita"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();
        return objCita;
    }
    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) obj;
        boolean delete = false;

        try {
            String sql = "DELETE FROM cita WHERE id_cita = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setInt(1, objCita.getId_cita());

            int arrowAffected = objPrepared.executeUpdate();
            if (arrowAffected>0){
                delete = true;
                JOptionPane.showMessageDialog(null, "La cita se elimino correctamente");
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
        Cita objCita = (Cita) obj;
        boolean update = false;

        try {
            String sql = "UPDATE cita SET fecha_cita = ?, hora_cita = ?, motivo = ?, id_paciente = ?, id_medico = ? WHERE id_cita = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            objPrepared.setDate(1, objCita.getFecha_cita());
            objPrepared.setTime(2,objCita.getHora());
            objPrepared.setString(3,objCita.getMotivo());
            objPrepared.setInt(4, objCita.getFk_id_paciente());
            objPrepared.setInt(5, objCita.getFk_id_medico());
            objPrepared.setInt(6, objCita.getId_cita());

            int arrowAffected = objPrepared.executeUpdate();
            if (arrowAffected>0){
                update = true;
                JOptionPane.showMessageDialog(null, "La cita se edito correctamente");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();
        return update;
    }
}

