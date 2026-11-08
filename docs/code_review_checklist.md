# Checklist Técnico de Code Review

| Criterio Evaluado | Estado | Observación Técnica | Acción Correctiva Aplicada |
| Responsabilidad Única (SRP) | Aprobado | `SistemaSolicitudes` concentraba persistencia, negocio y consola. | Se extrajeron `SolicitudRepositoryMemoria` y `ReporteadorSolicitudes`. |
| Listas de parámetros (Clean Code) | Aprobado | Método de alta recibía 9 argumentos primitivos. | Se introdujo la clase `NuevaSolicitudRequest`. |
| Encapsulamiento y Dominio | Aprobado | Atributos públicos permitían estados inconsistentes. | Se privatizaron campos y se movió la lógica de estado a `Solicitud`. |
| Uso de tipos seguros | Aprobado | Uso de Strings sueltos para estados y prioridades. | Se crearon los enums `EstadoSolicitud`, `Prioridad` y `TipoSolicitud`. |
| Manejo de errores | Aprobado | Validaciones repetidas con retornos silenciosos. | Se centralizaron validaciones en constructores y métodos de dominio. |
| Preservación de comportamiento | Aprobado | Riesgo de alterar la salida tras dividir clases. | Se validó con `Main` y `PruebasVerificacion` que el comportamiento se mantiene intacto. |