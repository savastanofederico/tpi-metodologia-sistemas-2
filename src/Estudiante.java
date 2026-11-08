public class Estudiante {
    private final String legajo;
    private final String nombre;
    private final String email;

    public Estudiante(String legajo, String nombre, String email) {
        if (legajo == null || legajo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos de estudiante invalidos");
        }
        this.legajo = legajo;
        this.nombre = nombre;
        this.email = email;
    }

    public String getLegajo() { return legajo; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}