# 📄 Planeación del Sistema en Jira – TECHCUP FÚTBOL

## Desglose de trabajo: Épicas, Historias de Usuario y Tareas

La implementación de los requerimientos identificados de TECHCUP FÚTBOL se gestiona en Jira bajo el framework Scrum + Kanban, con sprints de una semana de duración y estimaciones en puntos de historia.

---

## 1. Épicas

Las épicas representan las grandes áreas funcionales del sistema. Cada historia de usuario y tarea debe estar vinculada a una de estas épicas mediante el campo **Epic Link** en Jira.

| ID Jira | Épica | Descripción | Sprints relacionados |
|---------|-------|-------------|----------------------|
| EP-01 | Autenticación y Seguridad | Registro, login (institucional y Gmail), control de roles y auditoría de acciones. | Sprint 1 |
| EP-02 | Gestión de Torneos | Creación, configuración, inicio y finalización del torneo por el organizador. | Sprint 1 – Sprint 2 |
| EP-03 | Registro de Jugadores y Perfiles | Perfil deportivo, disponibilidad, foto, dorsal y posiciones de juego. | Sprint 1 – Sprint 2 |
| EP-04 | Gestión de Equipos | Creación de equipos, invitación de jugadores y validación de composición. | Sprint 2 |
| EP-05 | Inscripción y Pagos | Subida de comprobante, revisión por organizador y estados de inscripción. | Sprint 2 – Sprint 3 |
| EP-06 | Partidos y Alineaciones | Registro de alineaciones, resultados, goleadores, tarjetas y consulta para árbitros. | Sprint 3 |
| EP-07 | Tabla de Posiciones y Llaves | Cálculo automático de tabla y generación de cuadro eliminatorio. | Sprint 4 |
| EP-08 | Estadísticas e Historial | Goleadores, historial de partidos e historial de torneos anteriores. | Sprint 4 |

---

## 2. Historias de usuario

Las historias de usuario se crean en Jira como ítems de tipo **Story** y deben estar vinculadas a su épica mediante el campo **Epic Link**.

