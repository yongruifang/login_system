package org.example.util;

public enum Status {
    SUCCESS("success"),
    FAILURE("failed");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
