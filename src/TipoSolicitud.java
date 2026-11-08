public enum TipoSolicitud {
    EQUIVALENCIA(24, 72, 120),
    CERTIFICADO(12, 48, 48),
    MESA_EXAMEN(36, 36, 36),
    RECLAMO_NOTA(96, 96, 96);

    private final int horasAlta;
    private final int horasMedia;
    private final int horasBaja;

    TipoSolicitud(int horasAlta, int horasMedia, int horasBaja) {
        this.horasAlta = horasAlta;
        this.horasMedia = horasMedia;
        this.horasBaja = horasBaja;
    }

    public int calcularHorasEstimadas(Prioridad prioridad) {
        if (prioridad == Prioridad.ALTA) return horasAlta;
        if (prioridad == Prioridad.MEDIA) return horasMedia;
        return horasBaja;
    }
}