| ID | Historia de Usuario | Épica | Prioridad | Story Points | Sprint |
|----|---------------------|-------|-----------|:---:|--------|
| HU-01 | Como estudiante/graduado/profesor quiero iniciar sesión con correo institucional para acceder con mi rol asignado. | EP-01 | Alta | 5 | Sprint 1 |
| HU-02 | Como familiar quiero iniciar sesión con Gmail para acceder al torneo sin correo institucional. | EP-01 | Alta | 3 | Sprint 1 |
| HU-03 | Como administrador quiero controlar los permisos por rol para que cada actor acceda solo a sus funcionalidades. | EP-01 | Alta | 5 | Sprint 1 |
| HU-04 | Como administrador quiero un registro de auditoría para mantener trazabilidad de las acciones del sistema. | EP-01 | Media | 3 | Sprint 1 |
| HU-05 | Como organizador quiero crear un torneo con información básica para iniciar la gestión del evento. | EP-02 | Alta | 5 | Sprint 1 |
| HU-06 | Como organizador quiero configurar reglamento, canchas, horarios y sanciones para que los participantes tengan toda la información. | EP-02 | Alta | 8 | Sprint 2 |
| HU-07 | Como organizador quiero cambiar el estado del torneo para controlar cada fase de su ciclo de vida. | EP-02 | Alta | 3 | Sprint 2 |
| HU-08 | Como usuario quiero consultar los torneos disponibles para ver información y estado del torneo activo. | EP-02 | Media | 2 | Sprint 1 |
| HU-09 | Como jugador quiero completar mi perfil deportivo con posición, dorsal y foto para que los capitanes me conozcan. | EP-03 | Alta | 3 | Sprint 1 |
| HU-10 | Como jugador quiero activar mi disponibilidad para que los capitanes puedan encontrarme e invitarme. | EP-03 | Alta | 2 | Sprint 2 |
| HU-11 | Como jugador quiero recibir y gestionar invitaciones de equipos para decidir dónde jugar. | EP-03 | Alta | 3 | Sprint 2 |
| HU-12 | Como capitán quiero crear un equipo con nombre, escudo y colores para inscribirlo en el torneo. | EP-04 | Alta | 5 | Sprint 2 |
| HU-13 | Como capitán quiero invitar jugadores para completar el roster cumpliendo las reglas de composición. | EP-04 | Alta | 3 | Sprint 2 |
| HU-14 | Como capitán quiero buscar jugadores por posición, semestre y otros filtros para encontrar los perfiles que necesito. | EP-04 | Alta | 3 | Sprint 2 |
| HU-15 | Como sistema quiero validar la composición reglamentaria del equipo para garantizar la integridad del torneo. | EP-04 | Alta | 3 | Sprint 2 |
| HU-16 | Como capitán quiero subir el comprobante de pago para inscribir formalmente mi equipo en el torneo. | EP-05 | Alta | 5 | Sprint 2 |
| HU-17 | Como organizador quiero revisar y aprobar comprobantes de pago para confirmar las inscripciones. | EP-05 | Alta | 3 | Sprint 3 |
| HU-18 | Como capitán quiero consultar el estado de inscripción de mi equipo para saber si fui aceptado. | EP-05 | Media | 2 | Sprint 3 |
| HU-19 | Como capitán quiero registrar la alineación de mi equipo de forma visual para definir mi estrategia antes del partido. | EP-06 | Alta | 5 | Sprint 3 |
| HU-20 | Como jugador o capitán quiero consultar la alineación del equipo rival para preparar el encuentro. | EP-06 | Media | 2 | Sprint 3 |
| HU-21 | Como organizador quiero registrar marcador, goleadores y tarjetas para mantener el torneo actualizado. | EP-06 | Alta | 5 | Sprint 3 |
| HU-22 | Como árbitro quiero consultar mis partidos asignados para conocer horario, cancha y equipos. | EP-06 | Media | 2 | Sprint 4 |
| HU-23 | Como usuario quiero ver la tabla de posiciones actualizada automáticamente para seguir la clasificación. | EP-07 | Alta | 5 | Sprint 4 |
| HU-24 | Como organizador quiero que el sistema genere el cuadro eliminatorio automáticamente para no hacerlo a mano. | EP-07 | Alta | 8 | Sprint 4 |
| HU-25 | Como usuario quiero ver estadísticas del torneo (goleadores, historial) para seguir el rendimiento de jugadores y equipos. | EP-08 | Media | 3 | Sprint 4 |
| HU-26 | Como usuario quiero ver el historial de torneos anteriores para conocer la historia de la competencia. | EP-08 | Baja | 2 | Sprint 4 |

---

## 3. Tareas

Las tareas se crean en Jira como ítems de tipo **Task** y deben estar vinculadas a su historia mediante el campo **Parent** o **Subtask**.

