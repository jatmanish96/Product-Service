package com.ecp.ps.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Categories parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Categories> subcategories;

    @OneToMany(mappedBy = "category")
    private List<Products> products;

    @Column
    private String description;

}
