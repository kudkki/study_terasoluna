package com.example.todo.domain.service;

import com.example.todo.common.exception.BusinessException;
import com.example.todo.common.exception.ResourceNotFoundException;
import com.example.todo.common.message.ResultMessage;
import com.example.todo.common.message.ResultMessages;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.JpaTodoRepository;
import com.example.todo.domain.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final long MAX_UNFINISHED_COUNT = 5;

    private final JpaTodoRepository todoRepository;
//    private final TodoRepository todoRepository;

    @Autowired
//    public TodoServiceImpl(TodoRepository todoRepository) {

    public TodoServiceImpl(JpaTodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional(readOnly = true)   // if Cant resolve make sure import package from springframework
    public Collection<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo create(Todo todo) {
        long unfinishedCount = todoRepository.countByFinished(false);
        if(unfinishedCount > MAX_UNFINISHED_COUNT){
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage.fromText("[E001] The count of un-finished Todo must not be over " + MAX_UNFINISHED_COUNT));

            throw new BusinessException(messages);
        }
        String todoId = UUID.randomUUID().toString();
        Date createdAt = new Date();
        todo.setTodoId(todoId);
        todo.setCreatedAt(createdAt);
        todo.setFinished(false);

//        todoRepository.create(todo);
//         REMOVE THIS LINE IF YOU USE JPA
            todoRepository.save(todo); // (8)
        // REMOVE THIS LINE IF YOU USE JPA */

        return todo;
    }

    @Override
    public Todo finish(String todoId) {
        Todo todo = findOne(todoId);
        if(todo.isFinished()){
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage.fromText("[E002] Requested todo is already finished : " + todoId));
            throw new BusinessException(messages);
        }
        todo.setFinished(true);
//        todoRepository.update(todo);
//         /* REMOVE THIS LINE IF YOU USE JPA
            todoRepository.save(todo); // (9)
        // REMOVE THIS LINE IF YOU USE JPA */
        return todo;
    }

    @Override
    public void delete(String todoId) {
        Todo todo = findOne(todoId);
        todoRepository.delete(todo);
    }

    private Todo findOne(String todoId){
        return todoRepository.findById(todoId).orElseThrow(() -> {
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage.fromText("[E404] The requested todo is not found : " + todoId));
            return new ResourceNotFoundException(messages);
        });
    }
}
