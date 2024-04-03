package controller;

import database.ConfigDB;
import entities.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;
import java.sql.Connection;

public class EspecialidadController {

    public static void Create(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la nueva especialidad: ");
        String descripcion = JOptionPane.showInputDialog("Ingrese una descripcion de la nueva especialidad: ");
        Especialidad objEspecialidad = new Especialidad();
        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descripcion);

        objEspecialidad = (Especialidad) objEspecialidadModel.create(objEspecialidad);
        JOptionPane.showMessageDialog(null, objEspecialidad.toString());
    }

    public static void Update(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        GetAll();
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog("\n Ingresa el id de la especialidad: "));
        Especialidad objEspecialidad = objEspecialidadModel.findById(idUpdate);
        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null, "La especialidad ingresada no existe");
        }else {
            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la especialidad: "+objEspecialidad.getNombre());
            String descripcion = JOptionPane.showInputDialog("Ingrese la nueva descripcion de la especialidad: "+objEspecialidad.getDescripcion());
            objEspecialidad.setNombre(nombre);
            objEspecialidad.setDescripcion(descripcion);
            objEspecialidadModel.update(objEspecialidad);
        }

    }

    public static void Delete(){
    EspecialidadModel objEspecialidadModel = new EspecialidadModel();
    GetAll();
    int idDelete = Integer.parseInt(JOptionPane.showInputDialog("\n Ingrese el id de la especialidad que desea borrar: "));
    Especialidad objEspecialidad = objEspecialidadModel.findById(idDelete);
    int confirm = 1;
    if (objEspecialidad == null){
        JOptionPane.showMessageDialog(null,"La especialidad no existe");
    }else {
        confirm = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar la especialidad? \n"+objEspecialidad.toString());
        if (confirm == 0) objEspecialidadModel.delete(objEspecialidad);
    }
    }
    public static void GetAll(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = "LISTA ESPECIALIDADES \n";
        for (Object i: objEspecialidadModel.findAll()){
            Especialidad objEspecialidad = (Especialidad) i;
            listEspecialidad+=objEspecialidad.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listEspecialidad);
    }

    public static void GetId(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = "LISTA ESPECIALIDADES \n";
        for (Object i: objEspecialidadModel.findAll()){
            Especialidad objEspecialidad = (Especialidad) i;
            listEspecialidad+=objEspecialidad.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null, listEspecialidad);
    }

    public static String GetString(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = "LISTA ESPECIALIDADES \n";
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la especialidad: ");
        for (Object i: objEspecialidadModel.findByName(nombre)){
            Especialidad objEspecialidad = (Especialidad) i;
            listEspecialidad+=objEspecialidad.toString()+"\n";
        }
        return listEspecialidad;
    }

    public static void GetbyName(){
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();
        String listEspecialidad = "LISTA ESPECIALIDADES \n";
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la especialidad: ");
        for (Object i: objEspecialidadModel.findByName(nombre)){
            Especialidad objEspecialidad = (Especialidad) i;
            listEspecialidad+=objEspecialidad.toString()+"\n";
        }
        JOptionPane.showMessageDialog(null,listEspecialidad);
    }
}

