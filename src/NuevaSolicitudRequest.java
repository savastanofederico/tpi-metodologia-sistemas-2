public class NuevaSolicitudRequest {
    private final Estudiante estudiante;
    private final TipoSolicitud tipo;
    private final String descripcion;
    private final Prioridad prioridad;
    private final Responsable responsableInicial;

    public NuevaSolicitudRequest(Estudiante estudiante, TipoSolicitud tipo,
                                 String descripcion, Prioridad prioridad,
                                 Responsable responsableInicial) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("Descripcion vacia");
        }
        this.estudiante = estudiante;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.responsableInicial = responsableInicial;
    }

    public Estudiante getEstudiante() { return estudiante; }
    public TipoSolicitud getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public Prioridad getPrioridad() { return prioridad; }
    public Responsable getResponsableInicial() { return responsableInicial; }
}