package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a project in the database.
 */
@Entity
@Table(name = "project")
public class Project {
    private int id;
    private String name;
    private String description;
    private LocalDate dueDate;
    private Collection<Task> tasks;
    private Set<User> users = new HashSet<User>(0);
    private Note note;

    private double projectStatus;
    private boolean isDue;

    /**
     * Returns the project's current status in percentage.
     * @return the project's current status in percentage
     */
    @Transient
    public double getProjectStatus() {
        if(tasks == null || tasks.isEmpty()) {
            return 0;
        }
        double allTasks = (double) tasks.size();
        double doneTasks = (double) tasks.stream().filter(
                task -> {
                    return task.getStatus() == 2;
                }
        ).count();
        return (doneTasks / allTasks * 100);
    }

    /**
     * Returns the due status of the project.
     * @return the due status of the project
     */
    @Transient
    public boolean isDue() {
        LocalDate now = LocalDate.now();
        return (dueDate.isBefore(now));
    }

    /**
     * Return the id of the project.
     * @return the id of the project.
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the project.
     * @param id the if to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the project.
     * @return the name of the project
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the project.
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the project.
     * @return the description of the project
     */
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the project.
     * @param description the description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the due date of the project.
     * @return the due date of the project
     */
    @Column(name = "due_date")
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the project.
     * @param dueDate the date to be set
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the users of the project.
     * @return the users of the project
     */
    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name="user_project",
            joinColumns=@JoinColumn(name="project_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Sets the users for the project.
     * @param users the users to be set
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Returns the tasks for the project.
     * @return the tasks for the project.
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonIgnore
    public Collection<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the tasks for the project.
     * @param tasks the tasks to be set
     */
    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the {@link app.model.Note} what belongs to the {@link app.model.Project}.
     * @return the {@link app.model.Note} what belongs to the {@link app.model.Project}
     */
    @JsonIgnore
    @OneToOne(mappedBy = "project", cascade = CascadeType.REMOVE)
    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", users=" + users.toString() +
                '}';
    }
}
