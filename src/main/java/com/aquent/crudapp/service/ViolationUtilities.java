package com.aquent.crudapp.service;

import java.util.*;

import javax.validation.ConstraintViolation;

public class ViolationUtilities {

    public static <T> List<String> extractViolationMessages(Set<ConstraintViolation<T>> violations) {
        List<String> violationMessages = new ArrayList<>(violations.size());

        for (ConstraintViolation<T> violation : violations)
            violationMessages.add(violation.getMessage());

        Collections.sort(violationMessages);

        return violationMessages;
    }

}
