package app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * This class represents an invoice in the database.
 */
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "payed")
    private boolean payed;
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<InvoiceItem> items;
    @Transient
    private Double summedPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public Double getSummedPrice() {
        return items.stream().mapToDouble(item -> item.getSummedPrice()).sum();
    }

}
