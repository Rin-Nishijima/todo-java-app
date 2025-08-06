import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoApp {
    // タスクを保存するリスト
    private static List<Todo> todos = new ArrayList<>();
    // ユーザー入力を受け付けるためのScanner
    private static Scanner scanner = new Scanner(System.in);
    // 次のタスクID用のカウンター
    private static int nextId = 1;

    public static void main(String[] args) {
        // 起動時の読み込み
        todos = TodoStorage.load();
        for (Todo t : todos) {
            if (t.getId() >= nextId) {
                nextId = t.getId() + 1; // 次のIDを更新
            }
        }
        // メインループ
        while (true) {
            showMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addTodo();
                    break;
                case 2:
                    listTodos();
                    break;
                case 3:
                    completeTodo();
                    break;
                case 4:
                    deleteTodo();
                    break;
                case 5:
                    TodoStorage.save(todos); // タスクを保存
                    System.out.println("アプリケーションを終了します。");
                    System.exit(0);
                default:
                    System.out.println("無効な選択です。");
            }
        }
    }

    // メニューを表示するメソッド
    private static void showMenu() {
        System.out.println("\n=== Todoアプリケーション ===");
        System.out.println("1: 新規タスクの追加");
        System.out.println("2: タスク一覧の表示");
        System.out.println("3: タスクの完了");
        System.out.println("4: タスクの削除");
        System.out.println("5: アプリケーションの終了");
        System.out.println("選択してください (1-5): ");
    }

    // 新規タスクを追加するメソッド
    private static void addTodo() {
        System.out.print("タスクのタイトルを入力してください: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("タイトルは空にできません。");
            return;
        }

        System.out.print("期限を入力してください (例:2025-08-10 18:00) : ");
        String dueStr = scanner.nextLine().trim();

        LocalDateTime dueDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dueDate = LocalDateTime.parse(dueStr, formatter);
        } catch (Exception e) {
            System.out.println("無効な日時形式です。タスクは追加されません。");
            return;
        }

       Todo.Priority priority = inputPriority();
        if (priority == null) {
            return;
        }
        Todo todo = new Todo(nextId++, title, dueDate, priority);
        todos.add(todo);
        System.out.println("タスクが追加されました: " + todo);
    }

    // タスク一覧を表示するメソッド
    private static void listTodos() {
        if (todos.isEmpty()) {
            System.out.println("タスクはありません。");
            return;
        }

        // 優先度順に並び替え (HIGH → MEDIUM → LOW)
        todos.sort((a, b) -> a.getPriority().compareTo(b.getPriority()));

        System.out.println("\n=== 優先度順 タスク一覧 ===");
        for (Todo todo : todos) {
            System.out.println(todo);
        }
    }

    // タスクを完了させるメソッド
    private static void completeTodo() {
        listTodos();
        if (todos.isEmpty()) return;

        System.out.print("完了するタスクのIDを入力してください: ");
        int id = getIntInput();

        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.setCompleted(true);
                System.out.println("タスクを完了としてマークしました: " + todo);
                return;
            }
        }
        System.out.println("指定されたIDのタスクは見つかりませんでした。");   
    }

    // タスクを削除するメソッド
    private static void deleteTodo() {
        listTodos();
        if (todos.isEmpty()) return;

        System.out.print("削除するタスクのIDを入力してください: ");
        int id = getIntInput();

        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getId() == id) {
                Todo removed = todos.remove(i);
                System.out.println("タスクを削除しました: " + removed);
                return;
            }
        }
        System.out.println("指定されたIDのタスクは見つかりませんでした。");
    }

    // 整数入力を処理するヘルパーメソッド
    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // 無効な入力の場合
        }
    }
    // 優先度を入力するメソッド
    private static Todo.Priority inputPriority() {
        System.out.println("優先度を選択してください");
        System.out.println("1: 高 (HIGH)");
        System.out.println("2: 中 (MEDIUM)");
        System.out.println("3: 低 (LOW)");
        System.out.print("選択 (1-3): ");

        int choice = getIntInput();
        switch (choice) {
            case 1:
                return Todo.Priority.HIGH;
            case 2:
                return Todo.Priority.MEDIUM;
            case 3:
                return Todo.Priority.LOW;
            default:
                System.out.println("無効な選択です。");
                return null;
        }
    }
}
