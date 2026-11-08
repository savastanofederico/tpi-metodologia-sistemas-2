public class Solicitud {
    public int id;
    public Estudiante estudiante;
    public Responsable responsable;
    public String tipo;
    public String descripcion;
    public String prioridad;
    public String estado;
    public int horasEstimadas;

    public Solicitud(int id, Estudiante estudiante, Responsable responsable,
                     String tipo, String descripcion, String prioridad,
                     String estado, int horasEstimadas) {
        this.id = id;
        this.estudiante = estudiante;
        this.responsable = responsable;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.horasEstimadas = horasEstimadas;
    }
}
