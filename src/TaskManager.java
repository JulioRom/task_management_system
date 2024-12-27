import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks = new LinkedList<>();
    private int nextId = 1;

    public void addTask(String title, String description, Task.Priority priority, Task.Status status) {
        tasks.add(new Task(nextId++, title, description, priority, status));
    }

    public boolean editTask(int id, String newTitle, String newDescription, Task.Priority newPriority, Task.Status newStatus) {
        Optional<Task> taskOptional = tasks.stream().filter(task -> task.getId() == id).findFirst();
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            task.setPriority(newPriority);
            task.setStatus(newStatus);
            return true;
        }
        return false;
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    public List<Task> listTasksByPriority() {
        return tasks.stream()
                .sorted((t1, t2) -> t1.getPriority().compareTo(t2.getPriority()))
                .collect(Collectors.toList());
    }

    public List<Task> searchTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getTitle().contains(keyword) || task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Task> filterTasksByStatus(Task.Status status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }
}
