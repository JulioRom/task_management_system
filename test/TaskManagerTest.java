import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @Test
    void testAddTask() {
        TaskManager manager = new TaskManager();
        manager.addTask("Test Task", "Description", Task.Priority.HIGH, Task.Status.PENDING);
        assertEquals(1, manager.listTasksByPriority().size());
    }

    @Test
    void testEditTask() {
        TaskManager manager = new TaskManager();
        manager.addTask("Test Task", "Description", Task.Priority.HIGH, Task.Status.PENDING);
        boolean edited = manager.editTask(1, "Updated Task", "Updated Description", Task.Priority.MEDIUM, Task.Status.IN_PROGRESS);
        assertTrue(edited);
        assertEquals("Updated Task", manager.listTasksByPriority().get(0).getTitle());
    }

    @Test
    void testDeleteTask() {
        TaskManager manager = new TaskManager();
        manager.addTask("Test Task", "Description", Task.Priority.HIGH, Task.Status.PENDING);
        boolean deleted = manager.deleteTask(1);
        assertTrue(deleted);
        assertTrue(manager.listTasksByPriority().isEmpty());
    }
}
