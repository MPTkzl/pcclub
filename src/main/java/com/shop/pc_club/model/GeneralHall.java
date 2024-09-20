package com.shop.pc_club.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "GeneralHall")
public class GeneralHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    private String specifications;

    private Double pricePerHour;

    @OneToMany(mappedBy = "generalHall", cascade = CascadeType.ALL)
    private Set<Bron> bronSet;


    public GeneralHall() {}


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Set<Bron> getBronSet() {
        return bronSet;
    }

    public void setBronSet(Set<Bron> bronSet) {
        this.bronSet = bronSet;
    }
}
