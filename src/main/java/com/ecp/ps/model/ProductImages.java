package com.ecp.ps.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "product_images")
@Data
public class ProductImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private  String image_url;

    @Column
    private String image_type;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Products products;

    @Column(name = "created_at")
    private Date created_time;

    @Column(name = "updated_at")
    private Date updated_time;


}
