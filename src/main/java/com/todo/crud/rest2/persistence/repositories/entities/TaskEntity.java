package com.todo.crud.rest2.persistence.repositories.entities;



import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TaskEntity {

    public enum EnumStatusEntity {PENDING, IN_PROGRESS, COMPLETED}
    public enum EnumTypeEntity {PERSONAL, WORK}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long id;

    @Column(name = "task_title")
    private String title;

    @Column(name = "task_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumStatusEntity status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EnumTypeEntity taskType;

}
