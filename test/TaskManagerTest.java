import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private static final String RESULT_FILE = "test_results.txt";

    // Método auxiliar para escribir resultados en el archivo
    private void logResult(String testName, String status, String details) {
        String result = String.format("Test Name: %s\nStatus: %s\nDetails: %s\n\n", testName, status, details);
        try {
            Files.writeString(Path.of(RESULT_FILE), result, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddTask() {
        String testName = "testAddTask";
        try {
            TaskManager manager = new TaskManager();

            // Agrega una tarea al gestor
            manager.addTask("Test Task", "Description", Task.Priority.HIGH, Task.Status.PENDING);

            // Verifica que la tarea fue agregada a la lista
            assertEquals(1, manager.listTasksByPriority().size(), "La lista debería contener exactamente una tarea.");

            // Registrar resultado exitoso
            logResult(testName, "PASSED", "La tarea fue agregada correctamente.");
        } catch (AssertionError | Exception e) {
            // Registrar resultado fallido
            logResult(testName, "FAILED", e.getMessage());
            throw e;
        }
    }

    @Test
    void testEditTask() {
        String testName = "testEditTask";
        try {
            TaskManager manager = new TaskManager();

            // Agrega una tarea inicial
            manager.addTask("Test Task", "Description", Task.Priority.HIGH, Task.Status.PENDING);

            // Edita la tarea con nuevos valores
            boolean edited = manager.editTask(1, "Updated Task", "Updated Description", Task.Priority.MEDIUM, Task.Status.IN_PROGRESS);

            // Verifica que la edición fue exitosa
            assertTrue(edited, "La tarea debería haberse editado correctamente.");

            // Verifica que el título de la tarea se actualizó
            assertEquals("Updated Task", manager.listTasksByPriority().get(0).getTitle(), "El título debería haberse actualizado.");

            // Registrar resultado exitoso
            logResult(testName, "PASSED", "La tarea fue editada correctamente.");
        } catch (AssertionError | Exception e) {
            // Registrar resultado fallido
            logResult(testName, "FAILED", e.getMessage());
            throw e;
        }
    }

    @Test
    void testDeleteTask() {
        String testName = "testDeleteTask";
        try {
            TaskManager manager = new TaskManager();

            // Agrega una tarea inicial
            manager.addTask("Test Task", "Description", Task.Priority.HIGH, Task.Status.PENDING);

            // Elimina la tarea utilizando su ID
            boolean deleted = manager.deleteTask(1);

            // Verifica que la eliminación fue exitosa
            assertTrue(deleted, "La tarea debería haberse eliminado correctamente.");

            // Verifica que la lista de tareas esté vacía después de eliminar la tarea
            assertTrue(manager.listTasksByPriority().isEmpty(), "La lista debería estar vacía después de eliminar la tarea.");

            // Registrar resultado exitoso
            logResult(testName, "PASSED", "La tarea fue eliminada correctamente.");
        } catch (AssertionError | Exception e) {
            // Registrar resultado fallido
            logResult(testName, "FAILED", e.getMessage());
            throw e;
        }
    }

    /**
     * Verifica que se puedan filtrar tareas por estado correctamente.
     */
    @Test
    void testFilterTasksByStatus() {
        String testName = "testFilterTasksByStatus";
        try {
            TaskManager manager = new TaskManager();

            // Agrega varias tareas con diferentes estados
            manager.addTask("Task 1", "Description 1", Task.Priority.HIGH, Task.Status.PENDING);
            manager.addTask("Task 2", "Description 2", Task.Priority.MEDIUM, Task.Status.IN_PROGRESS);
            manager.addTask("Task 3", "Description 3", Task.Priority.LOW, Task.Status.COMPLETED);

            // Filtra tareas pendientes
            List<Task> pendingTasks = manager.filterTasksByStatus(Task.Status.PENDING);

            // Verifica que solo se devuelva una tarea con estado PENDING
            assertEquals(1, pendingTasks.size());
            assertEquals("Task 1", pendingTasks.get(0).getTitle());

            // Registrar resultado exitoso
            logResult(testName, "PASSED", "El filtrado por estado funcionó correctamente.");
        } catch (AssertionError | Exception e) {
            // Registrar resultado fallido
            logResult(testName, "FAILED", e.getMessage());
            throw e;
        }
    }

    /**
     * Verifica que se puedan buscar tareas por palabra clave.
     */
    @Test
    void testSearchTasks() {
        String testName = "testSearchTasks";
        try {
            TaskManager manager = new TaskManager();

            // Agrega tareas con diferentes palabras clave
            manager.addTask("Buy milk", "Go to the store", Task.Priority.HIGH, Task.Status.PENDING);
            manager.addTask("Do homework", "Complete math exercises", Task.Priority.MEDIUM, Task.Status.PENDING);
            manager.addTask("Read book", "Finish the novel", Task.Priority.LOW, Task.Status.PENDING);

            // Busca tareas que contienen "book"
            List<Task> searchResult = manager.searchTasks("book");

            // Verifica que solo se devuelva una tarea con la palabra clave
            assertEquals(1, searchResult.size());
            assertEquals("Read book", searchResult.get(0).getTitle());

            // Registrar resultado exitoso
            logResult(testName, "PASSED", "La búsqueda por palabra clave funcionó correctamente.");
        } catch (AssertionError | Exception e) {
            // Registrar resultado fallido
            logResult(testName, "FAILED", e.getMessage());
            throw e;
        }
    }

    /**
     * Verifica que se pueda obtener una tarea por ID correctamente.
     */
    @Test
    void testGetTaskById() {
        String testName = "testGetTaskById";
        try {
            TaskManager manager = new TaskManager();

            // Agrega una tarea y recupera por ID
            manager.addTask("Test Task", "Description", Task.Priority.HIGH, Task.Status.PENDING);
            Task task = manager.getTaskById(1);

            // Verifica que se devuelva la tarea correcta
            assertNotNull(task);
            assertEquals("Test Task", task.getTitle());

            // Registrar resultado exitoso
            logResult(testName, "PASSED", "La obtención de tarea por ID funcionó correctamente.");
        } catch (AssertionError | Exception e) {
            // Registrar resultado fallido
            logResult(testName, "FAILED", e.getMessage());
            throw e;
        }
    }

    /**
     * Verifica que el sistema maneje correctamente IDs inexistentes.
     */
    @Test
    void testNonExistentTaskId() {
        String testName = "testNonExistentTaskId";
        try {
            TaskManager manager = new TaskManager();

            // Intenta editar una tarea inexistente
            boolean edited = manager.editTask(999, "Title", "Description", Task.Priority.HIGH, Task.Status.PENDING);
            assertFalse(edited, "No debería ser posible editar una tarea inexistente.");

            // Intenta eliminar una tarea inexistente
            boolean deleted = manager.deleteTask(999);
            assertFalse(deleted, "No debería ser posible eliminar una tarea inexistente.");

            // Intenta obtener una tarea inexistente
            Task task = manager.getTaskById(999);
            assertNull(task, "Debería devolver null al buscar una tarea inexistente.");

            // Registrar resultado exitoso
            logResult(testName, "PASSED", "El manejo de IDs inexistentes funcionó correctamente.");
        } catch (AssertionError | Exception e) {
            // Registrar resultado fallido
            logResult(testName, "FAILED", e.getMessage());
            throw e;
        }
    }

    /**
     * Verifica que las tareas se ordenen correctamente por prioridad.
     */
    @Test
    void testListTasksByPriority() {
        String testName = "testListTasksByPriority";
        try {
            TaskManager manager = new TaskManager();

            // Agrega varias tareas con diferentes prioridades
            manager.addTask("High Priority Task", "Description", Task.Priority.HIGH, Task.Status.PENDING);
            manager.addTask("Low Priority Task", "Description", Task.Priority.LOW, Task.Status.PENDING);
            manager.addTask("Medium Priority Task", "Description", Task.Priority.MEDIUM, Task.Status.PENDING);

            // Obtiene tareas ordenadas por prioridad
            List<Task> sortedTasks = manager.listTasksByPriority();

            // Verifica que las tareas estén ordenadas correctamente
            assertEquals("Low Priority Task", sortedTasks.get(2).getTitle());
            assertEquals("Medium Priority Task", sortedTasks.get(1).getTitle());
            assertEquals("High Priority Task", sortedTasks.get(0).getTitle());

            // Registrar resultado exitoso
            logResult(testName, "PASSED", "La lista de tareas por prioridad funcionó correctamente.");
        } catch (AssertionError | Exception e) {
            // Registrar resultado fallido
            logResult(testName, "FAILED", e.getMessage());
            throw e;
        }
    }
}
