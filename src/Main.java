public class Main {
    public static void main(String[] args) {
        ISolicitudRepository repository = new SolicitudRepositoryMemoria();
        ReporteadorSolicitudes reporteador = new ReporteadorSolicitudes();
        SistemaSolicitudes sistema = new SistemaSolicitudes(repository, reporteador);

        sistema.registrarSolicitud(new NuevaSolicitudRequest(
                new Estudiante("L-1001", "Federico Savastano", "fsavastano@alumnos.utn.edu.ar"),
                TipoSolicitud.EQUIVALENCIA,
                "Solicitud de equivalencia de Programacion",
                Prioridad.ALTA,
                new Responsable("Carlos Cattaneo", "Comision 1 - Academica", true)
        ));

        sistema.registrarSolicitud(new NuevaSolicitudRequest(
                new Estudiante("L-1002", "Juan Martinez", "jmartinez@alumnos.utn.edu.ar"),
                TipoSolicitud.CERTIFICADO,
                "Certificado de alumno regular para trabajo",
                Prioridad.MEDIA,
                null
        ));

        sistema.registrarSolicitud(new NuevaSolicitudRequest(
                new Estudiante("L-1003", "Marisa Amicuzi", "mamicuzi@alumnos.utn.edu.ar"),
                TipoSolicitud.MESA_EXAMEN,
                "Habilitacion de mesa especial de examen",
                Prioridad.ALTA,
                new Responsable("Claudia Naveda", "Comision 9 - Alumnos", true)
        ));

        sistema.registrarSolicitud(new NuevaSolicitudRequest(
                new Estudiante("L-1004", "Sonia Gonzalez", "sgonzalez@alumnos.utn.edu.ar"),
                TipoSolicitud.RECLAMO_NOTA,
                "Revision de acta de cursada anterior",
                Prioridad.BAJA,
                new Responsable("Diego Lobos", "Comision 4 - Seguimiento", true)
        ));

        sistema.asignarResponsable(2, new Responsable("Guillermo Londero", "Comision 6 - Certificaciones", true));
        sistema.cambiarEstado(1, EstadoSolicitud.RESUELTA);
        sistema.cambiarEstado(3, EstadoSolicitud.CERRADA);

        System.out.println();
        sistema.buscarPorEstado(EstadoSolicitud.EN_REVISION);
        System.out.println();
        sistema.generarReporteGeneral();
    }
}