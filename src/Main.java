public class Main {
    public static void main(String[] args) {
        SistemaSolicitudes sistema = new SistemaSolicitudes();

        sistema.procesarYRegistrarSolicitud(
                "L-1001", "Federico Savastano", "fsavastano@alumnos.utn.edu.ar",
                "EQUIVALENCIA", "Solicitud de equivalencia de Programacion", "ALTA",
                "Carlos Cattaneo", "Comision 1 - Academica", true
        );

        sistema.procesarYRegistrarSolicitud(
                "L-1002", "Juan Martinez", "jmartinez@alumnos.utn.edu.ar",
                "CERTIFICADO", "Certificado de alumno regular para trabajo", "MEDIA",
                "", "", false
        );

        sistema.procesarYRegistrarSolicitud(
                "L-1003", "Marisa Amicuzi", "mamicuzi@alumnos.utn.edu.ar",
                "MESA_EXAMEN", "Habilitacion de mesa especial de examen", "ALTA",
                "Claudia Naveda", "Comision 9 - Alumnos", true
        );

        sistema.procesarYRegistrarSolicitud(
                "L-1004", "Sonia Gonzalez", "sgonzalez@alumnos.utn.edu.ar",
                "RECLAMO_NOTA", "Revision de acta de cursada anterior", "BAJA",
                "Diego Lobos", "Comision 4 - Seguimiento", true
        );

        sistema.asignarResponsable(2, "Guillermo Londero", "Comision 6 - Certificaciones", true);
        sistema.cambiarEstado(1, "RESUELTA");
        sistema.cambiarEstado(3, "CERRADA");

        System.out.println();
        sistema.buscarPorEstado("EN_REVISION");
        System.out.println();
        sistema.generarReporteGeneral();
    }
}