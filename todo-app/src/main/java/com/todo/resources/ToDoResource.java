package com.todo.resources;


import com.todo.db.TodoList;
import com.todo.db.dao.ToDoListDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ToDoResource {
    private static Logger logger = LoggerFactory.getLogger(ToDoResource.class);

    private final ToDoListDAO toDoListDAO;

    private String username;

    public ToDoResource(ToDoListDAO toDoListDAO, String username){
        this.toDoListDAO = toDoListDAO;
        this.username = username;
    }

    @POST
    @UnitOfWork
    public long createToDo(TodoList todoList){
        logger.info("Create todo!!!");
        todoList.setUsername(username);
        return toDoListDAO.create(todoList);
    }

    @GET
    @UnitOfWork
    public List<TodoList> getAllTodos(){
        logger.info("Get all todos!!!");
           return toDoListDAO.findAll();
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public TodoList updateTodo(@PathParam("id") long id, TodoList todoList){
        logger.info("Update todo !!!");
        todoList.setUsername(username);
        return toDoListDAO.update(todoList);

    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public String deleteTodo(@PathParam("id") long id){
        id =toDoListDAO.delete(id);
        String resp = "Deleted todo with id: "+ id;
        logger.info(resp);
        return resp;
    }
}
