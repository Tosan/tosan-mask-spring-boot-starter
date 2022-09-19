package com.tosan.tools.mask.starter.business.enumeration;

/**
 * @author mina khoshnevisan
 * @since 9/18/2022
 */
public enum ComparisonType {
    /**
     * check with equals operator
     */
    EQUALS,
    /**
     * check with equalsIgnoreCase operator
     */
    EQUALS_IGNORE_CASE,
    /**
     * check with contains operator(case-insensitive)
     */
    LIKE,
    /**
     * check with starts with operator(case-insensitive)
     */
    RIGHT_LIKE,
    /**
     * check with ends with operator(case-insensitive)
     */
    LEFT_LIKE
}