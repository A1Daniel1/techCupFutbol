# 📄 Requerimientos del Sistema – TECHCUP FÚTBOL

## 1. Lista general de requerimientos

El sistema de TECHCUP FÚTBOL tiene los siguientes requerimientos:

### 1.1 Requerimientos Funcionales

El sistema de TECHCUP FÚTBOL debe tener la capacidad de:

1. Permitir el registro y autenticación de usuarios con correo institucional (estudiantes, graduados, profesores y personal administrativo) y correo Gmail (familiares), asignando roles y permisos según el tipo de actor.
2. Gestionar el ciclo de vida completo del torneo: creación, configuración (reglamento, canchas, horarios, sanciones, fechas importantes), inicio y finalización por parte del organizador.
3. Permitir la inscripción de equipos mediante la subida de comprobantes de pago, con revisión y aprobación por parte del organizador.
4. Facilitar la creación y gestión de equipos por parte del capitán, incluyendo la invitación de jugadores y la validación automática de las reglas de composición.
5. Ofrecer un módulo de búsqueda de jugadores disponibles filtrable por posición, semestre, edad, género, nombre e identificación.
6. Permitir al capitán registrar la alineación del equipo (titulares, reservas y formación) con representación visual en la cancha, y a los jugadores consultar la alineación del equipo rival.
7. Permitir al organizador registrar los resultados de los partidos (marcador, goleadores y tarjetas).
8. Calcular automáticamente la tabla de posiciones con todos los indicadores estadísticos al registrarse cada resultado.
9. Generar automáticamente las llaves eliminatorias (cuartos de final, semifinal y final) y avanzar a los ganadores al registrarse cada resultado.
10. Proveer estadísticas del torneo: máximos goleadores, historial de partidos, resultados por equipo e historial de torneos anteriores.
11. Registrar un log de auditoría de las acciones relevantes realizadas por los usuarios del sistema.
12. Permitir al árbitro consultar la información de sus partidos asignados (fecha, hora, cancha y equipos).

### 1.2 Requerimientos No Funcionales

El sistema de TECHCUP FÚTBOL debe tener:

1. **Seguridad**: autenticación basada en JWT con expiración de tokens; integración con OAuth2 institucional y Google OAuth2; control de acceso por roles con anotaciones de seguridad en cada endpoint.
2. **Disponibilidad**: el sistema debe estar disponible al menos el 99% del tiempo durante el semestre activo del torneo, especialmente en días de registro de resultados y partidos.
3. **Escalabilidad**: la arquitectura por capas en Spring Boot debe permitir agregar nuevas funcionalidades sin afectar los módulos existentes; el frontend en React debe ser modular y reutilizable.
4. **Rendimiento**: el cálculo de la tabla de posiciones y la actualización de llaves deben completarse en menos de 2 segundos tras el registro de un resultado.
5. **Usabilidad**: la interfaz de usuario debe ser intuitiva, responsiva y accesible desde dispositivos móviles y de escritorio, siguiendo los lineamientos del manual de identidad del proyecto.
6. **Mantenibilidad**: el código debe seguir convenciones de estilo definidas, hacer uso de patrones de diseño documentados y contar con cobertura de pruebas unitarias en la capa de lógica de negocio.
7. **Trazabilidad**: todas las acciones sensibles (aprobación de pagos, cambio de estado de torneo, registro de resultados) deben quedar registradas en el log de auditoría con usuario, fecha y hora.
8. **Portabilidad**: el sistema debe poder desplegarse en cualquier servidor con soporte para Java 17+, Node.js 18+ y PostgreSQL 14+.

---

## 2. Diagramas de caso de uso

### 2.1 Requerimiento Funcional 1 – Autenticación y gestión de roles

| Campo | Descripción |
|-------|-------------|
| **ID** | RF-01 |
| **Nombre del requerimiento** | Autenticación y gestión de roles |
| **Descripción** | El sistema debe permitir que cada actor inicie sesión usando su correo institucional (OAuth2 institucional) o su correo Gmail (Google OAuth2 para familiares), y acceda únicamente a las funcionalidades correspondientes a su rol. |
| **Precondiciones** | El actor debe tener una cuenta de correo válida (institucional o Gmail). El sistema debe tener configurados los proveedores de OAuth2. |
| **Actor** | Estudiante, Graduado, Profesor, Personal Administrativo, Familiar, Administrador |
| **Flujo principal** | 1. El actor accede a la página de inicio de sesión.<br>2. El actor selecciona su método de autenticación (institucional o Gmail).<br>3. El sistema redirige al proveedor OAuth2 correspondiente.<br>4. El proveedor retorna el token de autorización.<br>5. El sistema valida el token, identifica el tipo de usuario por dominio de correo y asigna el rol correspondiente.<br>6. El sistema genera un JWT y redirige al actor al dashboard según su rol. |
| **Diagrama de caso de uso** | *Insertar imagen y link del diagrama* |
| **Poscondiciones** | El actor queda autenticado con un JWT válido y es redirigido a la vista correspondiente a su rol. La acción queda registrada en el log de auditoría. |

---

### 2.2 Requerimiento Funcional 2 – Registro y perfil de jugador

