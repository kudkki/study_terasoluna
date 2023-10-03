package com.example.todo.application;

import com.example.todo.common.exception.BusinessException;
import com.example.todo.common.message.ResultMessage;
import com.example.todo.common.message.ResultMessages;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.TodoService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Objects;

@Controller
@RequestMapping("todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ModelAttribute
    public TodoForm setupForm(){
        TodoForm form = new TodoForm();
        return form;
    }

    @GetMapping("list")
    public String list(Model model){
        Collection<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos  );

        Object result = model.getAttribute("resultMessages");
        if(Objects.nonNull(result)){
            String allMessages = "";
            ResultMessages resultMessages = (ResultMessages) result;
            for (ResultMessage message : resultMessages.getList()) {
                allMessages = allMessages + ":" + message.getText();
            }
            model.addAttribute("messagesPanel", allMessages);
        }

        return "todo/list";
    }

    @PostMapping("create")
    public String create(@Validated({Default.class, TodoForm.TodoCreate.class}) TodoForm todoForm,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes attributes){

        if(bindingResult.hasErrors()){
            System.out.println("bindingResult has Errors");
            return list(model);
        }

        Todo todo = new Todo();
        todo.setTodoTitle(todoForm.getTodoTitle());

//        System.out.println("debug");

        try {
            todoService.create(todo);
        } catch(BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Create success")));

        return "redirect:/todo/list";
    }

    @PostMapping("finish")
    public String finish(
            @Validated({Default.class, TodoForm.TodoFinish.class}) TodoForm todoForm,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes
    ){
        if(bindingResult.hasErrors()){
            return list(model);
        }
        try{
            todoService.finish(todoForm.getTodoId());
        }catch (BusinessException e){
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Finish success")));

        return "redirect:/todo/list";
    }

    @PostMapping("delete")
    public String delete(
            @Validated({Default.class, TodoForm.TodoDelete.class}) TodoForm todoForm,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes
    ){
        if(bindingResult.hasErrors()){
            return list(model);
        }
        try{
            todoService.delete(todoForm.getTodoId());
        }catch (BusinessException e){
            model.addAttribute(e.getResultMessages());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessages.success().add(ResultMessage.fromText("Delete success")));

        return "redirect:/todo/list";
    }
}
