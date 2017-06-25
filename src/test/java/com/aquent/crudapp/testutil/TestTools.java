package com.aquent.crudapp.testutil;

import java.util.Collections;

import javax.validation.*;

public class TestTools {

    public static Validator makeValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static String generateCopies(int numberOfCopies, String toCopy) {
        return String.join("", Collections.nCopies(numberOfCopies, toCopy));
    }

}
