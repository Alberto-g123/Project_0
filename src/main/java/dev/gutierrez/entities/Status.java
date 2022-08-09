package dev.gutierrez.entities;

public enum Status {
    APPROVED, DENIED, PENDING;

    public static Status fromString(String status) {
        return valueOf(status.toUpperCase());
    }
}

