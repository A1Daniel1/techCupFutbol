# 📄 Planeación del Sistema – TECHCUP FÚTBOL

## Desglose de trabajo: Épicas, Historias de Usuario y Tareas

La implementación de los requerimientos identificados de TECHCUP FÚTBOL se desglosa de la siguiente manera:

---

## ÉPICA 1 – Autenticación y Seguridad

| Campo | Descripción |
|-------|-------------|
| **ID** | EP-01 |
| **Título** | Autenticación y Seguridad |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica porque el sistema maneja múltiples tipos de actores con permisos distintos. Sin un mecanismo robusto de autenticación y control de roles, cualquier usuario podría acceder a funcionalidades restringidas (como aprobar pagos o registrar resultados), comprometiendo la integridad del torneo. |
| **Stakeholder** | Administrador, todos los actores del sistema |

### Historias de usuario

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-01 |
| **Título** | Inicio de sesión con correo institucional |
| **Descripción** | Como estudiante/profesor/graduado/personal administrativo quiero iniciar sesión con mi correo institucional para acceder al sistema de forma segura y obtener las funcionalidades correspondientes a mi rol. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-02 |
| **Título** | Inicio de sesión con Gmail para familiares |
| **Descripción** | Como familiar de un integrante de la comunidad quiero iniciar sesión con mi cuenta de Gmail para acceder al torneo sin necesidad de un correo institucional. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-03 |
| **Título** | Control de roles y permisos |
| **Descripción** | Como administrador quiero que el sistema controle los permisos por rol para que cada actor solo acceda a las funcionalidades que le corresponden. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-04 |
| **Título** | Registro de auditoría de acciones |
| **Descripción** | Como administrador quiero que el sistema registre las acciones relevantes de los usuarios para mantener trazabilidad de todo lo que ocurre en el sistema. |
| **Prioridad** | Media |
| **Estimación** | 3 puntos de historia |

### Tareas

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-01 |
| **Título** | Configurar Spring Security con JWT |
| **ID de la Historia asociada** | HU-01 |
| **Descripción** | Implementar filtros de seguridad y generación/validación de tokens JWT en Spring Boot para autenticación stateless. |
| **Tareas requisito** | — |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-02 |
| **Título** | Implementar endpoint POST /auth/login |
| **ID de la Historia asociada** | HU-01 |
| **Descripción** | Crear controlador REST que reciba credenciales OAuth2 institucional y retorne un JWT válido con el rol del usuario. |
| **Tareas requisito** | TR-01 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-03 |
| **Título** | Crear formulario de login en React con TypeScript |
| **ID de la Historia asociada** | HU-01 |
| **Descripción** | Desarrollar componente LoginForm con opciones de autenticación institucional y Google, manejo de errores y redirección por rol. |
| **Tareas requisito** | TR-02 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-04 |
| **Título** | Configurar Google OAuth2 en Spring Boot |
| **ID de la Historia asociada** | HU-02 |
| **Descripción** | Agregar dependencias y properties de Google OAuth2 y manejar el callback de autorización para familiares. |
| **Tareas requisito** | TR-01 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-05 |
| **Título** | Definir enum de Roles y aplicar @PreAuthorize en controladores |
| **ID de la Historia asociada** | HU-03 |
| **Descripción** | Crear enum Rol con todos los actores del sistema y aplicar anotaciones de seguridad por método en cada endpoint REST. |
| **Tareas requisito** | TR-01 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-06 |
| **Título** | Crear entidad AuditLog e implementar aspecto AOP |
| **ID de la Historia asociada** | HU-04 |
| **Descripción** | Diseñar tabla audit_log en PostgreSQL e implementar @Aspect en Spring para registrar automáticamente acciones sensibles. |
| **Tareas requisito** | TR-01 |

---

## ÉPICA 2 – Gestión de Torneos

| Campo | Descripción |
|-------|-------------|
| **ID** | EP-02 |
| **Título** | Gestión de Torneos |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica porque el torneo es la entidad central del sistema. Sin la capacidad de crear, configurar y controlar el ciclo de vida del torneo, ninguna de las demás funcionalidades (inscripciones, partidos, estadísticas) tiene contexto. |
| **Stakeholder** | Organizador |

