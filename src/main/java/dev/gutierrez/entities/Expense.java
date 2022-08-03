package dev.gutierrez.entities;

import java.util.Objects;

public class Expense {

    private int id;
    private double amount;
    private Status status;
    private Type type;
    private String description;

    public Expense(int id, double amount, Status status, Type type, String description) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return id == expense.id && Double.compare(expense.amount, amount) == 0 && status == expense.status && type == expense.type && Objects.equals(description, expense.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, status, type, description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", status=" + status +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
