public class PruebasVerificacion {
    public static void main(String[] args) {
        System.out.println("* INICIANDO PRUEBAS DE VERIFICACION POST-REFACTORING *");
        ISolicitudRepository repo = new SolicitudRepositoryMemoria();
        ReporteadorSolicitudes rep = new ReporteadorSolicitudes();
        SistemaSolicitudes sistema = new SistemaSolicitudes(repo, rep);

        // caso 1: registro con responsable activo pasa a EN_REVISION y calcula SLA correcto
        Solicitud s1 = sistema.registrarSolicitud(new NuevaSolicitudRequest(
                new Estudiante("L-1001", "Federico Savastano", "fsavastano@alumnos.utn.edu.ar"),
                TipoSolicitud.EQUIVALENCIA, "Equivalencia", Prioridad.ALTA,
                new Responsable("Oscar Londero", "Coordinacion General", true)
        ));
        verificar("Caso 1 - Estado inicial EN_REVISION", s1.getEstado() == EstadoSolicitud.EN_REVISION);
        verificar("Caso 1 - Calculo SLA 24 horas", s1.getHorasEstimadas() == 24);

        // caso 2: registro sin responsable inicia en ABIERTA y luego al asignar pasa a EN_REVISION
        Solicitud s2 = sistema.registrarSolicitud(new NuevaSolicitudRequest(
                new Estudiante("L-1002", "Juan Martinez", "jmartinez@alumnos.utn.edu.ar"),
                TipoSolicitud.CERTIFICADO, "Certificado regular", Prioridad.MEDIA, null
        ));
        verificar("Caso 2 - Estado inicial ABIERTA", s2.getEstado() == EstadoSolicitud.ABIERTA);
        sistema.asignarResponsable(s2.getId(), new Responsable("Samuel Tocaimaza", "Comision 12", true));
        verificar("Caso 2 - Cambio a EN_REVISION tras asignar", s2.getEstado() == EstadoSolicitud.EN_REVISION);

        // caso 3: solicitud CERRADA no permite cambiar estado ni reasignar responsable
        sistema.cambiarEstado(s1.getId(), EstadoSolicitud.CERRADA);
        sistema.cambiarEstado(s1.getId(), EstadoSolicitud.ABIERTA);
        verificar("Caso 3 - Bloqueo de reapertura en solicitud CERRADA", s1.getEstado() == EstadoSolicitud.CERRADA);

        System.out.println("* TODAS LAS VERIFICACIONES FINALIZARON CORRECTAMENTE *");
    }

    private static void verificar(String nombreCaso, boolean condicion) {
        if (condicion) {
            System.out.println("[PASS] " + nombreCaso);
        } else {
            throw new AssertionError("[FAIL] " + nombreCaso);
        }
    }
}