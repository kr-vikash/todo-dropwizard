package com.todo;

import com.todo.db.Task;
import com.todo.db.TodoList;
import com.todo.db.dao.ToDoListDAO;
import com.todo.resources.ToDoResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ToDoApplication extends Application<ToDoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ToDoApplication().run(args);
    }

    @Override
    public String getName() {
        return "TodoList";
    }

    private final HibernateBundle<ToDoConfiguration> hibernateBundle = new HibernateBundle<ToDoConfiguration>(TodoList.class, Task.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(ToDoConfiguration dataSourceConfig) {
            return dataSourceConfig.getDataSourceFactory();
        }
    };



    @Override
    public void initialize(final Bootstrap<ToDoConfiguration> bootstrap) {

        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final ToDoConfiguration configuration,
                    final Environment environment) throws Exception {
        final ToDoListDAO toDoListDAO = new ToDoListDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new ToDoResource(toDoListDAO));
    }

}
