package com.ecp.ps.dao;

import com.ecp.ps.model.Products;

import java.util.List;

public interface IProductDao {
         List<Products> search(String query,Long llimit,Long ulimit);
}
