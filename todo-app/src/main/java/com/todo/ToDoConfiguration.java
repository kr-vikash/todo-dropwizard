package com.todo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ToDoConfiguration extends Configuration {
    // TODO: implement service configuration

    @NotEmpty
    @JsonProperty("username")
    private String username;

    public String getUsername(){
       return this.username;
    }

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory(){
        return database;
    }
}
