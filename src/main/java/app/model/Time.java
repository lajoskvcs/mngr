package app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This class represent the worked time for a task in the database.
 */
@Entity
@Table(name = "times")
public class Time {
    /**
     * The id of the time.
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    /**
     * The {@link app.model.Task} where the time belongs to.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    /**
     * The start date of the time.
     */
    @Column(name = "start_date")
    private LocalDateTime startDate;
    /**
     * The end date of the time.
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * Returns the id of the time.
     * @return the id of the time.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the time.
     * @param id the id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the task where the time belongs to.
     * @return the task where the time belongs to
     */
    public Task getTask() {
        return task;
    }

    /**
     * Sets the task where the time belongs to.
     * @param task the task to be set
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Returns the start date of the time.
     * @return The start date of the time
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the time.
     * @param startDate the date to be set.
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Return the end date of the time.
     * @return the end date of the time
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the time.
     * @param endDate the date to be set
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
