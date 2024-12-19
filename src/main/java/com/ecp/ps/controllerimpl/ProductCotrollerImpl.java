package com.ecp.ps.controllerimpl;

import com.ecp.ps.controller.IProductController;
import com.ecp.ps.model.Products;
import com.ecp.ps.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/products")
@Slf4j
public class ProductCotrollerImpl implements IProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductCotrollerImpl.class);

    @Autowired
    private IProductService iProductService;

    @Override
    public ResponseEntity<Long> count() {
        logger.info("Product Cotroller class count method");
        return iProductService.count();
    }

    @Override
    public ResponseEntity<List<Products>> search() {
        logger.info("Product Cotroller class search method");
        return iProductService.search();
    }

    @Override
    public ResponseEntity<Products> create(Products products) {
        logger.info("Product Cotroller class create method product: {}", products);
        return iProductService.create(products);
    }

    @Override
    public ResponseEntity<Products> update() {
        logger.info("Product Cotroller class update method");
        return iProductService.update();
    }

    @Override
    public ResponseEntity<Products> delete(Long id) {
        logger.info("Product Cotroller class delete method id : {}",id);
        return iProductService.delete(id);
    }
}
