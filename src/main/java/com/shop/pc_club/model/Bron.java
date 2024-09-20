package com.shop.pc_club.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bron")
public class Bron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ModelUser user;

    @ManyToOne
    @JoinColumn(name = "computer_id", nullable = false)
    private GeneralHall generalHall;

    private LocalDateTime bronStart;

    private LocalDateTime bronEnd;

    private Double totalCost;

    public Bron() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelUser getUser() {
        return user;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }

    public GeneralHall getGeneralHall() {
        return generalHall;
    }

    public void setGeneralHall(GeneralHall generalHall) {
        this.generalHall = generalHall;
    }

    public LocalDateTime getBronStart() {
        return bronStart;
    }

    public void setBronStart(LocalDateTime bronStart) {
        this.bronStart = bronStart;
    }

    public LocalDateTime getBronEnd() {
        return bronEnd;
    }

    public void setBronEnd(LocalDateTime bronEnd) {
        this.bronEnd = bronEnd;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
