package com.todo.crud.rest2.models;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.todo.crud.rest2.persistence.repositories.entities.PersonalTaskEntity;
import com.todo.crud.rest2.persistence.repositories.entities.TaskEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("PERSONAL")
public class PersonalTask extends Task {
    public PersonalTask(long id, String title, String description, TaskEntity.EnumStatusEntity status, TaskEntity.EnumTypeEntity taskType, PersonalTaskEntity.Category category) {
    }

    public enum Category {EDUCATION, HEALTH, ENTERTAINMENT, FAMILY, TRAVEL, SHOPPING, HOUSEHOLD, OTHERS}

    @NotNull(message = "The category cannot be empty")
    private Category category;

    public PersonalTask(long id, String title, String description, EnumStatus status, EnumType taskType, Category category) {
        super(id, title, description, status, taskType);
        this.category = category;
    }

    @Override
    public String toString() {
        return "PersonalTask{" +
                "category=" + category +
                "} " + super.toString();
    }
}
