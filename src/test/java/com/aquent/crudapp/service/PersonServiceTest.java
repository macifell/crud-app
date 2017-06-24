package com.aquent.crudapp.service;

import static com.aquent.crudapp.testutil.ValidationTestTools.makeValidator;
import static org.junit.Assert.assertThat;

import java.util.*;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.*;

import com.aquent.crudapp.domain.Person;

public class PersonServiceTest {

    PersonService personService;

    @Before
    public void setUp() {
        personService = new DefaultPersonService();
        personService.setValidator(makeValidator());
    }

    @Test
    public void validatePerson_ViolationsAreSorted() {
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
