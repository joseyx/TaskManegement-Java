### 👤 Información del candidato
- Nombre completo: Jose Alejandro Rojas Campero
- Correo: josea.rcampero@gmail.com
- Teléfono: +58 414 5813740


# Task Manager API 📝

Sistema de gestión de tareas desarrollado como parte de la prueba técnica para el puesto de Desarrollador FullStack en ADITUMCR.

## 🛠️ Tecnologías utilizadas

- **Backend:** Java 21 + Spring Boot
- **Base de datos:** MySQL
- **Gestión de dependencias:** Maven
- **Variables de entorno:** dotenv-java

## 📋 Funcionalidades

- Crear, listar, editar y eliminar tareas
- Cada tarea tiene un nombre, una descripción y un estado (completada o no)

## 🚀 Cómo ejecutar el proyecto localmente

### 1. Clonar el repositorio

```bash
git clone https://github.com/joseyx/TaskManegement-Java
cd TaskManegement-Java
```

### 2. Configurar base de datos

Asegúrate de tener MySQL corriendo y crea una base de datos llamada:

```bash
CREATE DATABASE taskmanager_db;
```

### 3. Configurar las variables de entorno

Este proyecto usa variables de entorno para manejar la conexión a la base de datos.

1. Copia el archivo `.env.example` como `.env`:

   ```bash
   cp .env.example .env
   ```
2. Abre el archivo .env y completa tus credenciales de MySQL:

    ```
    DB_HOST=localhost
    DB_PORT=3306
    DB_NAME=taskmanager_db
    DB_USER=tu_usuario_aqui
    DB_PASSWORD=tu_contraseña_aqui
    ```
#### ⚠️ Si tu usuario de MySQL no tiene contraseña, puedes dejar DB_PASSWORD vacío.

### 4. Ejecutar la aplicación
Usa tu IDE (IntelliJ, Spring Tool Suite, etc.) o ejecuta desde consola:
```bash
./mvnw spring-boot:run
```
O si usas Maven instalado:
```bash
mvn spring-boot:run
```
La API estará disponible en:
👉 http://localhost:8080/api/tasks

### 📂 Estructura de la API
Modelo de Tarea
```json
{
  "id": 1,
  "name": "Aprender",
  "description": "Aprender Spring Boot",
  "completed": false,
  "createdAt": "2025-04-28T13:20:52.495058",
  "updatedAt": "2025-04-28T13:22:57.210828"
}
```

### 📌 Endpoints de la API

| Método | Endpoint                   | Descripción                                  | Cuerpo (JSON)                                      | Respuesta              |
|--------|----------------------------|----------------------------------------------|----------------------------------------------------|------------------------|
| GET    | `/api/tasks`               | Obtener todas las tareas                     | —                                                  | Lista de tareas (DTO)  |
| GET    | `/api/tasks/{id}`          | Obtener una tarea por su ID                  | —                                                  | Tarea (DTO)            |
| POST   | `/api/tasks`               | Crear una nueva tarea                        | `{ "name": "Texto, "description": "Texto", "completed": false }`   | Tarea creada (DTO)     |
| PUT    | `/api/tasks/{id}`          | Actualizar una tarea existente               | `{ "name": "Texto", description": "Texto", "completed": true }`    | Tarea actualizada (DTO)|
| PATCH  | `/api/tasks/{id}/toggle`   | Alternar el estado de completada/no completada | —                                               | Tarea actualizada (DTO)|
| DELETE | `/api/tasks/{id}`          | Eliminar una tarea por su ID                 | —                                                  | —                      |

## 📄 Paginación, Ordenamiento y Filtrado

El endpoint principal `/api/tasks` soporta **paginación**, **ordenamiento** y **filtrado por estado** desde el backend.

- **Paginación:** Usa los parámetros `page` (número de página, empezando en 0) y `size` (cantidad de elementos por página).
- **Ordenamiento:** Usa el parámetro `sort`, por ejemplo: `sort=createdAt,desc` o `sort=name,asc`.
- **Filtrado:** Puedes filtrar por estado usando el parámetro `completed` (`true` o `false`).

**Ejemplo de request:**


```http
GET /api/tasks?page=0&size=10&sort=createdAt,desc&completed=true
```
**Ejemplo de respuesta:**
```json
{
  "content": [ ...tareas... ],
  "totalPages": 1,
  "totalElements": 3,
  "number": 0,
  "size": 10,
  ...
}
```


