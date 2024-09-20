package com.shop.pc_club.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Double price;

    private Integer quantity;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<Sales> salesSet;


    public Food() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<Sales> getSalesSet() {
        return salesSet;
    }

    public void setSalesSet(Set<Sales> salesSet) {
        this.salesSet = salesSet;
    }
}
