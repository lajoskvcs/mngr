package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class represents an invoice item in the database.
 */
@Entity
@Table(name = "invoice_item")
public class InvoiceItem {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    private Invoice invoice;
    @Column(name = "name")
    private String name;
    @Column(name = "worked_price")
    private Double workPrice;
    @Column(name = "material_price")
    private Double materialPrice;
    @Transient
    private Double summedPrice;

    /**
     * This method returns the id of the item.
     * @return The id of the item
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets the id of the item
     * @param id the id to be set as the id of the item.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method returns the invoice where the item belongs to
     * @return The invoice where the item belongs to
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * This method sets the invoice where the item belongs to
     * @param invoice The invoice to be set as the invoice where the item belongs to
     */
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    /**
     * This method returns with the name of the item
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * This sets the name of the method
     * @param name The name to be set as the name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns with the summed price of the task
     * @return the summed price of the task
     */
    public Double getWorkPrice() {
        return workPrice;
    }

    /**
     * This method set the summed price of the task
     * @param workPrice The price to be set as the summed price of the task
     */
    public void setWorkPrice(Double workPrice) {
        this.workPrice = workPrice;
    }

    /**
     * This method returns with the material price of the task
     * @return the material price of the task
     */
    public Double getMaterialPrice() {
        return materialPrice;
    }

    /**
     * This method sets the material price of the task
     * @param materialPrice the material price to be set as the material price of the task
     */
    public void setMaterialPrice(Double materialPrice) {
        this.materialPrice = materialPrice;
    }

    /**
     * Thid method returns the summed price of the invoice item
     * @return the summed price of the invoice item
     */
    public Double getSummedPrice() {
        return getMaterialPrice() + getWorkPrice();
    }
}