### Historias de usuario

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-05 |
| **Título** | Crear un nuevo torneo |
| **Descripción** | Como organizador quiero crear un torneo con su información básica para iniciar el proceso de gestión del evento. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-06 |
| **Título** | Configurar el torneo |
| **Descripción** | Como organizador quiero definir el reglamento, canchas, horarios, sanciones y fechas importantes del torneo para que todos los participantes tengan claridad sobre las condiciones del evento. |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-07 |
| **Título** | Iniciar y finalizar el torneo |
| **Descripción** | Como organizador quiero cambiar el estado del torneo (Borrador → Activo → En progreso → Finalizado) para controlar cada fase de su ciclo de vida. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-08 |
| **Título** | Consultar torneos |
| **Descripción** | Como usuario quiero consultar los torneos disponibles y su información para estar al tanto del evento actual e historial. |
| **Prioridad** | Media |
| **Estimación** | 2 puntos de historia |

### Tareas

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-07 |
| **Título** | Crear entidad Torneo en PostgreSQL |
| **ID de la Historia asociada** | HU-05 |
| **Descripción** | Diseñar tabla torneo con campos id, nombre, fecha_inicio, fecha_fin, cantidad_equipos, costo_equipo, estado. |
| **Tareas requisito** | — |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-08 |
| **Título** | Implementar endpoint POST /torneos |
| **ID de la Historia asociada** | HU-05 |
| **Descripción** | Crear controlador y servicio para persistir un nuevo torneo con estado inicial BORRADOR. |
| **Tareas requisito** | TR-07 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-09 |
| **Título** | Crear formulario de creación de torneo en React |
| **ID de la Historia asociada** | HU-05 |
| **Descripción** | Desarrollar componente CreateTournamentForm con campos del torneo, validaciones de fechas y selector de estado. |
| **Tareas requisito** | TR-08 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-10 |
| **Título** | Implementar endpoints de reglamento, canchas, horarios y sanciones |
| **ID de la Historia asociada** | HU-06 |
| **Descripción** | Crear endpoints CRUD para cada entidad de configuración del torneo: reglamento, cancha, horario y sanción. |
| **Tareas requisito** | TR-07 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-11 |
| **Título** | Implementar endpoints PATCH de transición de estado del torneo |
| **ID de la Historia asociada** | HU-07 |
| **Descripción** | Crear endpoints /torneos/{id}/activar, /iniciar y /finalizar con validaciones de negocio por estado. |
| **Tareas requisito** | TR-07 |

---

## ÉPICA 3 – Registro de Jugadores y Perfiles

| Campo | Descripción |
|-------|-------------|
| **ID** | EP-03 |
| **Título** | Registro de Jugadores y Perfiles |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica porque sin perfiles deportivos completos los capitanes no pueden tomar decisiones informadas al conformar sus equipos, y el sistema no puede validar las reglas de composición. |
| **Stakeholder** | Estudiante, Graduado, Profesor, Personal Administrativo, Familiar, Capitán |

### Historias de usuario

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-09 |
| **Título** | Completar perfil deportivo |
| **Descripción** | Como jugador quiero completar mi perfil deportivo indicando posición, dorsal y foto para que los capitanes me conozcan y puedan invitarme. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-10 |
| **Título** | Marcarme como disponible para equipo |
| **Descripción** | Como jugador quiero activar mi disponibilidad para que los capitanes puedan encontrarme y contactarme. |
| **Prioridad** | Alta |
| **Estimación** | 2 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-11 |
| **Título** | Gestionar invitaciones a equipos |
| **Descripción** | Como jugador quiero recibir invitaciones de equipos y poder aceptarlas o rechazarlas para tomar el control de mi participación en el torneo. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

### Tareas

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-12 |
| **Título** | Crear entidad Jugador y tabla jugador en PostgreSQL |
| **ID de la Historia asociada** | HU-09 |
| **Descripción** | Diseñar tabla jugador con campos id, usuario_id, posicion_principal, posicion_secundaria, numero_dorsal, foto_url, tipo_participante. |
| **Tareas requisito** | TR-01 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-13 |
| **Título** | Implementar endpoint PUT /jugadores/{id}/perfil |
| **ID de la Historia asociada** | HU-09 |
| **Descripción** | Actualizar perfil deportivo del jugador autenticado y manejar subida de foto vía multipart/form-data. |
| **Tareas requisito** | TR-12 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-14 |
| **Título** | Implementar endpoint PATCH /jugadores/{id}/disponibilidad |
| **ID de la Historia asociada** | HU-10 |
| **Descripción** | Permitir al jugador activar o desactivar su flag disponible_para_equipo. |
| **Tareas requisito** | TR-12 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-15 |
| **Título** | Crear entidad Invitacion y endpoints de gestión |
| **ID de la Historia asociada** | HU-11 |
| **Descripción** | Diseñar tabla invitacion en PostgreSQL e implementar endpoints para enviar, listar, aceptar y rechazar invitaciones. |
| **Tareas requisito** | TR-12 |

