package com.example.jspr.examples;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SpelOperators {

    // Relational Operator: Greater than
    @Value("#{ 10 > 5 }")
    private boolean isGreaterThan;

    // Relational Operator: Less than
    @Value("#{ 3 < 7 }")
    private boolean isLessThan;

    // Relational Operator: Equal to
    @Value("#{ 'hello' == 'hello' }")
    private boolean isEqual;

    // Relational Operator: Not equal to
    @Value("#{ 10 != 5 }")
    private boolean isNotEqual;

    // Logical Operator: AND
    @Value("#{ 5 > 3 and 7 < 10 }")
    private boolean isAndTrue;

    // Logical Operator: OR
    @Value("#{ 5 > 3 or 7 > 10 }")
    private boolean isOrTrue;

    // Logical Operator: NOT
    @Value("#{ !(5 > 3) }")
    private boolean isNotTrue;

    // Assignment Operator: Assign value to a variable
    @Value("#{ 5 + 10 }")
    private int sum;

    // Assignment with ternary operator
    @Value("#{ 5 > 3 ? 'Greater' : 'Smaller' }")
    private String ternaryResult;

    // Inline list with relational condition
    @Value("#{ T(java.util.Arrays).asList(1, 2, 3).contains(2) }")
    private boolean containsValue;

    // Inline map with assignment
    @Value("#{ {'key1': 'value1', 'key2': 'value2'}.get('key2') }")
    private String mapValue;
}
