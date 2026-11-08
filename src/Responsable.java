public class Responsable {
    private final String nombre;
    private final String area;
    private final boolean activo;

    public Responsable(String nombre, String area, boolean activo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre de responsable invalido");
        }
        this.nombre = nombre;
        this.area = area;
        this.activo = activo;
    }

    public String getNombre() { return nombre; }
    public String getArea() { return area; }
    public boolean isActivo() { return activo; }

    public String formatearDetalle() {
        return nombre + " (" + area + ")";
    }
}