---

## ÉPICA 4 – Gestión de Equipos

| Campo | Descripción |
|-------|-------------|
| **ID** | EP-04 |
| **Título** | Gestión de Equipos |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica porque el equipo es la unidad de participación en el torneo. Sin esta funcionalidad, los capitanes no pueden conformar sus grupos ni el sistema puede validar las reglas de composición reglamentaria. |
| **Stakeholder** | Capitán, Organizador |

### Historias de usuario

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-12 |
| **Título** | Crear un equipo de fútbol |
| **Descripción** | Como capitán quiero crear un equipo con nombre, escudo y colores para inscribirlo en el torneo. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-13 |
| **Título** | Invitar jugadores al equipo |
| **Descripción** | Como capitán quiero invitar jugadores disponibles para completar el roster de mi equipo cumpliendo las reglas de composición. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-14 |
| **Título** | Buscar jugadores disponibles |
| **Descripción** | Como capitán quiero buscar jugadores disponibles filtrando por posición, semestre, edad y género para encontrar los perfiles que necesito. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-15 |
| **Título** | Validar composición reglamentaria del equipo |
| **Descripción** | Como sistema quiero validar las reglas de composición (mínimo 7, máximo 12, >50% de programas de ingeniería) para garantizar la integridad del torneo. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

### Tareas

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-16 |
| **Título** | Crear entidad Equipo en PostgreSQL |
| **ID de la Historia asociada** | HU-12 |
| **Descripción** | Diseñar tabla equipo con campos id, nombre, escudo_url, color_principal, color_secundario, capitan_id, torneo_id, estado. |
| **Tareas requisito** | TR-07 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-17 |
| **Título** | Implementar endpoint POST /equipos |
| **ID de la Historia asociada** | HU-12 |
| **Descripción** | Crear controlador y servicio que registre el equipo y asigne al creador como capitán. |
| **Tareas requisito** | TR-16 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-18 |
| **Título** | Implementar endpoint POST /equipos/{id}/invitaciones |
| **ID de la Historia asociada** | HU-13 |
| **Descripción** | Crear invitación para un jugador específico validando que no pertenezca a otro equipo. |
| **Tareas requisito** | TR-15, TR-16 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-19 |
| **Título** | Implementar endpoint GET /jugadores con filtros de búsqueda |
| **ID de la Historia asociada** | HU-14 |
| **Descripción** | Crear endpoint paginado con filtros por posición, semestre, edad, género, nombre e identificación. |
| **Tareas requisito** | TR-12 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-20 |
| **Título** | Implementar validadores de composición del equipo |
| **ID de la Historia asociada** | HU-15 |
| **Descripción** | Crear beans de validación para verificar reglas de cantidad de jugadores y porcentaje de estudiantes de ingeniería al momento de la inscripción. |
| **Tareas requisito** | TR-16 |

---

## ÉPICA 5 – Inscripción y Pagos

| Campo | Descripción |
|-------|-------------|
| **ID** | EP-05 |
| **Título** | Inscripción y Pagos |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica porque el pago es el requisito de entrada al torneo. Sin un flujo claro de subida y verificación de comprobantes, los organizadores no pueden confirmar qué equipos están realmente inscritos. |
| **Stakeholder** | Capitán, Organizador |

