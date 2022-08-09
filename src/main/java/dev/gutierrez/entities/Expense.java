package dev.gutierrez.entities;

public class Expense {

    private int expense_id;
    private double amount;
    private Status status;
    private Type type;
    private String description;
    private int id_empIssuer;

    public Expense() {
    }

    public Expense(int expense_id, double amount, Status status, Type type, String description, int id_empIssuer) {
        this.expense_id = expense_id;
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.description = description;
        this.id_empIssuer = id_empIssuer;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
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

    public int getId_empIssuer() {
        return id_empIssuer;
    }

    public void setId_empIssuer(int id_empIssuer) {
        this.id_empIssuer = id_empIssuer;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expense_id=" + expense_id +
                ", amount=" + amount +
                ", status=" + status +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", id_empIssuer=" + id_empIssuer +
                '}';
    }
}
