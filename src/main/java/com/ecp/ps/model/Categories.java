package com.ecp.ps.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Categories parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @JsonManagedReference
    private List<Categories> subcategories;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Products> products;

    @Column
    private String description;

}
