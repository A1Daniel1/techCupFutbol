# 📄 Alcance del Sistema – TECHCUP FÚTBOL

## 1. Sistema

* **Nombre del sistema:** TECHCUP FÚTBOL
* **Objetivo:** El sistema tiene como objetivo diseñar e implementar una plataforma web que permita gestionar de forma organizada, centralizada y transparente el torneo semestral de fútbol de los programas de Ingeniería de Sistemas, Inteligencia Artificial, Ciberseguridad y Estadística de la Escuela Colombiana de Ingeniería Julio Garavito, eliminando los procesos manuales actuales y mejorando la experiencia de todos los actores involucrados.

---

## 2. Problema a resolver

Actualmente, el torneo semestral de fútbol de los programas de Ingeniería de la Escuela Colombiana de Ingeniería se organiza de forma completamente manual. Los organizadores se apoyan en mensajes de WhatsApp, formularios aislados de Google y hojas de cálculo para gestionar inscripciones, pagos y resultados.

Esta situación genera los siguientes problemas concretos:

- El proceso de inscripción no es claro para los participantes, lo que provoca confusión y retrasos.
- Los capitanes tienen dificultades para completar sus equipos porque no existe un mecanismo de búsqueda de jugadores disponibles.
- Los pagos se verifican de forma manual, lo que es lento y propenso a errores.
- Los resultados, la tabla de posiciones y las llaves eliminatorias se actualizan a mano, lo que genera inconsistencias.
- No existe historial del torneo ni estadísticas consolidadas.
- La información oficial está dispersa entre múltiples canales informales.

Todo esto ocasiona retrasos administrativos, conflictos entre participantes y una experiencia caótica que desestimula la participación.

---

## 3. Diagrama de Contexto

### 3.1 Diagrama

> imagen del diagrama de contexto

### 3.2 Actores

| Actor / Rol              | Descripción                                                                                  |
|--------------------------|:--------------------------------------------------------------------------------------------:|
| Estudiante               | Participante activo de los programas de ingeniería que se registra como jugador y puede asumir el rol de capitán. |
| Graduado                 | Egresado de los programas de ingeniería que puede registrarse como jugador y ser capitán.   |
| Profesor                 | Docente de la Escuela que puede registrarse como jugador y asumir el rol de capitán.        |
| Personal Administrativo  | Empleado administrativo de la Escuela que puede participar como jugador o capitán.          |
| Familiar                 | Familiar de un integrante de la comunidad; se autentica con correo Gmail personal.          |
| Capitán                  | Jugador que crea y administra un equipo, gestiona invitaciones y define alineaciones.       |
| Organizador              | Responsable de crear, configurar y gestionar el torneo; revisa pagos y registra resultados. |
| Árbitro                  | Consulta la información de los partidos que le han sido asignados para arbitrar.            |
| Administrador            | Tiene control total del sistema: gestión de usuarios, roles, auditoría y configuración general. |

### 3.3 Sistemas externos

| Sistema                         | Descripción                                                                                             |
|---------------------------------|:-------------------------------------------------------------------------------------------------------:|
| Google OAuth2                   | Servicio de autenticación de Google que permite a los familiares iniciar sesión con correo Gmail.       |
| Proveedor OAuth2 Institucional  | Sistema de autenticación de la Escuela Colombiana de Ingeniería para estudiantes, profesores y personal. |
| NEQUI / Efectivo (Externo)      | Medio de pago externo a la plataforma; el capitán realiza el pago y luego sube el comprobante al sistema. |
| Servicio de Almacenamiento      | Servicio de almacenamiento en nube para guardar fotos de perfil, escudos de equipos y comprobantes de pago. |

---

## 4. Alcance del sistema

### 4.1 Dentro del sistema

Funciones que TECHCUP FÚTBOL **sí** realiza:

1. **Registro y autenticación de usuarios** mediante correo institucional (estudiantes, profesores, graduados y personal) y correo Gmail (familiares), con control de roles y permisos.
2. **Gestión completa del torneo**: creación, configuración (reglamento, canchas, horarios, sanciones), inicio y finalización por parte del organizador.
3. **Inscripción de equipos y verificación de pagos**: el capitán sube el comprobante de consignación y el organizador aprueba o rechaza la inscripción desde la plataforma.
4. **Creación y gestión de equipos**: el capitán crea su equipo, define nombre, escudo y colores, invita jugadores y el sistema valida las reglas de composición (mínimo 7, máximo 12, más del 50% de programas de ingeniería).
5. **Búsqueda de jugadores disponibles** por posición, semestre, edad, género, nombre e identificación.
6. **Gestión de alineaciones**: el capitán registra titulares y reservas con visualización gráfica de la cancha; los jugadores pueden ver la alineación del equipo rival.
7. **Registro de resultados y estadísticas de partidos**: marcador, goleadores, tarjetas amarillas y rojas registrados por el organizador.
8. **Tabla de posiciones automática**: el sistema calcula PJ, PG, PE, PP, GF, GC, DG y puntos en tiempo real.
9. **Llaves eliminatorias automáticas**: generación aleatoria de la primera ronda y avance automático de ganadores (cuartos de final, semifinal y final).
10. **Estadísticas del torneo**: máximos goleadores, historial de partidos y resultados por equipo, conservando el historial de torneos anteriores.
11. **Auditoría de acciones**: registro de las acciones relevantes realizadas por los usuarios en el sistema.

### 4.2 Fuera del sistema

Funciones que TECHCUP FÚTBOL **no** realiza:

1. **Procesamiento de pagos en línea**: el pago se realiza exclusivamente por NEQUI o efectivo fuera de la plataforma; el sistema solo gestiona la verificación del comprobante subido.
2. **Comunicación directa entre usuarios**: la plataforma no incluye chat, mensajería interna ni notificaciones push; la coordinación fuera del flujo del sistema queda a cargo de los participantes.
3. **Transmisión o streaming de partidos**: el sistema no ofrece funcionalidades de retransmisión en vivo ni cobertura multimedia de los encuentros.
4. **Gestión de infraestructura física**: la asignación y reserva de canchas deportivas físicas de la Escuela se gestiona externamente; el sistema solo registra la información de canchas ya definidas por el organizador.
5. **Integración con sistemas académicos**: la plataforma no se conecta con el sistema de información académico de la Escuela para verificar matriculas activas o datos académicos en tiempo real.
