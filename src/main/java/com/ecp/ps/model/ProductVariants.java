package com.ecp.ps.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table
@Data
public class ProductVariants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String variant_name;

    @Column
    private Long price;

    @Column
    private Long stock;

    @Column(name = "sku")
    private String stock_keep_unit;

    @Column(name = "created_at")
    private Date creation_time;

    @Column(name = "updated_at")
    private Date updation_time;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Products products;

}
