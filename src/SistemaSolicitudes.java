import java.util.ArrayList;
import java.util.List;

public class SistemaSolicitudes {
    public List<Solicitud> lista = new ArrayList<>();
    public int contador = 1;

    // long parameter list, long method, switch if complejos, duplicated code
    public void procesarYRegistrarSolicitud(String legajoEst, String nombreEst, String emailEst,
                                            String tipo, String desc, String prio,
                                            String nombreResp, String areaResp, boolean respActivo) {
        if (legajoEst == null || legajoEst.trim().isEmpty() || nombreEst == null || nombreEst.trim().isEmpty()) {
            System.out.println("Error: Datos de estudiante invalidos");
            return;
        }
        if (desc == null || desc.trim().isEmpty()) {
            System.out.println("Error: Descripcion vacia");
            return;
        }

        int horas = 0;
        if (tipo.equals("EQUIVALENCIA")) {
            if (prio.equals("ALTA")) {
                horas = 24;
            } else if (prio.equals("MEDIA")) {
                horas = 72;
            } else {
                horas = 120;
            }
        } else if (tipo.equals("CERTIFICADO")) {
            if (prio.equals("ALTA")) {
                horas = 12;
            } else {
                horas = 48;
            }
        } else if (tipo.equals("MESA_EXAMEN")) {
            horas = 36;
        } else {
            horas = 96;
        }

        Estudiante e = new Estudiante(legajoEst, nombreEst, emailEst);
        Responsable r = null;
        if (nombreResp != null && !nombreResp.isEmpty()) {
            r = new Responsable(nombreResp, areaResp, respActivo);
        }

        String estInicial = "ABIERTA";
        if (r != null && r.activo) {
            estInicial = "EN_REVISION";
        }

        Solicitud s = new Solicitud(contador++, e, r, tipo, desc, prio, estInicial, horas);
        lista.add(s);
        System.out.println("OK Solicitud #" + s.id + " registrada para " + s.estudiante.nombre + " - Estado: " + s.estado);
    }

    public void asignarResponsable(int idSolicitud, String nombreResp, String areaResp, boolean activo) {
        if (nombreResp == null || nombreResp.trim().isEmpty()) {
            System.out.println("Error: Nombre de responsable invalido");
            return;
        }
        for (Solicitud s : lista) {
            if (s.id == idSolicitud) {
                if (s.estado.equals("CERRADA")) {
                    System.out.println("Error: No se puede reasignar una solicitud cerrada");
                    return;
                }
                s.responsable = new Responsable(nombreResp, areaResp, activo);
                if (s.estado.equals("ABIERTA") && activo) {
                    s.estado = "EN_REVISION";
                }
                System.out.println("[OK] Solicitud #" + s.id + " asignada a " + s.responsable.nombre + " (" + s.responsable.area + ")");
                return;
            }
        }
        System.out.println("Error: Solicitud no encontrada");
    }

    public void cambiarEstado(int idSolicitud, String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            System.out.println("Error: Estado invalido");
            return;
        }
        for (Solicitud s : lista) {
            if (s.id == idSolicitud) {
                if (s.estado.equals("CERRADA")) {
                    System.out.println("Error: La solicitud ya esta cerrada");
                    return;
                }
                s.estado = nuevoEstado;
                System.out.println("OK Solicitud #" + s.id + " cambio a estado: " + s.estado);
                return;
            }
        }
        System.out.println("Error: Solicitud no encontrada");
    }

    public void buscarPorEstado(String estadoBuscado) {
        System.out.println("- Busqueda de solicitudes con estado: " + estadoBuscado + " -");
        for (Solicitud s : lista) {
            if (s.estado.equalsIgnoreCase(estadoBuscado)) {
                String resp = (s.responsable != null) ? s.responsable.nombre : "Sin asignar";
                System.out.println("ID: " + s.id + " - Estudiante: " + s.estudiante.nombre +
                        " | Tipo: " + s.tipo + " - Prioridad: " + s.prioridad + " | Resp: " + resp);
            }
        }
    }

    public void generarReporteGeneral() {
        System.out.println("* REPORTE GENERAL DE SOLICITUDES ACADEMICAS *");
        int abiertas = 0;
        int enRevision = 0;
        int resueltas = 0;
        int cerradas = 0;

        for (Solicitud s : lista) {
            String resp = (s.responsable != null) ? s.responsable.nombre + " (" + s.responsable.area + ")" : "Sin asignar";
            System.out.println("#" + s.id + " [" + s.estado + "] " + s.estudiante.nombre +
                    " (" + s.estudiante.legajo + ") - " + s.tipo + ": " + s.descripcion +
                    " - Prioridad: " + s.prioridad + " (SLA: " + s.horasEstimadas + "h) - Responsable: " + resp);

            if (s.estado.equals("ABIERTA")) abiertas++;
            else if (s.estado.equals("EN_REVISION")) enRevision++;
            else if (s.estado.equals("RESUELTA")) resueltas++;
            else if (s.estado.equals("CERRADA")) cerradas++;
        }
        System.out.println("Total: " + lista.size() + " - Abiertas: " + abiertas +
                " - En Revision: " + enRevision + " - Resueltas: " + resueltas + " - Cerradas: " + cerradas);
    }
}