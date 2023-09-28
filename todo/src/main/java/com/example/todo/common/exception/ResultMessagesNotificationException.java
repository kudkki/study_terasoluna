package com.example.todo.common.exception;

import com.example.todo.common.message.ResultMessage;
import com.example.todo.common.message.ResultMessages;
public abstract class ResultMessagesNotificationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ResultMessages resultMessages;

    protected ResultMessagesNotificationException(ResultMessages messages){
        this(messages, null);
    }

    public ResultMessagesNotificationException(ResultMessages messages, Throwable cause){
        super(cause);
        if (messages == null){
            throw new IllegalArgumentException("massages must not be null");
        }
        this.resultMessages = messages;
    }

    @Override
    public String getMessage(){
        return resultMessages.toString();
    }

    public ResultMessages getResultMessages(){
        return resultMessages;
    }
}
