package com.shop.pc_club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ModelUser user;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    private Integer quantity;

    private Double totalCost;


}
