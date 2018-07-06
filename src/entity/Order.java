package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Order {

    private int customerId;
    private int pharmacistId;
    private Map<Drug, Integer> drugs;
    private LocalDate orderDate;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order() {
        drugs = new HashMap<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(int pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public Map<Drug, Integer> getDrugs() {
        return drugs;
    }

    public void setDrugs(Map<Drug, Integer> drugs) {
        this.drugs = drugs;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void addDrug(Drug drug, Integer amount) {
        if (amount > 0) {
            drugs.put(drug, amount);
        }
    }
}
