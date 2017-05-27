package app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "name")
    private String name;
    @Column(name = "store_name")
    private String storeName;
    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonIgnore
    private Task task;
    @Column(name="list_price")
    private double listPrice;
    @Column(name = "percent")
    private double percent;

    @Transient
    private double price;
    @Transient
    private double actualPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getPrice() {
        return listPrice * (1 + percent);
    }

    public double getActualPrice() {
        return quantity*getPrice();
    }
}
