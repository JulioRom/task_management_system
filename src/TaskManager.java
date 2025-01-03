import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskManager {
    // Lista que almacena las tareas, implementada como una LinkedList
    private final List<Task> tasks = new LinkedList<>();

    // Contador para asignar IDs únicos a las tareas
    private int nextId = 1;

    /**
     * Agrega una nueva tarea a la lista de tareas.
     * @param title El título de la tarea.
     * @param description La descripción de la tarea.
     * @param priority La prioridad de la tarea (HIGH, MEDIUM, LOW).
     * @param status El estado de la tarea (PENDING, IN_PROGRESS, COMPLETED).
     */
    public void addTask(String title, String description, Task.Priority priority, Task.Status status) {
        // Crea una nueva tarea con un ID único y la agrega a la lista
        tasks.add(new Task(nextId++, title, description, priority, status));
    }

    /**
     * Edita una tarea existente según su ID.
     * @param id El ID de la tarea a editar.
     * @param newTitle El nuevo título para la tarea.
     * @param newDescription La nueva descripción para la tarea.
     * @param newPriority La nueva prioridad de la tarea.
     * @param newStatus El nuevo estado de la tarea.
     * @return true si la tarea se editó correctamente, false si no se encontró la tarea.
     */
    public boolean editTask(int id, String newTitle, String newDescription, Task.Priority newPriority, Task.Status newStatus) {
        // Busca la tarea por su ID utilizando streams
        Optional<Task> taskOptional = tasks.stream().filter(task -> task.getId() == id).findFirst();

        // Si se encuentra la tarea, actualiza sus atributos
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            task.setPriority(newPriority);
            task.setStatus(newStatus);
            return true;
        }

        // Si no se encuentra, devuelve false
        return false;
    }

    /**
     * Elimina una tarea por su ID.
     * @param id El ID de la tarea a eliminar.
     * @return true si la tarea fue eliminada, false si no se encontró.
     */
    public boolean deleteTask(int id) {
        // Elimina la tarea si coincide su ID
        return tasks.removeIf(task -> task.getId() == id);
    }

    /**
     * Lista todas las tareas ordenadas por prioridad.
     * @return Una lista de tareas ordenadas por prioridad (LOW a HIGH).
     */
    public List<Task> listTasksByPriority() {
        // Ordena las tareas por prioridad y devuelve la lista resultante
        return tasks.stream()
                .sorted((t1, t2) -> t1.getPriority().compareTo(t2.getPriority()))
                .collect(Collectors.toList());
    }

    /**
     * Busca tareas cuyo título o descripción contenga una palabra clave.
     * @param keyword La palabra clave para buscar.
     * @return Una lista de tareas que coinciden con la búsqueda.
     */
    public List<Task> searchTasks(String keyword) {
        // Filtra tareas que contengan la palabra clave en el título o descripción
        return tasks.stream()
                .filter(task -> task.getTitle().contains(keyword) || task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Filtra tareas según su estado.
     * @param status El estado para filtrar (PENDING, IN_PROGRESS, COMPLETED).
     * @return Una lista de tareas con el estado especificado.
     */
    public List<Task> filterTasksByStatus(Task.Status status) {
        // Filtra tareas por estado específico
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}