### Historias de usuario

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-16 |
| **Título** | Subir comprobante de pago del equipo |
| **Descripción** | Como capitán quiero subir el comprobante de pago para inscribir formalmente a mi equipo en el torneo. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-17 |
| **Título** | Revisar y aprobar comprobantes de pago |
| **Descripción** | Como organizador quiero revisar los comprobantes subidos y cambiar su estado para gestionar las inscripciones del torneo. |
| **Prioridad** | Alta |
| **Estimación** | 3 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-18 |
| **Título** | Consultar estado de inscripción del equipo |
| **Descripción** | Como capitán quiero ver el estado actual de la inscripción de mi equipo para saber si fui aceptado o necesito corregir algo. |
| **Prioridad** | Media |
| **Estimación** | 2 puntos de historia |

### Tareas

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-21 |
| **Título** | Crear entidad Pago en PostgreSQL |
| **ID de la Historia asociada** | HU-16 |
| **Descripción** | Diseñar tabla pago con campos id, equipo_id, torneo_id, comprobante_url, estado, fecha_subida, fecha_revision. |
| **Tareas requisito** | TR-16 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-22 |
| **Título** | Implementar endpoint POST /pagos para subir comprobante |
| **ID de la Historia asociada** | HU-16 |
| **Descripción** | Recibir archivo multipart/form-data, almacenar en servicio de archivos y crear registro de pago con estado PENDIENTE. |
| **Tareas requisito** | TR-21 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-23 |
| **Título** | Implementar endpoint PATCH /pagos/{id}/estado |
| **ID de la Historia asociada** | HU-17 |
| **Descripción** | Permitir al organizador cambiar estado del pago a EN_REVISION, APROBADO o RECHAZADO con observación opcional. |
| **Tareas requisito** | TR-21 |

---

## ÉPICA 6 – Partidos y Alineaciones

| Campo | Descripción |
|-------|-------------|
| **ID** | EP-06 |
| **Título** | Partidos y Alineaciones |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica porque los partidos son el núcleo operativo del torneo. Sin esta funcionalidad, no es posible registrar resultados, gestionar alineaciones ni mantener actualizada la competencia. |
| **Stakeholder** | Capitán, Organizador, Árbitro, Jugador |

### Historias de usuario

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-19 |
| **Título** | Registrar alineación antes del partido |
| **Descripción** | Como capitán quiero registrar la formación y los titulares de mi equipo de forma visual para definir mi estrategia antes del partido. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-20 |
| **Título** | Consultar alineación del equipo rival |
| **Descripción** | Como jugador o capitán quiero ver la alineación del equipo rival para conocer su formación y preparar el encuentro. |
| **Prioridad** | Media |
| **Estimación** | 2 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-21 |
| **Título** | Registrar resultado y estadísticas del partido |
| **Descripción** | Como organizador quiero registrar el marcador, goleadores y tarjetas para mantener el torneo actualizado tras cada partido. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-22 |
| **Título** | Consultar información de partido para árbitro |
| **Descripción** | Como árbitro quiero consultar mis partidos asignados para conocer horario, cancha y equipos que voy a arbitrar. |
| **Prioridad** | Media |
| **Estimación** | 2 puntos de historia |

### Tareas

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-24 |
| **Título** | Crear entidad Alineacion y tabla en PostgreSQL |
| **ID de la Historia asociada** | HU-19 |
| **Descripción** | Diseñar tabla alineacion con campos id, partido_id, equipo_id, formacion, titulares[], reservas[]. |
| **Tareas requisito** | TR-07 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-25 |
| **Título** | Crear editor visual de alineación en React |
| **ID de la Historia asociada** | HU-19 |
| **Descripción** | Desarrollar componente PitchEditor con representación gráfica de la cancha y drag-and-drop de jugadores según la formación elegida. |
| **Tareas requisito** | TR-24 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-26 |
| **Título** | Crear entidades Partido, Gol y Tarjeta en PostgreSQL |
| **ID de la Historia asociada** | HU-21 |
| **Descripción** | Diseñar tablas partido, gol (jugador_id, minuto, tipo) y tarjeta (jugador_id, minuto, tipo). |
| **Tareas requisito** | TR-07, TR-16 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-27 |
| **Título** | Implementar endpoint POST /partidos/{id}/resultado |
| **ID de la Historia asociada** | HU-21 |
| **Descripción** | Persistir marcador final, goleadores y tarjetas; disparar evento de actualización de tabla de posiciones y llaves. |
| **Tareas requisito** | TR-26 |

---

## ÉPICA 7 – Tabla de Posiciones y Llaves Eliminatorias

