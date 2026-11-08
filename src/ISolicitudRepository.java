import java.util.List;
import java.util.Optional;

public interface ISolicitudRepository {
    Solicitud guardar(NuevaSolicitudRequest request);
    Optional<Solicitud> buscarPorId(int id);
    List<Solicitud> obtenerTodas();
    List<Solicitud> buscarPorEstado(EstadoSolicitud estado);
}