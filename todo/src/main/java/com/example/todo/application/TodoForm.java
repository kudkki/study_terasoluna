package com.example.todo.application;

import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class TodoForm implements Serializable {

    public static interface TodoCreate {}
    public static interface TodoFinish {}
    public static final long serialVersionUID = 1L;

    @NotNull(groups = {TodoFinish.class})
    private String todoId;

    @NotNull(groups = {TodoCreate.class})
    @Size(min = 1, max = 30, groups = {TodoCreate.class})
    private String todoTitle;

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }
}
