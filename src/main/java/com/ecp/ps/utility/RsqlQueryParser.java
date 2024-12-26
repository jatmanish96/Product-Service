package com.ecp.ps.utility;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.Date;
import java.util.List;



public class RsqlQueryParser<T> implements GenericQueryParser<T> {



    @Override
    public List<T> generateQuery(EntityManager em, String filter, Class<T> entityClass) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);

        Object parsedFilter = parseSearchPayload(filter);
        // Generate the Predicate from the parsed query
        Predicate predicate = buildPredicate(cb, root, parsedFilter);
        query.where(predicate);

        return em.createQuery(query).getResultList();
    }

    @Override
    public Object parseSearchPayload(String filter) {
        // RSQL Parsing is done in generateQuery() method, but this is the place to implement it
        RSQLParser parser = new RSQLParser();
        return parser.parse(filter);
    }

    @Override
    public Predicate buildPredicate(CriteriaBuilder cb, Root<?> root, Object node) {
        if (node instanceof ComparisonNode) {
            return buildComparisonPredicate(cb, root, (ComparisonNode) node);
        } else if (node instanceof LogicalNode) {
            return buildLogicalPredicate(cb, root, (LogicalNode) node);
        }
        throw new IllegalArgumentException("Invalid node type: " + node.getClass());
    }

    private Predicate buildComparisonPredicate(CriteriaBuilder cb, Root<?> root, ComparisonNode comparisonNode) {
        Path<Object> path = root.get(comparisonNode.getSelector());
        RsqlParserOperations operations = RsqlParserOperations.fromString(comparisonNode.getOperator().toString());
        List<String> arguments = comparisonNode.getArguments(); // Get arguments as List<String>

        // Determine the field type dynamically
        Class<?> fieldType = path.getJavaType();

        switch (operations) {
            case EQUAL:
                return cb.equal(path, convertArgument(arguments.get(0), fieldType));
            case NOT_EQUAL:
                return cb.notEqual(path, convertArgument(arguments.get(0), fieldType));
            case INCLUDE:
                return path.in(convertArgumentsToObjectArray(arguments, fieldType));
            case EXCLUDE:
                return cb.not(path.in(convertArgumentsToObjectArray(arguments, fieldType)));
            default:
                throw new UnsupportedOperationException("Operator not supported: " + comparisonNode.getOperator());
        }
    }

    // Convert each argument to the correct type based on the field type
    private static Object convertArgument(String argument, Class<?> fieldType) {
        if (fieldType.equals(String.class)) {
            return argument;  // If field type is String, return the argument directly.
        } else if (fieldType.equals(Integer.class)) {
            return Integer.parseInt(argument);  // If it's an Integer field, parse it as Integer.
        } else if (fieldType.equals(Double.class)) {
            return Double.parseDouble(argument);  // If it's a Double field, parse it as Double.
        } else if (fieldType.equals(Boolean.class)) {
            return Boolean.parseBoolean(argument);  // If it's a Boolean field, parse it as Boolean.
        } else if (fieldType.equals(Long.class)) {
            return Long.parseLong(argument);  // If it's a Long field, parse it as Long.
        } else if (fieldType.equals(Date.class)) {
            // Handle Date parsing, assuming argument is in "yyyy-MM-dd" format
            try {
                return java.sql.Date.valueOf(argument);  // Convert string to Date.
            } catch (IllegalArgumentException e) {
                throw new UnsupportedOperationException("Invalid date format: " + argument);
            }
        }
        throw new UnsupportedOperationException("Unsupported field type: " + fieldType);
    }

    // Converts a list of arguments to Object[] based on the field type
    private static Object[] convertArgumentsToObjectArray(List<String> arguments, Class<?> fieldType) {
        if (arguments != null) {
            return arguments.stream()
                    .map(arg -> convertArgument(arg, fieldType))  // Convert each argument based on the field type
                    .toArray();
        }
        return new Object[0];  // Return an empty array if no arguments
    }

    private Predicate buildLogicalPredicate(CriteriaBuilder cb, Root<?> root, LogicalNode logicalNode) {
        Predicate lhsPredicate = buildPredicate(cb, root, logicalNode);
        Predicate rhsPredicate = buildPredicate(cb, root, logicalNode);

        switch (logicalNode.getOperator()) {
            case AND:
                return cb.and(lhsPredicate, rhsPredicate);
            case OR:
                return cb.or(lhsPredicate, rhsPredicate);
            default:
                throw new UnsupportedOperationException("Logical operator not supported: " + logicalNode.getOperator());
        }
    }


}
