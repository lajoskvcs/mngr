package app.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @Column(name = "hourly_salary")
    private int hourlySalary = 2000;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "task", cascade = CascadeType.MERGE)
    private Collection<Time> times;

    @Transient
    private Double workedHours;


    @OneToMany(mappedBy = "task", cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Material> materials;

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Collection<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Collection<Material> materials) {
        this.materials = materials;
    }

    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public Collection<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    public Double getWorkedHours() {
        List<Long> hours_list = new ArrayList<Long>();
        times.stream().forEach(time -> {
            Long hours = time.getStartDate().until(time.getEndDate(), ChronoUnit.MINUTES);
            hours_list.add(hours);
        });
        Double summedHours = hours_list.stream().mapToDouble(minute -> {
            return minute / 60;
        }).sum();
        System.out.println(summedHours);
        return summedHours;
    }
}
