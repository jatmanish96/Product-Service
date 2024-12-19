package com.ecp.ps.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long price;

    @Column
    private  Long stock;

    @Column(name = "sku")
    private String stock_keep_unit;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<ProductImages> productImages;

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<ProductRatings> productRatings;

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private  List<ProductVariants> productVariants;

    @Column
    private String brand;

    @Column(name = "created_at")
    private Date creation_time;

    @Column(name = "updated_at")
    private Date updation_time;

}
