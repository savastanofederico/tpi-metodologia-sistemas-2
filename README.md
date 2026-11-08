# Sistema de Gestión de Solicitudes Académicas — TPI Metodología de Sistemas II

Trabajo Práctico Integrador enfocado en el análisis de calidad interna y externa, detección de deuda técnica, aplicación de principios SOLID/Clean Code y ejecución de refactorings seguros

**Autor:** Federico Savastano (Modalidad individual justificada por baja de compañero de equipo durante la cursada)
**Carrera:** Tecnicatura Universitaria en Programación a Distancia (UTN)

## Funcionalidades del Sistema
1. Registro de solicitudes académicas (equivalencias, certificados, mesas de examen y reclamos de nota)
2. Asignación de docentes responsables por área/comisión
3. Gestión de cambios de estado con validación de reglas de negocio
4. Búsqueda y filtrado de solicitudes por estado
5. Generación de reporte general con tiempos estimados de respuesta (SLA) y totales por estado

## Estructura del Repositorio
* `src/`: Código fuente en Java (versión final refactorizada y suite de pruebas)
* `docs/`: Diagnóstico de calidad interna/externa y checklist técnico de Code Review
* `evidencias/`: Capturas del proceso antes y después del refactoring

## Ejecución del Proyecto
Para ejecutar el flujo principal:
```bash
javac src/*.java
java -cp src Main
```
Para ejecutar las pruebas de verificación:
```bash
javac src/*.java
java -cp src PruebasVerificacion
```

## Hitos y Tags del Repositorio
* estado-inicial : Sistema base funcional con code smells y deuda técnica.
* etapa-diagnostico : Documentación de problemas técnicos y análisis de calidad.
* etapa-refactoring : Código mejorado aplicando SOLID, Clean Code y refactorings seguros.
* etapa-pruebas : Suite de verificación de comportamiento externo.
* etapa-docs : Checklist de Code Review y documentación general.
* estadorefactorizado : Versión final consolidada del proyecto.
