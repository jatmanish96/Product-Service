package com.ecp.ps.controller;

import com.ecp.ps.model.Products;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



public interface IProductController {




      @GetMapping("/count")
      public ResponseEntity<Long> count();

      @GetMapping("/search")
      public ResponseEntity<?>search(@RequestParam(name = "query") String query,@RequestParam(name = "llimit") Long llimit,@RequestParam(name = "ulimit")Long ulimit);

      @PostMapping("/create")
      public ResponseEntity<Products> create(@RequestBody Products products);

      @PatchMapping("/update")
      public ResponseEntity<Products>update();

      @DeleteMapping("/delete")
      public ResponseEntity<Products>delete(@RequestParam(name = "product_id",required = true) Long id);







}
