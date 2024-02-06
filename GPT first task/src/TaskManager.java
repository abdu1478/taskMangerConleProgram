import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Task {
    private final String title;
    private String description;
    private boolean completed;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\nStatus: " + (completed ? "Completed" : "Not Completed");
    }
}

class TaskManaging {
    private ArrayList<Task> tasks;

    public TaskManaging() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markCompleted();
        } else {
            System.out.println("Invalid task index. Please enter a valid index.");
        }
    }


    public void displayAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("Task #" + (i + 1) + ":");
                System.out.println(tasks.get(i));
                System.out.println();
            }
        }
    }
}

public class TaskManager {
    public static void main(String[] args) {
        TaskManaging taskManager = new TaskManaging();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Task Manager Menu ===");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. View All Tasks");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); /*Therefore, by calling scanner.nextLine() immediately after scanner.nextInt(), you ensure that any leftover newline character is consumed,
             and subsequent scanner.nextLine() calls will read the input correctly
             */

                switch (choice) {
                    case 1:
                        System.out.print("Enter task title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter task description: ");
                        String description = scanner.nextLine();
                        taskManager.addTask(new Task(title, description));
                        System.out.println("Task added successfully!");
                        break;
                    case 2:
                        System.out.print("Enter task number to mark as completed: ");
                        int completedTaskIndex = scanner.nextInt();
                        taskManager.markTaskAsCompleted(completedTaskIndex - 1);
                        System.out.println("Task marked as completed!");
                        break;
                    case 3:
                        taskManager.displayAllTasks();
                        break;
                    case 4:
                        System.out.print("Enter task number to delete: ");
                        int deleteTaskIndex = scanner.nextInt();
                        taskManager.deleteTask(deleteTaskIndex - 1);
                        System.out.println("Task deleted successfully!");
                        break;
                    case 5:
                        System.out.println("Exiting... Thank you!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear input buffer
            }
        }
    }
}