| ID | Tarea | Historia | Capa | Sprint |
|----|-------|----------|------|--------|
| TR-01 | Configurar Spring Security con JWT en el backend | HU-01 | Backend | Sprint 1 |
| TR-02 | Implementar endpoint POST /auth/login | HU-01 | Backend | Sprint 1 |
| TR-03 | Crear formulario de login en React con TypeScript | HU-01 | Frontend | Sprint 1 |
| TR-04 | Configurar Google OAuth2 en Spring Boot | HU-02 | Backend | Sprint 1 |
| TR-05 | Crear botón de login con Google en React | HU-02 | Frontend | Sprint 1 |
| TR-06 | Definir enum de Roles y aplicar @PreAuthorize en controladores | HU-03 | Backend | Sprint 1 |
| TR-07 | Crear guards de rutas privadas por rol en React Router | HU-03 | Frontend | Sprint 1 |
| TR-08 | Crear entidad AuditLog en PostgreSQL e implementar aspecto AOP | HU-04 | Backend | Sprint 1 |
| TR-09 | Crear entidad Torneo en PostgreSQL | HU-05 | Base de Datos | Sprint 1 |
| TR-10 | Implementar endpoint POST /torneos | HU-05 | Backend | Sprint 1 |
| TR-11 | Crear formulario de creación de torneo en React | HU-05 | Frontend | Sprint 1 |
| TR-12 | Implementar endpoint GET /torneos con filtros | HU-08 | Backend | Sprint 1 |
| TR-13 | Crear pantalla de listado de torneos en React | HU-08 | Frontend | Sprint 1 |
| TR-14 | Crear entidad Jugador en PostgreSQL | HU-09 | Base de Datos | Sprint 1 |
| TR-15 | Implementar endpoint PUT /jugadores/{id}/perfil | HU-09 | Backend | Sprint 1 |
| TR-16 | Crear componente de edición de perfil deportivo en React | HU-09 | Frontend | Sprint 1 |
| TR-17 | Implementar endpoints CRUD para canchas, horarios y sanciones | HU-06 | Backend | Sprint 2 |
| TR-18 | Crear formularios de configuración del torneo en React | HU-06 | Frontend | Sprint 2 |
| TR-19 | Implementar endpoints PATCH de transición de estado del torneo | HU-07 | Backend | Sprint 2 |
| TR-20 | Implementar endpoint PATCH /jugadores/{id}/disponibilidad | HU-10 | Backend | Sprint 2 |
| TR-21 | Crear toggle de disponibilidad en el dashboard del jugador | HU-10 | Frontend | Sprint 2 |
| TR-22 | Crear entidad Invitacion y endpoints de gestión | HU-11 | Backend | Sprint 2 |
| TR-23 | Crear pantalla de gestión de invitaciones en React | HU-11 | Frontend | Sprint 2 |
| TR-24 | Crear entidad Equipo en PostgreSQL | HU-12 | Base de Datos | Sprint 2 |
| TR-25 | Implementar endpoint POST /equipos | HU-12 | Backend | Sprint 2 |
| TR-26 | Crear formulario de creación de equipo en React | HU-12 | Frontend | Sprint 2 |
| TR-27 | Implementar endpoint POST /equipos/{id}/invitaciones con validaciones | HU-13 | Backend | Sprint 2 |
| TR-28 | Implementar endpoint GET /jugadores con filtros de búsqueda | HU-14 | Backend | Sprint 2 |
| TR-29 | Crear pantalla de búsqueda de jugadores con filtros en React | HU-14 | Frontend | Sprint 2 |
| TR-30 | Implementar validadores de composición del equipo | HU-15 | Backend | Sprint 2 |
| TR-31 | Crear entidad Pago en PostgreSQL | HU-16 | Base de Datos | Sprint 2 |
| TR-32 | Implementar endpoint POST /pagos para subir comprobante | HU-16 | Backend | Sprint 2 |
| TR-33 | Crear componente PaymentUpload en React | HU-16 | Frontend | Sprint 2 |
| TR-34 | Implementar endpoint PATCH /pagos/{id}/estado | HU-17 | Backend | Sprint 3 |
| TR-35 | Crear dashboard de gestión de pagos para organizador en React | HU-17 | Frontend | Sprint 3 |
| TR-36 | Crear componente de estado de inscripción en React | HU-18 | Frontend | Sprint 3 |
| TR-37 | Crear entidades Alineacion, Partido, Gol y Tarjeta en PostgreSQL | HU-19 | Base de Datos | Sprint 3 |
| TR-38 | Implementar endpoint POST /partidos/{id}/alineaciones | HU-19 | Backend | Sprint 3 |
| TR-39 | Crear editor visual de alineación PitchEditor en React | HU-19 | Frontend | Sprint 3 |
| TR-40 | Implementar endpoint GET /partidos/{id}/alineaciones/{equipoId} | HU-20 | Backend | Sprint 3 |
| TR-41 | Implementar endpoint POST /partidos/{id}/resultado | HU-21 | Backend | Sprint 3 |
| TR-42 | Crear formulario MatchResultForm en React | HU-21 | Frontend | Sprint 3 |
| TR-43 | Implementar endpoint GET /arbitros/{id}/partidos | HU-22 | Backend | Sprint 4 |
| TR-44 | Crear dashboard del árbitro en React | HU-22 | Frontend | Sprint 4 |
| TR-45 | Implementar servicio TablaPosicionesService | HU-23 | Backend | Sprint 4 |
| TR-46 | Implementar endpoint GET /torneos/{id}/tabla-posiciones | HU-23 | Backend | Sprint 4 |
| TR-47 | Crear componente StandingsTable en React | HU-23 | Frontend | Sprint 4 |
| TR-48 | Implementar algoritmo de sorteo y avance automático en llaves | HU-24 | Backend | Sprint 4 |
| TR-49 | Implementar endpoint GET /torneos/{id}/llaves | HU-24 | Backend | Sprint 4 |
| TR-50 | Crear componente BracketView en React | HU-24 | Frontend | Sprint 4 |
| TR-51 | Implementar endpoints GET /goleadores e /historial | HU-25 | Backend | Sprint 4 |
| TR-52 | Crear componente TournamentStats en React | HU-25 | Frontend | Sprint 4 |
| TR-53 | Crear componente TournamentHistory en React | HU-26 | Frontend | Sprint 4 |

