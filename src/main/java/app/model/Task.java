package app.model;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "status", nullable = false)
    private int status = 0;
    @Column(name = "priority", nullable = false)
    private int priority = 0;
    @Column(name = "sort_number", nullable = false)
    private int sortNumber = 0;
    @Column(name = "description")
    private String description = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
