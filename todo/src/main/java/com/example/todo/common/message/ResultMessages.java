package com.example.todo.common.message;

import static com.example.todo.common.message.MessageType.DANGER;
import static com.example.todo.common.message.MessageType.DARK;
import static com.example.todo.common.message.MessageType.ERROR;
import static com.example.todo.common.message.MessageType.INFO;
import static com.example.todo.common.message.MessageType.LIGHT;
import static com.example.todo.common.message.MessageType.PRIMARY;
import static com.example.todo.common.message.MessageType.SECONDARY;
import static com.example.todo.common.message.MessageType.SUCCESS;
import static com.example.todo.common.message.MessageType.WARNING;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.StringUtils;

public class ResultMessages implements Serializable, Iterable<ResultMessage> {
    private static final long serialVersionUID = -7323120914608188540L;

    private final MessageType type;

    private final List<ResultMessage> list = new ArrayList<ResultMessage>();

    public static final String DEFAULT_MESSAGES_ATTRIBUTE_NAME = StringUtils
            .uncapitalize(ResultMessages.class.getSimpleName());

    public ResultMessages(MessageType type) {
        this(type, (ResultMessage[]) null);
    }

    public ResultMessages(MessageType type, ResultMessage... messages) {
        if (type == null) {
            throw new IllegalArgumentException("type must not be null!");
        }
        this.type = type;
        if (messages != null) {
            addAll(messages);
        }
    }

    public MessageType getType() {
        return type;
    }

    public List<ResultMessage> getList() {
        return list;
    }

    public ResultMessages add(ResultMessage message) {
        if (message != null) {
            this.list.add(message);
        } else {
            throw new IllegalArgumentException("message must not be null");
        }
        return this;
    }

    public ResultMessages add(String code) {
        if (code != null) {
            this.add(ResultMessage.fromCode(code));
        } else {
            throw new IllegalArgumentException("code must not be null");
        }
        return this;
    }

    public ResultMessages add(String code, Object... args) {
        if (code != null) {
            this.add(ResultMessage.fromCode(code, args));
        } else {
            throw new IllegalArgumentException("code must not be null");
        }
        return this;
    }

    public ResultMessages addAll(ResultMessage... messages) {
        if (messages != null) {
            for (ResultMessage message : messages) {
                add(message);
            }
        } else {
            throw new IllegalArgumentException("messages must not be null");
        }
        return this;
    }

    public ResultMessages addAll(Collection<ResultMessage> messages) {
        if (messages != null) {
            for (ResultMessage message : messages) {
                add(message);
            }
        } else {
            throw new IllegalArgumentException("messages must not be null");
        }
        return this;
    }

    public boolean isNotEmpty() {
        return !list.isEmpty();
    }

    public static ResultMessages success() {
        return new ResultMessages(SUCCESS);
    }

    public static ResultMessages info() {
        return new ResultMessages(INFO);
    }

    public static ResultMessages warning() {
        return new ResultMessages(WARNING);
    }

    public static ResultMessages error() {
        return new ResultMessages(ERROR);
    }

    public static ResultMessages danger() {
        return new ResultMessages(DANGER);
    }

    public static ResultMessages primary() {
        return new ResultMessages(PRIMARY);
    }

    public static ResultMessages secondary() {
        return new ResultMessages(SECONDARY);
    }

    public static ResultMessages light() {
        return new ResultMessages(LIGHT);
    }

    public static ResultMessages dark() {
        return new ResultMessages(DARK);
    }

    @Override
    public Iterator<ResultMessage> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return "ResultMessages [type=" + type + ", list=" + list + "]";
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(
            ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
