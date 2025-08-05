import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Todo {
    // フィールド (クラスの属性)
    private int id;             // タスクID
    private String title;       // タスクのタイトル
    private LocalDateTime createdAt; // 作成日時
    private boolean completed; // 完了状態
    private LocalDateTime dueDate; // 期限
    private Priority priority; // 優先度
    
    // コンストラクタ (newでインスタンス化する時に呼ばれる)
    public Todo(int id, String title, LocalDateTime dueDate, Priority priority) {
        this.id = id;
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.completed = false;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    // 優先度を表す列挙型
    public enum Priority {
        HIGH, // 高
        MEDIUM, // 中
        LOW     // 低
    }

    // getter/setterメソッド
    public  int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isOverdue() {
        return !completed && dueDate.isBefore(LocalDateTime.now());
    }

    // タスクの情報を文字列で返すメソッド
    @Override
    public String toString() {
        String status = completed ? "[完了]" : "[未完了]";
        String overDueMark = isOverdue() ? "⚠期限切れ" : "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%d: %s %s (期限: %s, 優先度: %s)%s", id, title, status, dueDate.format(formatter), priority, overDueMark);
    }
}
