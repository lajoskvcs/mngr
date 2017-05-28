package app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * This class represents an Invoice in the database.
 */
@Entity
@Table(name = "invoices")
public class Invoice {
    /**
     * The id of the Invoice.
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    /**
     * The dueDate of the Invoice.
     */
    @Column(name = "due_date")
    private LocalDate dueDate;
    /**
     * The payment status of the Invoice.
     */
    @Column(name = "payed")
    private boolean payed;
    /**
     * The List of {@link app.model.InvoiceItem Items} of the Invoice.
     */
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<InvoiceItem> items;
    /**
     * The summed price of the {@link app.model.InvoiceItem Items} of the Invoice.
     */
    @Transient
    private Double summedPrice;

    /**
     * Returns the id of the Invoice.
     * @return the ud of the Invoice
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the Invoice.
     * @param id the id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retruns the dueDate of the Invoice.
     * @return the dueDate of the Invoice
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the dueDate of the Invoice.
     * @param dueDate the {@code LocalDate} to be set
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the payment status of the Invoice.
     * @return the payment status of the Invoice
     */
    public boolean isPayed() {
        return payed;
    }

    /**
     * Sets the paymnet status of the Invoice.
     * @param payed the status to be set
     */
    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    /**
     * Returns the {@link app.model.InvoiceItem Items} of the Invoice.
     * @return the {@link app.model.InvoiceItem Items} of the Invoice
     */
    public List<InvoiceItem> getItems() {
        return items;
    }

    /**
     * Sets the {@link app.model.InvoiceItem Items} of the Invoice.
     * @param items The the {@link app.model.InvoiceItem Items} to be set.
     */
    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    /**
     * Returns the Summed Price of the the {@link app.model.InvoiceItem Items}.
     * @return the Summed Price of the the {@link app.model.InvoiceItem Items}
     */
    public Double getSummedPrice() {
        return items.stream().mapToDouble(item -> item.getSummedPrice()).sum();
    }

}
