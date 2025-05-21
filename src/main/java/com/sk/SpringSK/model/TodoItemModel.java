package com.sk.SpringSK.model;

public class TodoItemModel {
    private final double id;
    private String body = "";
    private boolean isDone;

    public TodoItemModel(String body){
        this.id = Math.random();
        this.body = body;
        this.isDone = false;
    }

    public double getId() {
        return id;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public boolean isDone() {
        return isDone;
    }
    public void toggleIsDone() {
        this.isDone = !this.isDone;
    }
}
