package com.model;

public class Item {
    private Integer id;

    private String sku;

    private Double price;

    private Integer catalog;
    
    private String name;

    private String unit;

    private String supplier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCatalog() {
        return catalog;
    }

    public void setCatalog(Integer catalog) {
        this.catalog = catalog;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", sku=" + sku + ", price=" + price + ", catalog=" + catalog + ", name=" + name
				+ ", unit=" + unit + ", supplier=" + supplier + "]";
	}

	
    
}