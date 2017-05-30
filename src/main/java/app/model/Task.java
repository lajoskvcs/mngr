package app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * This class represents a task in the database.
 */
@Entity
@Table(name = "tasks")
public class Task {
    /**
     * The id of the task.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    /**
     * The {@link app.model.Project} where the task belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * The name of the task.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The status of the task.
     */
    @Column(name = "status", nullable = false)
    private int status = 0;

    /**
     * The priority of the task.
     */
    @Column(name = "priority", nullable = false)
    private int priority = 0;

    /**
     * The sorting number of the task.
     */
    @Column(name = "sort_number", nullable = false)
    private int sortNumber = 0;

    /**
     * The description of the task.
     */
    @Column(name = "description")
    private String description = "";

    /**
     * The hourly salary of the task.
     */
    @Column(name = "hourly_salary")
    private int hourlySalary = 2000;

    /**
     * The time entries for the task.
     */
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Time> times;

    /**
     * The total worked hours computed from time the entries.
     */
    @Transient
    private Double workedHours;

    /**
     * The summed price of the {@code Materials}.
     */
    @Transient
    private Double summedMaterialPrice;

    /**
     * The summed payment computed by the {@code summedMaterialPrice} and the {@code workedHours}.
     */
    @Transient
    private Double totalPayment;

    /**
     * The total profit of the {@code Task}.
     */
    @Transient
    private Double totalProfit;

    @JsonManagedReference
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Material> materials;

    /**
     * Returns the id of the task.
     * @return the id of the task
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the task.
     * @param id the id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the Project where the task belongs to.
     * @return the Project where the task belongs to
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the project where the task belongs to.
     * @param project the project to be set.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Returns the name of the task.
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the task.
     * @param name the name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the status of the task.
     * @return the status of the task.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     * @param status the status to be set.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns the description of the task.
     * @return the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     * @param description the description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the priority of the task.
     * @return the priority of the task.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     * @param priority the priority to be set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns the Materials of the task.
     * @return the Materials of the task.
     */
    public Set<Material> getMaterials() {
        return materials;
    }

    /**
     * Sets the Materials of the task.
     * @param materials the materials to be set
     */
    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

    /**
     * Returns the hourly salary of the task.
     * @return the hourly salary of the task.
     */
    public int getHourlySalary() {
        return hourlySalary;
    }

    /**
     * Sets the hourly salary of the task.
     * @param hourlySalary the hourly salary to be set
     */
    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    /**
     * Returns the time entries of the task.
     * @return the time entries of the task.
     */
    public Set<Time> getTimes() {
        return times;
    }

    /**
     * Sets the time entries of the task.
     * @param times the time to be set
     */
    public void setTimes(Set<Time> times) {
        this.times = times;
    }

    /**
     * Returns the total worked hours computed by the time entries.
     * @return the total worked hours
     */
    public Double getWorkedHours() {
        List<Long> hours_list = new ArrayList<Long>();
        times.stream().forEach(time -> {
            Long hours = time.getStartDate().until(time.getEndDate(), ChronoUnit.MINUTES);
            hours_list.add(hours);
        });
        Double summedHours = hours_list.stream().mapToDouble(minute -> {
            return minute / 60;
        }).sum();
        return summedHours;
    }

    /**
     * Returns the summed price of the {@code Materials}.
     * @return the summed price of the {@code Materials}.
     */
    public Double getSummedMaterialPrice() {
        Double summedPrice = materials.stream()
                .mapToDouble(material -> {
                    return material.getActualPrice();
                }).sum();
        return summedPrice;
    }

    /**
     * Returns the total payment of the {@code Task}.
     * @return the total payment of the {@code Task}.
     */
    public Double getTotalPayment() {
        return getSummedMaterialPrice() + getWorkedHours() * getHourlySalary();
    }

    /**
     * Returns the total profit of the {@code Task}.
     * @return the total profit of the {@code Task}
     */
    public Double getTotalProfit() {
        Double totalProfitFromMaterials = materials.stream()
                .mapToDouble(material -> {
                    return material.getListPrice() * material.getPercent();
                }).sum();
        return totalProfitFromMaterials + getHourlySalary() * getWorkedHours();
    }
}
