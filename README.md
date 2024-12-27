# Sistema de Gestión de Tareas

Este proyecto es un **sistema básico de gestión de tareas** desarrollado en **Java**, diseñado para demostrar habilidades en programación orientada a objetos (OOP), estructuras de datos, y pruebas unitarias. Es una herramienta funcional que permite a los usuarios crear, editar, eliminar, listar y buscar tareas mediante un menú interactivo en consola.

## Características Principales

- **Estructura de datos personalizada:** Uso de listas enlazadas para almacenar tareas.
- **Programación funcional:** Ordenamiento y filtrado eficiente de tareas usando Streams y lambdas.
- **Diseño modular:** Clases independientes para manejar las tareas y su gestión.
- **Pruebas unitarias:** Validación exhaustiva de las funcionalidades con JUnit.
- **Interfaz amigable:** Menú interactivo para facilitar el uso del sistema.

---

## Tecnologías Utilizadas

- **Lenguaje:** Java 22
- **Pruebas:** JUnit 5.9
- **IDE:** IntelliJ IDEA

---

## Cómo Ejecutar el Proyecto

### Requisitos

- Tener instalado Java 22.
- IntelliJ IDEA o cualquier editor de código compatible con Java.

### Pasos para la Ejecución

1. Clona el repositorio o copia los archivos del proyecto en tu máquina local.
2. Abre el proyecto en IntelliJ IDEA.
3. Asegúrate de configurar correctamente los archivos JAR necesarios para JUnit en el proyecto:
    - `junit-jupiter-api-5.9.2.jar`
    - `junit-jupiter-engine-5.9.2.jar`
4. Compila el proyecto:
   ```bash
   javac -d out src/main/tasks/*.java src/main/TaskManagementSystem.java
   ```
5. Ejecuta la aplicación:
   ```bash
   java -cp out TaskManagementSystem
   ```

---

## Funcionalidades del Sistema

1. **Agregar Tarea:**
    - Crea una nueva tarea con título, descripción, prioridad y estado.

2. **Editar Tarea:**
    - Modifica los atributos de una tarea existente mediante su ID.

3. **Eliminar Tarea:**
    - Elimina una tarea específica identificada por su ID.

4. **Listar Tareas:**
    - Muestra las tareas ordenadas por prioridad (ALTA, MEDIA, BAJA).

5. **Buscar Tareas:**
    - Busca tareas por palabras clave en el título o descripción.

6. **Filtrar Tareas:**
    - Filtra las tareas por estado (PENDIENTE, EN PROGRESO, COMPLETADA).

---

## Pruebas Unitarias

Las pruebas unitarias se encuentran en `src/test/TaskManagerTest.java`. Validan las principales funcionalidades del sistema, incluyendo:

- Creación de tareas.
- Edición de tareas.
- Eliminación de tareas.
- Listado y ordenamiento de tareas.
- Búsqueda y filtrado por estado.

### Ejecutar las Pruebas

1. Asegúrate de que JUnit esté configurado en el proyecto.
2. Corre las pruebas desde IntelliJ IDEA o usando la línea de comandos:
   ```bash
   java -cp "libs/*:src" org.junit.platform.console.ConsoleLauncher --select-class TaskManagerTest
   ```

---

## Estructura del Proyecto

```
TareaModuloDos
├── src
│   ├── main
│   │   ├── Task.java               // Clase que define una tarea.
│   │   ├── TaskManager.java        // Clase que gestiona las operaciones sobre las tareas.
│   │   ├── TaskManagementSystem.java // Clase principal con menú interactivo.
│   ├── test
│       ├── TaskManagerTest.java    // Pruebas unitarias para TaskManager.
├── libs                            // Carpeta con archivos JAR necesarios para JUnit.
└── README.md                       // Este archivo.
```

---

## Autor

**Julio**  
_Desarrollador Backend en formación_

---

## Próximos Pasos

1. Implementar persistencia de datos usando archivos o bases de datos.
2. Crear una interfaz gráfica para el sistema.
3. Optimizar los algoritmos para manejo de tareas con grandes volúmenes de datos.