---

## 4. Cronograma

El proyecto se ejecuta durante el segundo tercio del semestre, de la **Semana 6 a la Semana 12**, distribuido en 4 sprints de una semana cada uno.

| Sprint | Semana | Objetivo principal | Story Points estimados |
|--------|--------|--------------------|:---:|
| Sprint 1 | Semana 7 | Autenticación, roles, auditoría, creación y consulta de torneos, perfil de jugadores | 26 |
| Sprint 2 | Semana 8 | Configuración del torneo, disponibilidad de jugadores, equipos, búsqueda, pagos (subida) | 32 |
| Sprint 3 | Semana 9–10 | Revisión de pagos, alineaciones, registro de resultados y partidos | 24 |
| Sprint 4 | Semana 11 | Tabla de posiciones, llaves eliminatorias, estadísticas e historial | 28 |
| **Total** | | | **110** |

---

## 5. Product Backlog

El Product Backlog está ordenado por prioridad de negocio. Los ítems con mayor prioridad deben abordarse primero.

| Prioridad | ID | Historia de Usuario | Story Points | Estado |
|:---------:|----|---------------------|:---:|--------|
| 1 | HU-01 | Inicio de sesión con correo institucional | 5 | Por hacer |
| 2 | HU-03 | Control de roles y permisos | 5 | Por hacer |
| 3 | HU-05 | Crear un nuevo torneo | 5 | Por hacer |
| 4 | HU-09 | Completar perfil deportivo | 3 | Por hacer |
| 5 | HU-02 | Inicio de sesión con Gmail | 3 | Por hacer |
| 6 | HU-12 | Crear un equipo de fútbol | 5 | Por hacer |
| 7 | HU-06 | Configurar el torneo | 8 | Por hacer |
| 8 | HU-16 | Subir comprobante de pago | 5 | Por hacer |
| 9 | HU-13 | Invitar jugadores al equipo | 3 | Por hacer |
| 10 | HU-14 | Buscar jugadores disponibles | 3 | Por hacer |
| 11 | HU-15 | Validar composición reglamentaria | 3 | Por hacer |
| 12 | HU-17 | Revisar y aprobar pagos | 3 | Por hacer |
| 13 | HU-07 | Iniciar y finalizar torneo | 3 | Por hacer |
| 14 | HU-10 | Marcarse como disponible | 2 | Por hacer |
| 15 | HU-11 | Gestionar invitaciones | 3 | Por hacer |
| 16 | HU-19 | Registrar alineación del equipo | 5 | Por hacer |
| 17 | HU-21 | Registrar resultado del partido | 5 | Por hacer |
| 18 | HU-23 | Tabla de posiciones automática | 5 | Por hacer |
| 19 | HU-24 | Llaves eliminatorias automáticas | 8 | Por hacer |
| 20 | HU-04 | Registro de auditoría | 3 | Por hacer |
| 21 | HU-20 | Consultar alineación del rival | 2 | Por hacer |
| 22 | HU-22 | Consulta de partidos para árbitro | 2 | Por hacer |
| 23 | HU-08 | Consultar torneos | 2 | Por hacer |
| 24 | HU-18 | Consultar estado de inscripción | 2 | Por hacer |
| 25 | HU-25 | Estadísticas del torneo | 3 | Por hacer |
| 26 | HU-26 | Historial de torneos anteriores | 2 | Por hacer |

**Total Product Backlog: 110 Story Points**

---

## 6. Sprint Backlog

### Sprint 1 – Autenticación, Torneos y Perfiles (26 SP)

**Objetivo del Sprint:** Tener el sistema con autenticación funcional, control de roles, creación de torneos y perfiles deportivos de jugadores.

