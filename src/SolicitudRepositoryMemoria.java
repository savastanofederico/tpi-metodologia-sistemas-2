import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SolicitudRepositoryMemoria implements ISolicitudRepository {
    private final List<Solicitud> solicitudes = new ArrayList<>();
    private int secuenciaId = 1;

    @Override
    public Solicitud guardar(NuevaSolicitudRequest request) {
        Solicitud nueva = new Solicitud(secuenciaId++, request);
        solicitudes.add(nueva);
        return nueva;
    }

    @Override
    public Optional<Solicitud> buscarPorId(int id) {
        for (Solicitud s : solicitudes) {
            if (s.getId() == id) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Solicitud> obtenerTodas() {
        return new ArrayList<>(solicitudes);
    }

    @Override
    public List<Solicitud> buscarPorEstado(EstadoSolicitud estado) {
        List<Solicitud> resultado = new ArrayList<>();
        for (Solicitud s : solicitudes) {
            if (s.getEstado() == estado) {
                resultado.add(s);
            }
        }
        return resultado;
    }
}