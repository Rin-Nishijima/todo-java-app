# Java製 Todoリストアプリ

このアプリは、コマンドラインで操作できるJava製のTodoリストアプリケーションです。以下の機能が実装されています：

## ✅ 実装済みの機能

1. **タスクの期限設定**
   - `Todo`クラスに期限（dueDate）フィールドを追加
   - 期限切れのタスクには警告マーク!を表示

2. **タスクの優先度設定**
   - 優先度を表す列挙型（enum）を使用（HIGH / MEDIUM / LOW）
   - 優先度に応じた並び替え機能

3. **データの永続化**
   - タスク情報をファイルに保存（終了時）
   - プログラム起動時にファイルからデータを読み込み

---

## 🛠️ 使い方

### コンパイル

```bash
javac Todo.java TodoApp.java
```

### 実行

```bash
java TodoApp
```

---

## 📂 ファイル構成

```
├── Todo.java        // タスク情報を表すクラス
├── TodoApp.java     // メインアプリケーション（UI含む）
├── todos.csv        // 永続化されるタスクデータ
├── README.md        // このファイル
```

---

## 📄 ライセンスと出典

このアプリケーションは、以下のQiita記事を参考に開発・機能追加を行ったものです：

- [Hemmy7 氏の Qiita 記事「【Java】Todoアプリを作ってみた」](https://qiita.com/Hemmy7/items/64c2e8e70e6b835628bf)

当リポジトリでは MIT ライセンスを採用しています。

---

## 🔗 GitHub リポジトリ

[https://github.com/Rin-Nishijima/todo-java-app](https://github.com/Rin-Nishijima/todo-java-app)

ぜひスターをお願いします ⭐
