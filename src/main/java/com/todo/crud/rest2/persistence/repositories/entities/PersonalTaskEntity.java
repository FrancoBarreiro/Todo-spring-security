package com.todo.crud.rest2.persistence.repositories.entities;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "task_id")
@Table(name = "personal_tasks")
public class PersonalTaskEntity extends TaskEntity {

    public enum Category {EDUCATION, HEALTH, ENTERTAINMENT, FAMILY, TRAVEL, SHOPPING, HOUSEHOLD, OTHERS}

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Override
    public String toString() {
        return "PersonalTaskEntity{" +
                "category=" + category +
                "} " + super.toString();
    }
}
