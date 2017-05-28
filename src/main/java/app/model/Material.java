package app.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represents a material in the database.
 */
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

    /**
     * Returns the id of the material.
     * @return the id of the material
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the material.
     * @param id the if to bse set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the quantity of the material.
     * @return the queantity of the material
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the material.
     * @param quantity the quantity to be set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the name of the material.
     * @return the name of the material
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the material.
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the store where the material was bought.
     * @return the name of the store
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Sets the name of the store where the material was bought.
     * @param storeName the name of the store to be set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * Returns the {@link app.model.Task} where the material belongs to.
     * @return the {@link app.model.Task} where the material belongs to
     */
    public Task getTask() {
        return task;
    }

    /**
     * Sets the {@link app.model.Task} where the material belongs to.
     * @param task the {@link app.model.Task} to be set
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Returns the list price of the material.
     * @return the list price of the material
     */
    public double getListPrice() {
        return listPrice;
    }

    /**
     * Sets the list price of the material.
     * @param listPrice the list price to be set
     */
    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    /**
     * Returns The percentage of the work price.
     * @return the percentage of the work price
     */
    public double getPercent() {
        return percent;
    }

    /**
     * Sets the percentage of the work price.
     * @param percent the percentage to be set
     */
    public void setPercent(double percent) {
        this.percent = percent;
    }

    /**
     * Returns the price computed by the list price and the percentage.
     * @return the price
     */
    public double getPrice() {
        return listPrice * (1 + percent);
    }

    /**
     * Returns the actual price computed by the quantity and the price.
     * @return the actual price
     */
    public double getActualPrice() {
        return quantity*getPrice();
    }
}
