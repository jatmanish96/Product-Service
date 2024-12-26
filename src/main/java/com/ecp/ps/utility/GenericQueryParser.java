package com.ecp.ps.utility;


import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;


public interface GenericQueryParser<T> {


    /**
     * Generates a query based on the RSQL filter string using Criteria API.
     * @param em EntityManager to create the query.
     * @param filter The RSQL query string to filter data.
     * @param entityClass The entity class to query against.
     * @return List of results matching the query.
     */
    List<T> generateQuery(EntityManager em, String filter, Class<T> entityClass);

    /**
     * Parses the RSQL filter into an object that can be used to build the predicate.
     * @param filter The RSQL filter string.
     * @return A parsed filter object.
     */
    Object parseSearchPayload(String filter);

    /**
     * Builds the Predicate from parsed node.
     * @param cb CriteriaBuilder instance.
     * @param root Root of the entity in the query.
     * @param node Parsed node (comparison or logical).
     * @return Predicate based on the parsed node.
     */
    Predicate buildPredicate(CriteriaBuilder cb, Root<?> root, Object node);
}

