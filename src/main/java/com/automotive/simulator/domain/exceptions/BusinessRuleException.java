package com.automotive.simulator.domain.exceptions;

/**
 * Exceção lançada quando uma regra de negócio é violada.
 */
public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
