package com.ecp.ps.serviceimpl;

import com.ecp.ps.controllerimpl.ProductCotrollerImpl;
import com.ecp.ps.model.Products;
import com.ecp.ps.repository.ProductRepository;
import com.ecp.ps.service.IProductService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<Long> count() {
        logger.info("ProductService class count method");
        Long totalCount = null;
        try {
            totalCount = productRepository.count();
        } catch (Exception exception) {
         throw  new NullPointerException(exception.getMessage());
        }
        return ResponseEntity.ok(totalCount);
    }

    @Override
    public ResponseEntity<List<Products>> search() {
        logger.info("ProductService class count method");
        List<Products> productsList=null;
        try{
            productsList = productRepository.findAll();
        }
        catch (Exception e){
            throw  new NullPointerException(e.getMessage());
        }
        return ResponseEntity.ok(productsList);
    }

    @Override
    public ResponseEntity<Products> create(Products products) {
        return null ;
    }

    @Override
    public ResponseEntity<Products> update() {
        return null;
    }

    @Override
    public ResponseEntity<Products> delete(Long id) {
        return null;
    }



}
