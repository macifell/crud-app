package com.aquent.crudapp.domain;

import javax.validation.constraints.*;

/**
 * The person entity corresponding to the "person" table in the database.
 */
public class Person {

    public static final String FIRST_NAME_NULL_MESSAGE = "First name may not be null";
    public static final String LAST_NAME_NULL_MESSAGE = "Last name may not be null";
    public static final String EMAIL_ADDRESS_NULL_MESSAGE = "Email address may not be null";
    public static final String STREET_ADDRESS_NULL_MESSAGE = "Street address may not be null";
    public static final String CITY_NULL_MESSAGE = "City may not be null";
    public static final String STATE_NULL_MESSAGE = "Sate may not be null";
    public static final String ZIP_CODE_NULL_MESSAGE = "Zip code may not be null";
    public static final String CLIENT_ID_NULL_MESSAGE = "Client id may not be null";
    public static final String CLIENT_ID_LENGTH_MESSAGE = "Client id may not be more than 50 characters";

    private Integer personId;

    @NotNull(message = FIRST_NAME_NULL_MESSAGE)
    @Size(min = 1, max = 50, message = "First name is required with maximum length of 50")
    private String firstName;

    @NotNull(message = LAST_NAME_NULL_MESSAGE)
    @Size(min = 1, max = 50, message = "Last name is required with maximum length of 50")
    private String lastName;

    @NotNull(message = EMAIL_ADDRESS_NULL_MESSAGE)
    @Size(min = 1, max = 50, message = "Email address is required with maximum length of 50")
    private String emailAddress;

    @NotNull(message = STREET_ADDRESS_NULL_MESSAGE)
    @Size(min = 1, max = 50, message = "Street address is required with maximum length of 50")
    private String streetAddress;

    @NotNull(message = CITY_NULL_MESSAGE)
    @Size(min = 1, max = 50, message = "City is required with maximum length of 50")
    private String city;

    @NotNull(message = STATE_NULL_MESSAGE)
    @Size(min = 2, max = 2, message = "State is required with length 2")
    private String state;

    @NotNull(message = ZIP_CODE_NULL_MESSAGE)
    @Size(min = 5, max = 5, message = "Zip code is required with length 5")
    private String zipCode;

    @NotNull(message = CLIENT_ID_NULL_MESSAGE)
    @Size(min = 0, max = 50, message = CLIENT_ID_LENGTH_MESSAGE)
    private String clientId;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
