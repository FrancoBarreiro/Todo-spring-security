package com.todo.crud.rest2.models;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.todo.crud.rest2.persistence.repositories.entities.TaskEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("WORK")
public class WorkTask extends Task {

    @NotBlank
    private String project;

    public WorkTask(long id, String title, String description, EnumStatus status, EnumType taskType, String project) {
        super(id, title, description, status, taskType);
        this.project = project;
    }
    
    @Override
    public String toString() {
        return "WorkTask{" +
                "project='" + project + '\'' +
                "} " + super.toString();
    }
}

