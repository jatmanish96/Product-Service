package com.ecp.ps.utility;

public enum RsqlParserOperations {
    EQUAL("=="),
    NOT_EQUAL("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<="),
    INCLUDE("in"),
    EXCLUDE("not in"),
    AND("and"),
    OR("or"),
    NOT("not");

    private final String operator;

    RsqlParserOperations(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static RsqlParserOperations fromString(String operator) {
        for (RsqlParserOperations op : RsqlParserOperations.values()) {
            if (op.getOperator().equals(operator)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + operator);
    }
}
