package com.aquent.crudapp.domain;

import javax.validation.constraints.*;

public class Client {

    public static final String COMPANY_NAME_NULL_MESSAGE = "Company name may not be null";
    public static final String COMPANY_NAME_LENGTH_MESSAGE = "Company name is required with maximum length of 50";
    public static final String WEBSITE_URI_NULL_MESSAGE = "Website URI may not be null";
    public static final String WEBSITE_URI_LENGTH_MESSAGE = "Website URI is required with maximum length of 50";
    public static final String PHONE_NUMBER_NULL_MESSAGE = "Phone number may not be null";
    public static final String PHONE_NUMBER_INVALID_MESSAGE = "Phone number is not in the correct format";
    public static final String MAILING_ADDRESS_NULL_MESSAGE = "Mailing address may not be null";
    public static final String MAILING_ADDRESS_LENGTH_MESSAGE = "Mailing address is required with maximum length of 50";

    private Integer clientId;

    @NotNull(message = COMPANY_NAME_NULL_MESSAGE)
    @Size(min = 1, max = 50, message = COMPANY_NAME_LENGTH_MESSAGE)
    private String companyName;

    @NotNull(message = WEBSITE_URI_NULL_MESSAGE)
    @Size(min = 1, max = 50, message = WEBSITE_URI_LENGTH_MESSAGE)
    private String websiteUri;

    @NotNull(message = PHONE_NUMBER_NULL_MESSAGE)
    @Pattern(regexp = "((\\(\\d{3}\\) ?)|(\\d{3}-))?\\d{3}-\\d{4}", message = PHONE_NUMBER_INVALID_MESSAGE)
    private String phoneNumber;

    @NotNull(message = MAILING_ADDRESS_NULL_MESSAGE)
    @Size(min = 1, max = 50, message = MAILING_ADDRESS_LENGTH_MESSAGE)
    private String mailingAddress;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

}
