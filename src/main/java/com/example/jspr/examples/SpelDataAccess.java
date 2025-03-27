package com.example.jspr.examples;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Getter
public class SpelDataAccess {

    // Accessing a property
    @Value("#{ 'Hello World'.length() }")
    private int stringLength;

    // Accessing array elements
    @Value("#{ T(java.util.Arrays).asList('Apple', 'Banana', 'Cherry')[1] }")
    private String fruit;

    // Accessing elements from a list
    @Value("#{ T(java.util.Arrays).asList('Apple', 'Banana', 'Cherry')[2] }")
    private String secondFruit;

    // Accessing values from a map
    @Value("#{ {'key1':'value1', 'key2':'value2'}.get('key1') }")
    private String mapValue;

    // Inline list
    @Value("#{ T(java.util.Arrays).asList(1, 2, 3, 4, 5) }")
    private List<Integer> numberList;

    // Inline map
    @Value("#{ {'name':'John', 'age':30, 'city':'New York'} }")
    private Map<String, Object> userInfo;

    // Array construction
    @Value("#{ new int[]{1, 2, 3, 4, 5} }")
    private int[] numberArray;

    // Accessing properties of a record
    @Value("#{ new com.example.jspr.examples.model.User('Alice', 28, 'London').name() }")
    private String userName;

    // Accessing a collection property (e.g., list of records)
    @Value("#{ T(java.util.List).of(new com.example.jspr.examples.model.User('Bob', 35, 'Paris'), new com.example.jspr.examples.model.User('Charlie', 40, 'Berlin')).get(0).name() }")
    private String firstUserName;

    // Accessing a map with object properties
    @Value("#{ {'user1': new com.example.jspr.examples.model.User('David', 25, 'Madrid')}.get('user1').city() }")
    private String userCity;
}
