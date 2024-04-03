import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import entities.Especialidad;
import entities.Medico;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String opcion = "";

        do {
            opcion = JOptionPane.showInputDialog(null,
                    "BIENVENIDO AL HOSPITAL\n" +
                            "Elija su opcion:\n" +
                            "1. Especialidad\n" +
                            "2. Medico\n" +
                            "3. Paciente\n" +
                            "4. Cita\n" +
                            "5. Salir");

            switch (opcion) {
                case "1":
                    String opcionEspecialidad = JOptionPane.showInputDialog(null,
                            "ESPECIALIDAD\n" +
                                    "1. Listar especialidades\n" +
                                    "2. Crear Especialidad\n" +
                                    "3. Buscar Especialidad\n" +
                                    "4. Actualizar Especialidad\n" +
                                    "5. Eliminar Especialidad\n" +
                                    "6. Salir\n");

                    switch (opcionEspecialidad) {
                        case "1":
                            EspecialidadController.GetAll();
                            break;
                        case "2":
                            EspecialidadController.Create();
                            break;
                        case "3":
                            EspecialidadController.GetbyName();
                            break;
                        case "4":
                            EspecialidadController.Update();
                            break;
                        case "5":
                            EspecialidadController.Delete();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida para Especialidad.");
                            break;
                    }
                    break;
                case "2":
                    switch (opcion) {
                        case "2":
                            String opcionMedico = JOptionPane.showInputDialog(null,
                                    "MEDICO\n" +
                                            "1. Listar Medicos\n" +
                                            "2. Crear Medico\n" +
                                            "3. Buscar Medico por nombre \n" +
                                            "4. Buscar Medico por especialidad \n" +
                                            "5. Actualizar Medico\n" +
                                            "6. Eliminar Medico\n" +
                                            "7. Salir");

                            switch (opcionMedico) {
                                case "1":
                                    MedicoController.GetAll();
                                    break;
                                case "2":
                                    MedicoController.Create();
                                    break;
                                case "3":
                                    MedicoController.GetbyName();
                                    break;
                                case "4":
                                    MedicoController.GetMedicalSpeciality();
                                    break;
                                case "5":
                                    MedicoController.Update();
                                    break;
                                case "6":
                                    MedicoController.Delete();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opción inválida para Especialidad.");
                                    break;
                            }
                    }
                case "3":
                    switch (opcion) {
                        case "3":
                            String opcionPaciente = JOptionPane.showInputDialog(null,
                                    "CITA\n" +
                                            "1. Listar Paciente\n" +
                                            "2. Crear Paciente\n" +
                                            "3. Buscar Paciente por Cedula\n" +
                                            "4. Actualizar Paciente\n" +
                                            "5. Eliminar Paciente\n" +
                                            "6. Salir");

                            switch (opcionPaciente) {
                                case "1":
                                    PacienteController.GetAll();
                                    break;
                                case "2":
                                    PacienteController.Create();
                                    break;
                                case "3":
                                    PacienteController.GetDocument();
                                    break;
                                case "4":
                                    PacienteController.Update();
                                    break;
                                case "5":
                                    PacienteController.Delete();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opción inválida para Especialidad.");
                                    break;
                            }
                    }
                    break;
                case "4":
                    switch (opcion) {
                        case "4":
                            String opcionCita = JOptionPane.showInputDialog(null,
                                    "CITA\n" +
                                            "1. Listar Citas\n" +
                                            "2. Crear Cita\n" +
                                            "3. Buscar Cita por fecha\n" +
                                            "4. Actualizar Cita\n" +
                                            "5. Eliminar Cita\n" +
                                            "6. Salir");

                            switch (opcionCita) {
                                case "1":
                                    CitaController.GetAll();
                                    break;
                                case "2":
                                    CitaController.Create();
                                    break;
                                case "3":
                                    CitaController.GetbyDate();
                                    break;
                                case "4":
                                    CitaController.Update();
                                    break;
                                case "5":
                                    CitaController.Delete();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opción inválida para Especialidad.");
                                    break;
                            }
                            break;
                        case "5":
                            JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.");
                            break;
                    }
            }
        } while (!opcion.equals("5"));

    }
}

