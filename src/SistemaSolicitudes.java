import java.util.List;

public class SistemaSolicitudes {
    private final ISolicitudRepository repository;
    private final ReporteadorSolicitudes reporteador;

    public SistemaSolicitudes(ISolicitudRepository repository, ReporteadorSolicitudes reporteador) {
        this.repository = repository;
        this.reporteador = reporteador;
    }

    public Solicitud registrarSolicitud(NuevaSolicitudRequest request) {
        Solicitud registrada = repository.guardar(request);
        System.out.println("[OK] Solicitud #" + registrada.getId() + " registrada para " +
                registrada.getEstudiante().getNombre() + " - Estado: " + registrada.getEstado());
        return registrada;
    }

    public void asignarResponsable(int idSolicitud, Responsable responsable) {
        Solicitud solicitud = obtenerSolicitudOError(idSolicitud);
        if (solicitud == null) return;

        try {
            solicitud.asignarResponsable(responsable);
            System.out.println("OK Solicitud #" + solicitud.getId() + " asignada a " +
                    responsable.formatearDetalle());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cambiarEstado(int idSolicitud, EstadoSolicitud nuevoEstado) {
        Solicitud solicitud = obtenerSolicitudOError(idSolicitud);
        if (solicitud == null) return;

        try {
            solicitud.cambiarEstado(nuevoEstado);
            System.out.println("OK Solicitud #" + solicitud.getId() + " cambio a estado: " + solicitud.getEstado());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void buscarPorEstado(EstadoSolicitud estadoBuscado) {
        List<Solicitud> filtradas = repository.buscarPorEstado(estadoBuscado);
        reporteador.imprimirResultadoBusqueda(estadoBuscado, filtradas);
    }

    public void generarReporteGeneral() {
        reporteador.imprimirReporteGeneral(repository.obtenerTodas());
    }

    private Solicitud obtenerSolicitudOError(int idSolicitud) {
        return repository.buscarPorId(idSolicitud).orElseGet(() -> {
            System.out.println("Error: Solicitud no encontrada");
            return null;
        });
    }
}