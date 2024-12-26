package com.ecp.ps.daoimpl;

import com.ecp.ps.dao.IProductDao;
import com.ecp.ps.model.Products;
import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


import static cz.jirutka.rsql.parser.ast.RSQLOperators.*;

@Repository
public class ProductDaoImpl implements IProductDao {

    private static Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    private RSQLVisitor<CriteriaQuery<Products>, EntityManager> visitor;

    public  ProductDaoImpl(){
      this.visitor= new JpaCriteriaQueryVisitor<>(Products.class);
    }

    @Override
    public List<Products> search(String query, Long llimit, Long ulimit) {
        logger.info("ProductDao class search method search: {}",query);

        RSQLParser parser = new RSQLParser();
        RSQLVisitor<CriteriaQuery<Products>,EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
        Node node = parser.parse(query);
        CriteriaQuery<Products> querystr = node.accept(visitor);




//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Products> cq = cb.createQuery(Products.class);
//        Root<?>root = cq.from(Products.class);
//        Object parseObject = parseSearchPayload(query);
//        logger.info("ProductDao class search method parseObject: {}",parseObject);
//        Predicate predicate = buildPredicate(cb, root,parseObject);
//        cq.where(predicate);
        return em.createQuery(querystr).getResultList();
    }

    private Object parseSearchPayload(String filter) {
        // Assuming RSQL parsing logic here
        RSQLParser parser = new RSQLParser();
        return parser.parse(filter);
    }




}