| Campo | Descripción |
|-------|-------------|
| **ID** | RF-02 |
| **Nombre del requerimiento** | Registro y perfil deportivo del jugador |
| **Descripción** | El sistema debe permitir que un participante autenticado complete su perfil deportivo indicando posición(es) de juego, número dorsal, foto y disponibilidad para ser contactado por capitanes. |
| **Precondiciones** | El actor debe estar autenticado en el sistema. Debe existir un torneo en estado Activo o Borrador. |
| **Actor** | Estudiante, Graduado, Profesor, Personal Administrativo, Familiar |
| **Flujo principal** | 1. El jugador accede a la sección "Mi perfil deportivo".<br>2. El jugador completa los campos: posición principal, posición secundaria, número dorsal y sube su foto.<br>3. El jugador activa el indicador de disponibilidad para equipos.<br>4. El sistema valida que el número dorsal no esté duplicado dentro del mismo equipo.<br>5. El sistema persiste el perfil y lo hace visible en el buscador de jugadores para los capitanes. |
| **Diagrama de caso de uso** | *Insertar imagen y link del diagrama* |
| **Poscondiciones** | El perfil del jugador queda actualizado y visible en el módulo de búsqueda de jugadores disponibles si activó la disponibilidad. |

---

### 2.3 Requerimiento Funcional 3 – Creación y gestión de equipos

| Campo | Descripción |
|-------|-------------|
| **ID** | RF-03 |
| **Nombre del requerimiento** | Creación y gestión de equipos |
| **Descripción** | El sistema debe permitir que un capitán cree un equipo con nombre, escudo y colores, e invite jugadores. El sistema debe validar las reglas de composición: mínimo 7 jugadores, máximo 12, y que más del 50% sean de los programas de Ingeniería de Sistemas, IA, Ciberseguridad o Estadística. |
| **Precondiciones** | El capitán debe estar autenticado y tener el rol de Capitán asignado. Debe existir un torneo en estado Activo. |
| **Actor** | Capitán |
| **Flujo principal** | 1. El capitán accede al módulo "Crear equipo".<br>2. El capitán ingresa el nombre del equipo, sube el escudo y define los colores del uniforme.<br>3. El capitán busca jugadores disponibles y envía invitaciones.<br>4. Los jugadores invitados aceptan o rechazan la invitación.<br>5. El sistema valida que el equipo cumpla con las reglas de composición al momento de la inscripción.<br>6. El sistema confirma la creación del equipo y habilita el proceso de inscripción. |
| **Diagrama de caso de uso** | *Insertar imagen y link del diagrama* |
| **Poscondiciones** | El equipo queda registrado en el sistema. El capitán puede gestionar el roster y proceder con el pago de inscripción. |

---

### 2.4 Requerimiento Funcional 4 – Inscripción y verificación de pago

| Campo | Descripción |
|-------|-------------|
| **ID** | RF-04 |
| **Nombre del requerimiento** | Inscripción del equipo y verificación de pago |
| **Descripción** | El sistema debe permitir que el capitán suba el comprobante de pago del equipo. El organizador revisa el documento y cambia el estado de la inscripción (Pendiente → En revisión → Aprobado / Rechazado). Solo los equipos con estado Aprobado podrán participar en el torneo. |
| **Precondiciones** | El equipo debe estar creado y cumplir las reglas de composición. El torneo debe estar en estado Activo con inscripciones abiertas. El capitán debe haber realizado el pago por NEQUI o efectivo. |
| **Actor** | Capitán, Organizador |
| **Flujo principal** | 1. El capitán accede al módulo de inscripción de su equipo.<br>2. El capitán sube el archivo del comprobante de pago (imagen o PDF).<br>3. El sistema registra el pago con estado Pendiente y notifica al organizador.<br>4. El organizador revisa el comprobante desde su dashboard.<br>5. El organizador cambia el estado a En revisión mientras lo analiza.<br>6. El organizador aprueba o rechaza la inscripción con una observación.<br>7. El sistema actualiza el estado del equipo y lo notifica en el dashboard del capitán. |
| **Diagrama de caso de uso** | *Insertar imagen y link del diagrama* |
| **Poscondiciones** | Si el pago es Aprobado, el equipo queda habilitado para participar en el torneo. Si es Rechazado, el capitán puede subir un nuevo comprobante. La acción queda registrada en auditoría. |

---

### 2.5 Requerimiento Funcional 5 – Registro de resultados y estadísticas automáticas

| Campo | Descripción |
|-------|-------------|
| **ID** | RF-05 |
| **Nombre del requerimiento** | Registro de resultados y actualización automática de estadísticas |
| **Descripción** | El sistema debe permitir que el organizador registre el marcador final, los goleadores y las tarjetas de cada partido. Tras el registro, el sistema debe actualizar automáticamente la tabla de posiciones y avanzar las llaves eliminatorias si corresponde. |
| **Precondiciones** | El torneo debe estar en estado En progreso. El partido debe estar programado y con alineaciones registradas. |
| **Actor** | Organizador |
| **Flujo principal** | 1. El organizador accede al módulo de registro de resultados.<br>2. Selecciona el partido correspondiente.<br>3. Ingresa el marcador final (goles local y visitante).<br>4. Selecciona los goleadores indicando el minuto del gol.<br>5. Registra las tarjetas amarillas y rojas con el jugador y el minuto.<br>6. El sistema persiste el resultado y dispara el evento de actualización de tabla de posiciones.<br>7. Si el partido es de fase eliminatoria, el sistema avanza al ganador en la llave correspondiente. |
| **Diagrama de caso de uso** | *Insertar imagen y link del diagrama* |
| **Poscondiciones** | La tabla de posiciones queda actualizada con los nuevos indicadores del partido. Las llaves eliminatorias reflejan el avance del ganador si aplica. Las estadísticas de goleadores se actualizan. |

