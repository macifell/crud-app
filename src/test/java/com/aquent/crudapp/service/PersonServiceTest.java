package com.aquent.crudapp.service;

import static com.aquent.crudapp.data.dao.PersonDaoTestFactory.makeListPeopleWithClientStub;
import static com.aquent.crudapp.testutil.ValidationTestTools.makeValidator;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;

import java.util.*;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.*;

import com.aquent.crudapp.domain.*;

public class PersonServiceTest {

    PersonService personService;

    private void assertNoViolations(Person person) {
        assertViolations(person);
    }

    private void assertViolations(Person person, String... expectedViolationMessages) {
        List<String> violationMessages = personService.validatePerson(person);

        assertThat(violationMessages, containsInAnyOrder(expectedViolationMessages));
    }

    @Before
    public void setUp() {
        personService = new DefaultPersonService();
        personService.setValidator(makeValidator());
    }

    @Test
    public void listPeopleWithClient_ReturnsDaoList() {
        List<Person> responseList = new ArrayList<>();
        responseList.add(new Person());
        personService.setPersonDao(makeListPeopleWithClientStub(responseList));

        assertEquals(responseList, personService.listPeopleWithClient(0));
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

    @Test
    public void validatePerson_SucceedsWithNullClientId() {
        Person person = new PersonStub();
        person.setClientId(null);

        assertNoViolations(person);
    }

}
