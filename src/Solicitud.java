public class Solicitud {
    private final int id;
    private final Estudiante estudiante;
    private Responsable responsable;
    private final TipoSolicitud tipo;
    private final String descripcion;
    private final Prioridad prioridad;
    private EstadoSolicitud estado;
    private final int horasEstimadas;

    public Solicitud(int id, NuevaSolicitudRequest request) {
        this.id = id;
        this.estudiante = request.getEstudiante();
        this.responsable = request.getResponsableInicial();
        this.tipo = request.getTipo();
        this.descripcion = request.getDescripcion();
        this.prioridad = request.getPrioridad();
        this.horasEstimadas = tipo.calcularHorasEstimadas(prioridad);
        this.estado = determinarEstadoInicial(responsable);
    }

    private EstadoSolicitud determinarEstadoInicial(Responsable resp) {
        if (resp != null && resp.isActivo()) {
            return EstadoSolicitud.EN_REVISION;
        }
        return EstadoSolicitud.ABIERTA;
    }

    // con move method la responsabilidad de asignar y validar estado vive en Solicitud
    public void asignarResponsable(Responsable nuevoResponsable) {
        validarNoCerrada("No se puede reasignar una solicitud cerrada");
        this.responsable = nuevoResponsable;
        if (this.estado == EstadoSolicitud.ABIERTA && nuevoResponsable.isActivo()) {
            this.estado = EstadoSolicitud.EN_REVISION;
        }
    }

    // con move method la transicion de estado se controla dentro de la entidad
    public void cambiarEstado(EstadoSolicitud nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("Estado invalido");
        }
        validarNoCerrada("La solicitud ya esta cerrada");
        this.estado = nuevoEstado;
    }

    private void validarNoCerrada(String mensajeError) {
        if (this.estado == EstadoSolicitud.CERRADA) {
            throw new IllegalStateException(mensajeError);
        }
    }

    public int getId() { return id; }
    public Estudiante getEstudiante() { return estudiante; }
    public Responsable getResponsable() { return responsable; }
    public TipoSolicitud getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public Prioridad getPrioridad() { return prioridad; }
    public EstadoSolicitud getEstado() { return estado; }
    public int getHorasEstimadas() { return horasEstimadas; }

    public String obtenerNombreResponsable() {
        return (responsable != null) ? responsable.getNombre() : "Sin asignar";
    }

    public String obtenerDetalleResponsable() {
        return (responsable != null) ? responsable.formatearDetalle() : "Sin asignar";
    }
}