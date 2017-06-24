package com.aquent.crudapp.domain;

import javax.validation.constraints.*;

public class Client {
    
    private Integer clientId;
    
    @NotNull
    @Size(min = 1, max = 50, message = "Company name is required with maximum length of 50")
    private String companyName;

}
