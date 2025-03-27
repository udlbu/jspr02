package com.example.jspr.examples;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SpelExpressions {

    @Value("#{T(java.lang.Math).PI}")
    private double piValue;

    @Value("#{ 'Hello ' + 'World' }")
    private String greeting;

    @Value("#{ 'Hello'.substring(0, 3) }")
    private String substring;

    @Value("#{ 5 + 3 }")
    private int sum;

    @Value("#{ 10 - 2 }")
    private int difference;

    @Value("#{ 'SpringBoot123'.matches('[A-Za-z]+\\d+') }")
    private boolean matchesPattern;

    @Value("#{ 'abc123'.replaceAll('[^a-zA-Z]', '') }")
    private String cleanedString;

    @Value("#{systemProperties['user.name']}")
    private String systemUserName;

}
