package controller;

import entities.Cita;
import entities.Medico;
import entities.Paciente;
import model.CitaModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;

public class PacienteController {
    public static void Create(){
        PacienteModel objPacienteModel = new PacienteModel();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del paciente");
        Date fecha_nacimiento = Date.valueOf(JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del paciente: "));
        String documento_identidad = JOptionPane.showInputDialog("Ingrese el documento de identidad del paciente: ");

        Paciente objPaciente = new Paciente();
        objPaciente.setNombre(nombre);
        objPaciente.setApellidos(apellido);
        objPaciente.setFecha_nacimiento(fecha_nacimiento);
        objPaciente.setDocumento_identidad(documento_identidad);

        objPaciente = (Paciente) objPacienteModel.create(objPaciente);
        JOptionPane.showMessageDialog(null, objPaciente.toString());
    }

    public static void Update(){
        PacienteModel objPacienteModel = new PacienteModel();
        GetAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog("\n Ingresa el id del paciente: "));
        Paciente objPaciente = objPacienteModel.findById(idUpdate);
        if (objPaciente == null){
            JOptionPane.showMessageDialog(null, "El paciente ingresado no existe");
        }else {

            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del paciente"+objPaciente.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido del paciente"+objPaciente.getApellidos());
            Date fecha_nacimiento = Date.valueOf(JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del paciente: "+objPaciente.getFecha_nacimiento()));
            String documento_identidad = JOptionPane.showInputDialog("Ingrese el documento de identidad del paciente: "+objPaciente.getDocumento_identidad());
            objPaciente.setNombre(nombre);
            objPaciente.setApellidos(apellido);
            objPaciente.setFecha_nacimiento(fecha_nacimiento);
            objPaciente.setDocumento_identidad(documento_identidad);

            objPacienteModel.update(objPaciente);
        }

    }

    public static void Delete(){
        PacienteModel objPacienteModel = new PacienteModel();
        GetAll();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog("\n Ingrese el id del paciente que desea borrar: "));
        Paciente objPaciente = objPacienteModel.findById(idDelete);
        int confirm = 1;
        if (objPaciente == null){
            JOptionPane.showMessageDialog(null,"El paciente no existe");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar el paciente? \n"+objPaciente.toString());
            if (confirm == 0) objPacienteModel.delete(objPaciente);
        }
    }
    public static void GetAll(){
        PacienteModel objPacienteModel = new PacienteModel();
        String listPacientes = "LISTA PACIENTES \n";
        for (Object i: objPacienteModel.findAll()){
            Paciente objPaciente = (Paciente) i;
            listPacientes+=objPaciente.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listPacientes);
    }

    public static void GetDocument(){
        PacienteModel objPacienteModel = new PacienteModel();
        String paciente = "PACIENTE:  \n";
        String documento = JOptionPane.showInputDialog("Ingrese el documento del paciente: ");
        Paciente pacientenew = objPacienteModel.findByDocument(documento);
        JOptionPane.showMessageDialog(null, pacientenew.toString());
    }
}
