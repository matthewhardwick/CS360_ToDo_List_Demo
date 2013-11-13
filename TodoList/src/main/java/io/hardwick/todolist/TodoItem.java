package io.hardwick.todolist;

public class TodoItem {
    private String todo_string;
    private boolean todo_complete;

    public TodoItem(String todo_string, boolean todo_complete) {
        this.todo_string = todo_string;
        this.todo_complete = todo_complete;
    }

    public TodoItem() {
        this.todo_string = "";
        this.todo_complete = false;
    }

    @Override
    public String toString() {
        return todo_string + ',' + todo_complete;
    }

    public String getTodo_string() {
        return todo_string;
    }

    public void setTodo_string(String todo_string) {
        this.todo_string = todo_string;
    }

    public boolean isTodo_complete() {
        return todo_complete;
    }

    public void setTodo_complete(boolean todo_complete) {
        this.todo_complete = todo_complete;
    }
}
