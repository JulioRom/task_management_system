import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Task Management System ===");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. List Tasks by Priority");
            System.out.println("5. Search Tasks");
            System.out.println("6. Filter Tasks by Status");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
                    Task.Priority priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Enter status (PENDING, IN_PROGRESS, COMPLETED): ");
                    Task.Status status = Task.Status.valueOf(scanner.nextLine().toUpperCase());
                    manager.addTask(title, description, priority, status);
                    System.out.println("Task added successfully!");
                }
                case 2 -> {
                    System.out.print("Enter task ID to edit: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter new priority (HIGH, MEDIUM, LOW): ");
                    Task.Priority priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Enter new status (PENDING, IN_PROGRESS, COMPLETED): ");
                    Task.Status status = Task.Status.valueOf(scanner.nextLine().toUpperCase());
                    if (manager.editTask(id, title, description, priority, status)) {
                        System.out.println("Task updated successfully!");
                    } else {
                        System.out.println("Task not found!");
                    }
                }
                case 3 -> {
                    System.out.print("Enter task ID to delete: ");
                    int id = scanner.nextInt();
                    if (manager.deleteTask(id)) {
                        System.out.println("Task deleted successfully!");
                    } else {
                        System.out.println("Task not found!");
                    }
                }
                case 4 -> {
                    System.out.println("Tasks by Priority:");
                    manager.listTasksByPriority().forEach(System.out::println);
                }
                case 5 -> {
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    manager.searchTasks(keyword).forEach(System.out::println);
                }
                case 6 -> {
                    System.out.print("Enter status to filter (PENDING, IN_PROGRESS, COMPLETED): ");
                    Task.Status status = Task.Status.valueOf(scanner.nextLine().toUpperCase());
                    manager.filterTasksByStatus(status).forEach(System.out::println);
                }
                case 7 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
