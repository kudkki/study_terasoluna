package com.example.todo.common.exception;

import com.example.todo.common.message.ResultMessage;
import com.example.todo.common.message.ResultMessages;
public class BusinessException extends ResultMessagesNotificationException {
    private static final long serialVersionUID = 1L;

    public BusinessException(String message){
        super(ResultMessages.error().add(ResultMessage.fromText(message)));
    }

    public BusinessException(ResultMessages resultMessages){
        super(resultMessages);
    }

    public BusinessException(ResultMessages resultMessages, Throwable cause){
        super(resultMessages, cause);
    }
}
