package com.sk.SpringSK.plugins;

import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import com.microsoft.semantickernel.semanticfunctions.annotations.KernelFunctionParameter;

import com.sk.SpringSK.model.TodoItemModel;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<TodoItemModel> todolist = new ArrayList<>();

    private void printItem(double id){
        for(TodoItemModel item : todolist){
            double itemId = item.getId();
            if(itemId == id){
                System.out.println("------------------------------");
                System.out.println("ID >> " + itemId);
                System.out.println("Content >> " + item.getBody());
                System.out.println("Done >> " + item.isDone());
                System.out.println("------------------------------");
            }
        }
    }

    @DefineKernelFunction(name = "get_list", description = "get list's all contents")
    public void getList(){
        for (TodoItemModel item : this.todolist){
            printItem(item.getId());
        }
    }

    @DefineKernelFunction(name = "add_todo", description = "add a todo in todolist")
    public void addTodo(
            @KernelFunctionParameter(name = "body", description = "todo item's body content") String body
    ){
        TodoItemModel item = new TodoItemModel(body);
        this.todolist.add(item);
        System.out.println("Item Add >> ");
        printItem(item.getId());
    }

    @DefineKernelFunction(name="toggle_todo", description = "toggle a todo's isdone state")
    public void toggleTodo(
            @KernelFunctionParameter(name="id", description = "todo item's id") double id
    ){
        for (TodoItemModel item : this.todolist) {
            double itemId = item.getId();
            if (id==itemId){
                item.toggleIsDone();
            }
        }
        System.out.println("Item State Changed >> ");
        printItem(id);
    }

    @DefineKernelFunction(name = "delete_todo", description = "delete a todo item with it's id")
    public void deleteTodo(
            @KernelFunctionParameter(name="id", description = "id to delete") double id
    ){
        todolist.removeIf(item -> item.getId() == id);
        System.out.println("Removed item >> ");
        printItem(id);
    }

    @DefineKernelFunction(name = "update_todo", description = "update todo item's content")
    public void updateTodo(
            @KernelFunctionParameter(name="id", description = "todo item's id to update") double id,
            @KernelFunctionParameter(name="content", description = "content to update") String update_content
    ){
        for (TodoItemModel item : this.todolist) {
            double itemId = item.getId();
            if (id==itemId){
                item.setBody(update_content);
            }
        }
        System.out.println("Updated content >> ");
        printItem(id);
    }
}
