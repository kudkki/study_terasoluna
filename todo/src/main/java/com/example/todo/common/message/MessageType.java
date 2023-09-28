package com.example.todo.common.message;

public enum MessageType {

    SUCCESS("success"),

    INFO("info"),

    WARNING("warning"),

    ERROR("error"),

    DANGER("danger"),

    PRIMARY("primary"),

    SECONDARY("secondary"),

    LIGHT("light"),

    DARK("dark");

    private final String type;


    MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString(){
        return this.type;
    }
}