| Campo | Descripción |
|-------|-------------|
| **ID** | EP-07 |
| **Título** | Tabla de Posiciones y Llaves Eliminatorias |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica para eliminar la actualización manual de resultados y clasificaciones que actualmente genera errores y conflictos. La automatización garantiza transparencia y confianza entre los participantes. |
| **Stakeholder** | Todos los actores del sistema |

### Historias de usuario

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-23 |
| **Título** | Visualizar tabla de posiciones automática |
| **Descripción** | Como usuario quiero ver la tabla de posiciones actualizada automáticamente para seguir la clasificación del torneo en tiempo real. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-24 |
| **Título** | Generar llaves eliminatorias automáticamente |
| **Descripción** | Como organizador quiero que el sistema genere el cuadro eliminatorio de forma automática para no tener que organizarlo manualmente. |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos de historia |

### Tareas

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-28 |
| **Título** | Implementar servicio TablaPosicionesService |
| **ID de la Historia asociada** | HU-23 |
| **Descripción** | Crear servicio que calcule PJ, PG, PE, PP, GF, GC, DG y puntos por equipo al registrar cada partido. |
| **Tareas requisito** | TR-26 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-29 |
| **Título** | Crear componente StandingsTable en React |
| **ID de la Historia asociada** | HU-23 |
| **Descripción** | Desarrollar componente con columnas PJ/PG/PE/PP/GF/GC/DG/Pts, ordenado por puntos y diferencia de goles. |
| **Tareas requisito** | TR-28 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-30 |
| **Título** | Implementar algoritmo de sorteo y avance en llaves |
| **ID de la Historia asociada** | HU-24 |
| **Descripción** | Crear servicio de generación aleatoria de emparejamientos iniciales y lógica de avance automático del ganador tras cada partido eliminatorio. |
| **Tareas requisito** | TR-26 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-31 |
| **Título** | Crear componente BracketView en React |
| **ID de la Historia asociada** | HU-24 |
| **Descripción** | Desarrollar visualización tipo copa del cuadro eliminatorio con cuartos, semifinal y final, mostrando equipos y marcadores. |
| **Tareas requisito** | TR-30 |

---

## ÉPICA 8 – Estadísticas e Historial del Torneo

| Campo | Descripción |
|-------|-------------|
| **ID** | EP-08 |
| **Título** | Estadísticas e Historial del Torneo |
| **Descripción** | TECHCUP FÚTBOL necesita esta épica para conservar la memoria histórica del torneo y ofrecer estadísticas que enriquezcan la experiencia competitiva. Actualmente no existe ningún registro histórico del torneo. |
| **Stakeholder** | Todos los actores del sistema |

### Historias de usuario

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-25 |
| **Título** | Consultar estadísticas del torneo |
| **Descripción** | Como usuario quiero ver las estadísticas generales del torneo (goleadores, historial de partidos) para seguir el rendimiento de jugadores y equipos. |
| **Prioridad** | Media |
| **Estimación** | 3 puntos de historia |

| Campo | Descripción |
|-------|-------------|
| **ID** | HU-26 |
| **Título** | Consultar historial de torneos anteriores |
| **Descripción** | Como usuario quiero ver los torneos finalizados y sus resultados para conocer la historia de la competencia. |
| **Prioridad** | Baja |
| **Estimación** | 2 puntos de historia |

### Tareas

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-32 |
| **Título** | Implementar endpoints de estadísticas del torneo |
| **ID de la Historia asociada** | HU-25 |
| **Descripción** | Crear endpoints GET /torneos/{id}/goleadores y GET /torneos/{id}/historial con datos calculados de los partidos registrados. |
| **Tareas requisito** | TR-26 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-33 |
| **Título** | Crear página de estadísticas TournamentStats en React |
| **ID de la Historia asociada** | HU-25 |
| **Descripción** | Desarrollar componente con tabla de goleadores, historial de partidos y resultados por equipo en pestañas organizadas. |
| **Tareas requisito** | TR-32 |

| Campo | Descripción |
|-------|-------------|
| **ID** | TR-34 |
| **Título** | Crear galería de torneos anteriores TournamentHistory en React |
| **ID de la Historia asociada** | HU-26 |
| **Descripción** | Mostrar torneos finalizados con campeón, goleador y estadísticas destacadas de cada edición. |
| **Tareas requisito** | TR-32 |
