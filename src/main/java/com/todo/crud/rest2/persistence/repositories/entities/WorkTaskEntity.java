package com.todo.crud.rest2.persistence.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "task_id")
@Table(name = "work_tasks")
public class WorkTaskEntity extends TaskEntity {

    @Column(name = "project")
    private String project;

    public void setProject(String project) {
        this.project = project;
    }

    public String getProject() {
        return project;
    }

}
