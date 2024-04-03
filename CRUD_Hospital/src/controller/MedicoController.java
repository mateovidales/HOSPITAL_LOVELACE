package controller;

import entities.Especialidad;
import entities.Medico;
import model.EspecialidadModel;
import model.MedicoModel;

import javax.swing.*;

public class MedicoController {
    public static void Create(){
        MedicoModel objMedicoModel = new MedicoModel();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del medico: ");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del medico: ");
        int fk_id_especialidad= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la especialidad del medico: "));
        Medico objMedico = new Medico();
        objMedico.setNombre(nombre);
        objMedico.setApellidos(apellido);
        objMedico.setFk_id_especialidad(fk_id_especialidad);


        objMedico = (Medico) objMedicoModel.create(objMedico);
        JOptionPane.showMessageDialog(null, objMedico.toString());
    }
    public static void Update(){
        MedicoModel objMedicoModel = new MedicoModel();
        GetAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog("\n Ingresa el id del medico: "));
        Medico objMedico = objMedicoModel.findById(idUpdate);
        if (objMedico == null){
            JOptionPane.showMessageDialog(null, "el medico ingresado no existe");
        }else {
            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la especialidad: "+objMedico.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido del medico: "+objMedico.getApellidos());
            int id_especialidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la especialidad del medico: "+objMedico.getFk_id_especialidad()));
            objMedico.setNombre(nombre);
            objMedico.setApellidos(apellido);
            objMedico.setFk_id_especialidad(id_especialidad);
            objMedicoModel.update(objMedico);
        }

    }

    public static void Delete(){
        MedicoModel objMedicoModel = new MedicoModel();
        GetAll();
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog("\n Ingrese el id del medico que desea borrar: "));
        Medico objMedico = objMedicoModel.findById(idDelete);
        int confirm = 1;
        if (objMedico == null){
            JOptionPane.showMessageDialog(null,"La especialidad no existe");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar la especialidad? \n"+objMedico.toString());
            if (confirm == 0) objMedicoModel.delete(objMedico);
        }
    }
    public static void GetAll(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedicos = "LISTA MEDICOS \n";
        for (Object i: objMedicoModel.findAll()){
            Medico objMedico = (Medico) i;
            listMedicos+=objMedico.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listMedicos);
    }

    public static void GetMedicalSpeciality(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedicosEspecialidad = "LISTA MEDICOS \n";
        String especialidad = JOptionPane.showInputDialog("Ingrese la especialidad a buscar: ");
        for (Object i: objMedicoModel.findByMedicalSpeciality(especialidad)){
            Medico objMedico = (Medico) i;
            listMedicosEspecialidad+=objMedico.toStringEspe()+"\n";
        }
        JOptionPane.showMessageDialog(null,listMedicosEspecialidad);
    }
    public static void GetId(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedicos = "LISTA MEDICOS \n";
        for (Object i: objMedicoModel.findAll()){
            Medico objMedico = (Medico) i;
            listMedicos+=objMedico.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listMedicos);
    }

    public static String GetString(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedicos = "LISTA ESPECIALIDADES \n";
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del medico: ");
        for (Object i: objMedicoModel.findByName(nombre)){
            Medico objMedico = (Medico) i;
            listMedicos+=objMedico.toString()+"\n";
        }
        return listMedicos;
    }

    public static void GetbyName(){
        MedicoModel objMedicoModel = new MedicoModel();
        String listMedicos = "LISTA DE MEDICOS \n";
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del medico: ");
        for (Object i: objMedicoModel.findByName(nombre)){
            Medico objMedico = (Medico) i;
            listMedicos+=objMedico.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listMedicos);
    }
}
