package com.aquent.crudapp.service;

import static org.junit.Assert.assertThat;

import java.util.*;

import javax.validation.*;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.*;

import com.aquent.crudapp.domain.*;

public class PersonServiceTest {

    PersonService personService;

    // TODO - move to a common area
    private Validator makeValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Before
    public void setUp() {
        personService = new DefaultPersonService();
    }

    @Test
    public void validatePerson_ViolationsAreSorted() {
        personService.setValidator(makeValidator());
        List<String> violationMessages = personService.validatePerson(new Person());
        List<String> expectedOrder = new ArrayList<>();

        expectedOrder.add(Person.CITY_NULL_MESSAGE);
        expectedOrder.add(Person.EMAIL_ADDRESS_NULL_MESSAGE);
        expectedOrder.add(Person.FIRST_NAME_NULL_MESSAGE);
        expectedOrder.add(Person.LAST_NAME_NULL_MESSAGE);
        expectedOrder.add(Person.STATE_NULL_MESSAGE);
        expectedOrder.add(Person.STREET_ADDRESS_NULL_MESSAGE);
        expectedOrder.add(Person.ZIP_CODE_NULL_MESSAGE);

        assertThat(violationMessages, IsIterableContainingInOrder.contains(expectedOrder.toArray()));
    }

}
