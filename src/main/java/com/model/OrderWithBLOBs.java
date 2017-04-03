package com.model;

public class OrderWithBLOBs extends ProOrder {
    private String items;

    private String justification;

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification == null ? null : justification.trim();
    }
}