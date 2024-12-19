package com.ecp.ps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Categories category;


    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductImages> productImages;

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductRatings> productRatings;

    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    @JsonManagedReference
    private  List<ProductVariants> productVariants;

    @Column
    private String brand;

    @Column(name = "created_at")
    private Date creation_time;

    @Column(name = "updated_at")
    private Date updation_time;

}
