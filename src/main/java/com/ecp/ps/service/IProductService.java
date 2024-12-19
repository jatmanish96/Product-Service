package com.ecp.ps.service;

import com.ecp.ps.model.Products;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IProductService {


    public ResponseEntity<Long> count();


    public ResponseEntity<List<Products>>search();


    public ResponseEntity<Products> create( Products products);


    public ResponseEntity<Products>update();


    public ResponseEntity<Products>delete(Long id);
}
