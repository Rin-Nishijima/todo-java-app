import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoStorage {
    private static final String FILE_NAME = "todos.txt";

    public static void save(List<Todo> todos) {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            for (Todo todo : todos) {
                writer.printf("%d,%s,%s,%s,%b\n",
                    todo.getId(),
                    todo.getTitle().replace(",", " "), // カンマ対策
                    todo.getDueDate().toString(),
                    todo.getPriority().name(),
                    todo.isCompleted()
                );
            }
        } catch (IOException e) {
            System.out.println("保存に失敗しました: " + e.getMessage());
        }
    }

    public static List<Todo> load() {
        List<Todo> todos = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return todos;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",", 5);
                if (parts.length < 5) continue;

                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                LocalDateTime dueDate = LocalDateTime.parse(parts[2]);
                Todo.Priority priority = Todo.Priority.valueOf(parts[3]);
                boolean completed = Boolean.parseBoolean(parts[4]);

                Todo todo = new Todo(id, title, dueDate, priority);
                todo.setCompleted(completed);
                todos.add(todo);
            }
        } catch (Exception e) {
            System.out.println("読み込みに失敗しました: " + e.getMessage());
        }

        return todos;
    }
}