| ID | Historia / Tarea | Tipo | SP | Estado |
|----|-----------------|------|:---:|--------|
| HU-01 | Inicio de sesión con correo institucional | Story | 5 | Por hacer |
| TR-01 | Configurar Spring Security con JWT | Task | — | Por hacer |
| TR-02 | Implementar endpoint POST /auth/login | Task | — | Por hacer |
| TR-03 | Crear formulario de login en React | Task | — | Por hacer |
| HU-02 | Inicio de sesión con Gmail para familiares | Story | 3 | Por hacer |
| TR-04 | Configurar Google OAuth2 en Spring Boot | Task | — | Por hacer |
| TR-05 | Crear botón de login con Google en React | Task | — | Por hacer |
| HU-03 | Control de roles y permisos | Story | 5 | Por hacer |
| TR-06 | Definir enum Roles y @PreAuthorize | Task | — | Por hacer |
| TR-07 | Crear guards de rutas en React Router | Task | — | Por hacer |
| HU-04 | Registro de auditoría | Story | 3 | Por hacer |
| TR-08 | AuditLog + aspecto AOP | Task | — | Por hacer |
| HU-05 | Crear un nuevo torneo | Story | 5 | Por hacer |
| TR-09 | Entidad Torneo en PostgreSQL | Task | — | Por hacer |
| TR-10 | Endpoint POST /torneos | Task | — | Por hacer |
| TR-11 | Formulario de creación en React | Task | — | Por hacer |
| HU-08 | Consultar torneos | Story | 2 | Por hacer |
| TR-12 | Endpoint GET /torneos con filtros | Task | — | Por hacer |
| TR-13 | Pantalla de listado de torneos | Task | — | Por hacer |
| HU-09 | Completar perfil deportivo | Story | 3 | Por hacer |
| TR-14 | Entidad Jugador en PostgreSQL | Task | — | Por hacer |
| TR-15 | Endpoint PUT /jugadores/{id}/perfil | Task | — | Por hacer |
| TR-16 | Componente de edición de perfil en React | Task | — | Por hacer |

---

### Sprint 2 – Equipos, Búsqueda, Disponibilidad y Pagos (32 SP)

**Objetivo del Sprint:** Tener funcional la gestión completa de equipos, búsqueda e invitación de jugadores, configuración del torneo y subida de comprobantes de pago.

| ID | Historia / Tarea | Tipo | SP | Estado |
|----|-----------------|------|:---:|--------|
| HU-06 | Configurar el torneo | Story | 8 | Por hacer |
| HU-07 | Iniciar y finalizar el torneo | Story | 3 | Por hacer |
| HU-10 | Marcarse como disponible | Story | 2 | Por hacer |
| HU-11 | Gestionar invitaciones | Story | 3 | Por hacer |
| HU-12 | Crear un equipo de fútbol | Story | 5 | Por hacer |
| HU-13 | Invitar jugadores al equipo | Story | 3 | Por hacer |
| HU-14 | Buscar jugadores disponibles | Story | 3 | Por hacer |
| HU-15 | Validar composición reglamentaria | Story | 3 | Por hacer |
| HU-16 | Subir comprobante de pago | Story | 5 | Por hacer |

---

### Sprint 3 – Pagos (revisión), Alineaciones y Partidos (24 SP)

**Objetivo del Sprint:** Tener operativa la revisión de pagos por el organizador, el módulo de alineaciones visuales y el registro de resultados de partidos.

| ID | Historia / Tarea | Tipo | SP | Estado |
|----|-----------------|------|:---:|--------|
| HU-17 | Revisar y aprobar comprobantes de pago | Story | 3 | Por hacer |
| HU-18 | Consultar estado de inscripción | Story | 2 | Por hacer |
| HU-19 | Registrar alineación del equipo | Story | 5 | Por hacer |
| HU-20 | Consultar alineación del rival | Story | 2 | Por hacer |
| HU-21 | Registrar resultado y estadísticas del partido | Story | 5 | Por hacer |

---

### Sprint 4 – Árbitros, Tabla, Llaves y Estadísticas (28 SP)

**Objetivo del Sprint:** Completar el sistema con tabla de posiciones automática, cuadro eliminatorio, módulo del árbitro y estadísticas del torneo.

| ID | Historia / Tarea | Tipo | SP | Estado |
|----|-----------------|------|:---:|--------|
| HU-22 | Consulta de partidos para árbitro | Story | 2 | Por hacer |
| HU-23 | Tabla de posiciones automática | Story | 5 | Por hacer |
| HU-24 | Llaves eliminatorias automáticas | Story | 8 | Por hacer |
| HU-25 | Estadísticas del torneo | Story | 3 | Por hacer |
| HU-26 | Historial de torneos anteriores | Story | 2 | Por hacer |
