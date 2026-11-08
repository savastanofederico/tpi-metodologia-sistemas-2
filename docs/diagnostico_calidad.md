# Diagnóstico de Calidad Interna, Externa y Deuda Técnica

## 1. Calidad Externa vs. Calidad Interna
* **Calidad Externa:** El sistema cumple las operaciones solicitadas. Permite dar de alta solicitudes de estudiantes (Federico Savastano, Juan Martinez, Marisa Amicuzi y Sonia Gonzalez), asignar docentes responsables (Carlos Cattaneo, Claudia Naveda, Diego Lobos y Guillermo Londero), actualizar estados, filtrar por estado y emitir el reporte general sin errores visibles.
* **Calidad Interna:** Presenta alta deuda técnica. Toda la lógica de negocio, validaciones, cálculo de tiempos SLA, almacenamiento en memoria e impresión por consola están concentrados en `SistemaSolicitudes`.

## 2. Registro de Code Smells Detectados
1. **God Class (`SistemaSolicitudes.java`):** Concentra almacenamiento, validación, reglas de negocio y formato de salida.
2. **Long Method (`procesarYRegistrarSolicitud`):** Método extenso que valida, calcula tiempos, instancia objetos, persiste y muestra mensajes.
3. **Long Parameter List (`procesarYRegistrarSolicitud`):** Recibe 9 parámetros sueltos, dificultando su lectura e invocación.
4. **Primitive Obsession (`Solicitud.java` y `SistemaSolicitudes.java`):** Uso de cadenas de texto (`"ABIERTA"`, `"ALTA"`, `"EQUIVALENCIA"`) sin validación de tipos.
5. **Switch Statements / Condicionales Complejos (`procesarYRegistrarSolicitud`):** Bloques `if-else` anidados para calcular las horas estimadas según tipo y prioridad.
6. **Duplicated Code (`procesarYRegistrarSolicitud`, `asignarResponsable`, `cambiarEstado`):** Validaciones de nulos/vacíos y formateo de cadenas de responsable repetidos en varios métodos.
7. **Feature Envy (`SistemaSolicitudes.java`):** El servicio consulta y modifica directamente los campos internos de `Solicitud` en lugar de delegar el cambio de estado a la propia entidad.
8. **Public Mutable Fields / Violación de Encapsulamiento (`Estudiante`, `Responsable`, `Solicitud`):** Atributos públicos sin control de acceso ni invariantes de dominio.