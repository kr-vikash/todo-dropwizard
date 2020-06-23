package com.todo.db.dao;

import com.todo.db.TodoList;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.internal.CriteriaImpl;

import java.util.List;

public class ToDoListDAO extends AbstractDAO<TodoList> {

    public ToDoListDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public TodoList findById(long id){
        return get(id);
    }

    public long create(TodoList task){
        return persist(task).getId();
    }

    public TodoList update(TodoList task){
        return persist(task);
    }

    public long delete(long id){
        TodoList task = get(id);
        currentSession().delete(task);
        return id;
    }

    public List<TodoList> findAll(){
        return list(namedQuery("com.todo.db.TodoList.findAll"));
    }
}
