package controller;

import entities.Cita;
import entities.Especialidad;
import entities.Medico;
import model.CitaModel;
import model.EspecialidadModel;
import model.MedicoModel;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Time;

public class CitaController {
    public static void Create(){
        CitaModel objCitaModel = new CitaModel();
        Date fecha_cita = Date.valueOf(JOptionPane.showInputDialog("Ingrese la fecha de la nueva especialidad (AAAA-MM-DD): "));
        Time hora = Time.valueOf(JOptionPane.showInputDialog("Ingrese la hora de la cita: "));
        String motivo = JOptionPane.showInputDialog("Ingrese el motivo de la cita: ");
        int fk_id_paciente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del paciente: "));
        int fk_id_medico = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del medico: "));
        Cita objCita = new Cita();
        objCita.setFecha_cita(fecha_cita);
        objCita.setHora(hora);
        objCita.setMotivo(motivo);
        objCita.setFk_id_paciente(fk_id_paciente);
        objCita.setFk_id_medico(fk_id_medico);

        objCita = (Cita) objCitaModel.create(objCita);
        JOptionPane.showMessageDialog(null, objCita.toString());
    }

    public static void Update(){
        CitaModel objCitaModel = new CitaModel();
        GetAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog("\n Ingresa el id de la cita: "));
        Cita objCita = objCitaModel.findById(idUpdate);
        if (objCita == null){
            JOptionPane.showMessageDialog(null, "La cita ingresada no existe");
        }else {

            Date fecha_cita = Date.valueOf(JOptionPane.showInputDialog("Ingrese la nueva fecha de la cita: "+objCita.getFecha_cita()));
            Time hora_cita = Time.valueOf(JOptionPane.showInputDialog("Ingrese la nueva hora de la cita: "+objCita.getHora()));
            String motivo_cita = JOptionPane.showInputDialog("Ingrese el motivo de la cita: "+objCita.getMotivo());
            int id_paciente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del paciente: "+objCita.getFk_id_paciente()));
            int id_medico = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del medico: "+objCita.getFk_id_medico()));
            objCita.setFecha_cita(fecha_cita);
            objCita.setHora(hora_cita);
            objCita.setMotivo(motivo_cita);
            objCita.setFk_id_paciente(id_paciente);
            objCita.setFk_id_medico(id_medico);

            objCitaModel.update(objCita);
        }

    }

    public static void Delete(){
        CitaModel objCitaModel = new CitaModel();
        GetAll();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog("\n Ingrese el id de la cita que desea borrar: "));
        Cita objCita = objCitaModel.findById(idDelete);
        int confirm = 1;
        if (objCita == null){
            JOptionPane.showMessageDialog(null,"La cita no existe");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar la cita? \n"+objCita.toString());
            if (confirm == 0) objCitaModel.delete(objCita);
        }
    }
    public static void GetAll(){
        CitaModel objCitaModel = new CitaModel();
        String listCitas = "LISTA CITAS \n";
        for (Object i: objCitaModel.findAll()){
            Cita objCita = (Cita) i;
            listCitas+=objCita.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listCitas);
    }

    public static void GetId(){
        CitaModel objCitaModel = new CitaModel();
        String listCita = "LISTA DE CITAS \n";
        for (Object i: objCitaModel.findAll()){
            Cita objCita = (Cita) i;
            listCita+=objCita.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listCita);
    }

    public static void GetbyDate(){
        CitaModel objCitaModel = new CitaModel();
        String listCita = "LISTA DE CITAS \n";
        Date fecha_cita = Date.valueOf(JOptionPane.showInputDialog("Ingrese la fecha de la cita (AAAA-MM-DD): "));
        for (Object i: objCitaModel.findByDate(fecha_cita)){
            Cita objCita = (Cita) i;
            listCita+=objCita.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listCita);
    }
}
