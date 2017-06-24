package com.aquent.crudapp.domain;

public class ClientStub extends Client {

    public ClientStub() {
        this.setClientId(1);
        this.setCompanyName("name");
        this.setWebsiteUri("uri");
        this.setPhoneNumber("123-456-7890");
        this.setMailingAddress("address");
    }

}
