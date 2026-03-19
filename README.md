Preguntas sobre la estructura de paquetes en Spring Boot

1. Controller:
	- El paquete **Controller** contiene las clases que exponen la API (endpoints REST). Su responsabilidad es recibir las solicitudes HTTP, validar parámetros básicos y delegar la lógica al `Service`.

2. Service:
	- El paquete **Service** incluye la lógica de negocio de la aplicación. Aquí se implementan las reglas, orquestación entre repositorios y otras operaciones que no pertenecen a la capa de persistencia ni a la presentación.

3. Repository:
	- El paquete **Repository** gestiona el acceso a datos (persistencia). Normalmente contiene interfaces que extienden `JpaRepository` o similares para realizar consultas a la base de datos.

4. Controller (repetido):
	- Igual que la respuesta 1: controla las rutas/recursos expuestos y delega en `Service` para el procesamiento.

5. Entity:
	- El paquete **Entity** define las clases que representan las tablas (o documentos) de la base de datos. Incluye anotaciones JPA (`@Entity`, `@Id`, etc.) y mapeo de campos.

6. DTO:
	- El paquete **DTO** (Data Transfer Objects) contiene estructuras usadas para transferir datos entre capas o hacia/desde el cliente. Sirven para encapsular solo los campos necesarios y evitar exponer entidades directamente.

7. Exception:
	- El paquete **Exception** centraliza las clases de excepción y manejadores (custom exceptions y `@ControllerAdvice`). Facilita el manejo consistente de errores y la limpieza del código de negocio.

Si quieres, puedo ajustar la redacción o traducirlo a otro formato (FAQ, tabla, etc.).

