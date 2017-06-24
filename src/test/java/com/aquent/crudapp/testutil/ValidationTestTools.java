package com.aquent.crudapp.testutil;

import javax.validation.*;

public class ValidationTestTools {

    public static Validator makeValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
