package com.todo.crud.rest2.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "reference")
@JsonSubTypes({
        @JsonSubTypes.Type(value = WorkTask.class, name = "WORK"),
        @JsonSubTypes.Type(value = PersonalTask.class, name = "PERSONAL")})
public abstract class Task {
    public enum EnumStatus {PENDING, IN_PROGRESS, COMPLETED}
    public enum EnumType {PERSONAL, WORK}

    private long id;
    @NotBlank(message = "Please add a title")
    private String title;
    @Length(min = 3, max = 20, message = "The description must have a minimum of 3 characters and a maximum of 20")
    private String description;
    @NotNull(message = "Status cannot be empty")
    private EnumStatus status;
    @NotNull(message = "Type cannot be empty")
    private EnumType taskType;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", taskType=" + taskType +
                '}';
    }
}
