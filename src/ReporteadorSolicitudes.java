import java.util.List;

public class ReporteadorSolicitudes {

    public void imprimirResultadoBusqueda(EstadoSolicitud estado, List<Solicitud> solicitudes) {
        System.out.println("- Busqueda de solicitudes con estado: " + estado + " -");
        for (Solicitud s : solicitudes) {
            System.out.println(formatearResumenBusqueda(s));
        }
    }

    public void imprimirReporteGeneral(List<Solicitud> solicitudes) {
        System.out.println("* REPORTE GENERAL DE SOLICITUDES ACADEMICAS *");
        for (Solicitud s : solicitudes) {
            System.out.println(formatearLineaReporte(s));
        }
        imprimirTotalesPorEstado(solicitudes);
    }

    private String formatearResumenBusqueda(Solicitud s) {
        return "ID: " + s.getId() + " - Estudiante: " + s.getEstudiante().getNombre() +
                " - Tipo: " + s.getTipo() + " - Prioridad: " + s.getPrioridad() +
                " - Resp: " + s.obtenerNombreResponsable();
    }

    private String formatearLineaReporte(Solicitud s) {
        return "#" + s.getId() + " [" + s.getEstado() + "] " + s.getEstudiante().getNombre() +
                " (" + s.getEstudiante().getLegajo() + ") - " + s.getTipo() + ": " + s.getDescripcion() +
                " - Prioridad: " + s.getPrioridad() + " (SLA: " + s.getHorasEstimadas() + "h) - Responsable: " +
                s.obtenerDetalleResponsable();
    }

    private void imprimirTotalesPorEstado(List<Solicitud> solicitudes) {
        long abiertas = contarPorEstado(solicitudes, EstadoSolicitud.ABIERTA);
        long enRevision = contarPorEstado(solicitudes, EstadoSolicitud.EN_REVISION);
        long resueltas = contarPorEstado(solicitudes, EstadoSolicitud.RESUELTA);
        long cerradas = contarPorEstado(solicitudes, EstadoSolicitud.CERRADA);

        System.out.println("Total: " + solicitudes.size() + " - Abiertas: " + abiertas +
                " - En Revision: " + enRevision + " - Resueltas: " + resueltas + " - Cerradas: " + cerradas);
    }

    private long contarPorEstado(List<Solicitud> solicitudes, EstadoSolicitud estado) {
        long contador = 0;
        for (Solicitud s : solicitudes) {
            if (s.getEstado() == estado) contador++;
        }
        return contador;
    }
}