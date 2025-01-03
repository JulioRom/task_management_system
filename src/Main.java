import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager(); // Gestor de tareas que maneja las operaciones principales
        Scanner scanner = new Scanner(System.in); // Scanner para leer entradas del usuario

        while (true) {
            // Menú interactivo para la gestión de tareas
            System.out.println("\n=== Task Management System ===");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. List Tasks by Priority");
            System.out.println("5. Search Tasks");
            System.out.println("6. Filter Tasks by Status");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt(); // Lee la elección del usuario
            scanner.nextLine(); // Consume el salto de línea

            switch (choice) {
                case 1 -> {
                    // Opción para agregar una nueva tarea
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    Task.Priority priority = readPriority(scanner); // Validación para prioridad
                    Task.Status status = readStatus(scanner); // Validación para estado
                    manager.addTask(title, description, priority, status);
                    System.out.println("Task added successfully!");
                }
                case 2 -> {
                    // Opción para editar una tarea existente
                    int id;
                    while (true) {
                        System.out.print("Enter task ID to edit: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                        if (manager.getTaskById(id) != null) { // Verifica si el ID es válido
                            break;
                        } else {
                            System.out.println("Invalid task ID. Please try again.");
                        }
                    }

                    System.out.print("Enter new title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String description = scanner.nextLine();
                    Task.Priority priority = readPriority(scanner); // Validación para prioridad
                    Task.Status status = readStatus(scanner); // Validación para estado
                    if (manager.editTask(id, title, description, priority, status)) {
                        System.out.println("Task updated successfully!");
                    } else {
                        System.out.println("Task not found!");
                    }
                }
                case 3 -> {
                    // Opción para eliminar una tarea por ID
                    System.out.print("Enter task ID to delete: ");
                    int id = scanner.nextInt();
                    if (manager.deleteTask(id)) {
                        System.out.println("Task deleted successfully!");
                    } else {
                        System.out.println("Task not found!");
                    }
                }
                case 4 -> {
                    // Opción para listar tareas ordenadas por prioridad
                    System.out.println("Tasks by Priority:");
                    manager.listTasksByPriority().forEach(System.out::println);
                }
                case 5 -> {
                    // Opción para buscar tareas por palabra clave
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    manager.searchTasks(keyword).forEach(System.out::println);
                }
                case 6 -> {
                    // Opción para filtrar tareas por estado
                    Task.Status status = readStatus(scanner); // Validación para estado
                    manager.filterTasksByStatus(status).forEach(System.out::println);
                }
                case 7 -> {
                    // Opción para salir del programa
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Método para leer prioridad del usuario con validación
    private static Task.Priority readPriority(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
                return Task.Priority.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Please enter HIGH, MEDIUM, or LOW.");
            }
        }
    }

    // Método para leer estado del usuario con validación
    private static Task.Status readStatus(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter status (PENDING, IN_PROGRESS, COMPLETED): ");
                return Task.Status.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please enter PENDING, IN_PROGRESS, or COMPLETED.");
            }
        }
    }
}
// POTENCIALES MEJORAS FUTURAS:
// 1. Implementar persistencia de datos para guardar las tareas en un archivo o base de datos
// 2. Crear una interfaz gráfica de usuario (GUI) para mejorar la experiencia del usuario
// 3. Añadir opciones avanzadas de filtrado y búsqueda con múltiples criterios
// 4. Implementar una funcionalidad de deshacer para revertir cambios recientes
// 5. Permitir la internacionalización (i18n) para soportar múltiples idiomas
// 6. Optimizar el rendimiento para manejar grandes volúmenes de datos