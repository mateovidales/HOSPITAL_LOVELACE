package entities;

public class Medico {
    private int id_medico;
    private String nombre;
    private String apellidos;
    private int fk_id_especialidad;
    private String nombre_Especialidad;


    public Medico() {
    }

    public Medico(int id_medico, String nombre, String apellidos, int fk_id_especialidad) {
        this.id_medico = id_medico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fk_id_especialidad = fk_id_especialidad;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getFk_id_especialidad() {
        return fk_id_especialidad;
    }

    public void setFk_id_especialidad(int fk_id_especialidad) {
        this.fk_id_especialidad = fk_id_especialidad;
    }

    public String getNombre_Especialidad() {
        return nombre_Especialidad;
    }

    public void setNombre_Especialidad(String nombre_Especialidad) {
        this.nombre_Especialidad = nombre_Especialidad;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id_medico=" + id_medico +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", especialidad=" + nombre_Especialidad +
                '}';
    }

    public String toStringEspe() {
        return "Medico{" +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", especialidad=" + nombre_Especialidad +
                '}';
    }





}
