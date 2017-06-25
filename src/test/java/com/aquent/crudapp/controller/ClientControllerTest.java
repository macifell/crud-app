package com.aquent.crudapp.controller;

import static com.aquent.crudapp.data.dao.ClientDaoTestFactory.*;
import static com.aquent.crudapp.data.dao.PersonDaoTestFactory.makeListPeopleAndClientStub;
import static com.aquent.crudapp.testutil.ValidationTestTools.makeValidator;
import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.springframework.web.servlet.ModelAndView;

import com.aquent.crudapp.controller.ClientController.ClientForm;
import com.aquent.crudapp.data.dao.*;
import com.aquent.crudapp.domain.*;
import com.aquent.crudapp.service.*;

public class ClientControllerTest {

    ClientController clientController;
    ClientService clientService;
    PersonService personService;

    private void assertViewName(String viewName, ModelAndView modelAndView) {
        assertEquals(viewName, modelAndView.getViewName());
    }

    private void assertModelContainsKey(String key, ModelAndView modelAndView) {
        assertTrue(modelAndView.getModel().containsKey(key));
    }

    private void assertModelIsEmpty(ModelAndView modelAndView) {
        assertTrue(modelAndView.getModel().isEmpty());
    }

    private void assertClientIdsMatch(Client client, Person person) {
        assertEquals(client.getClientId().toString(), person.getClientId());
    }

    private PersonDaoSpy createClientWithSpy(Person person, Client client) {
        PersonDaoSpy personDaoSpy = attachPersonDaoSpy(asList(person));
        ClientForm clientForm = makeClientForm(client, asList(person.getPersonId()));

        clientService.setClientDao(makeCreateClientStub(1));
        clientController.create(clientForm);
        return personDaoSpy;
    }

    private PersonDaoSpy editClientWithSpy(List<Integer> selectedPersonIds, List<Person> allPeople, Client client) {
        PersonDaoSpy personDaoSpy = attachPersonDaoSpy(allPeople);
        ClientForm clientForm = makeClientForm(client, selectedPersonIds);

        clientService.setClientDao(new ClientDaoDummy());
        clientController.edit(clientForm);

        return personDaoSpy;
    }

    private PersonDaoSpy deleteClientWithSpy(List<Person> associatedPersonIds, List<Person> allPeople, Client client) {
        PersonDaoSpy personDaoSpy = attachPersonDaoSpy(allPeople);
        personDaoSpy.setPeopleWithClient(associatedPersonIds);

        if (!associatedPersonIds.isEmpty())
            personDaoSpy.setReadPerson(associatedPersonIds.get(0));

        clientService.setClientDao(new ClientDaoDummy());
        clientController.delete(ClientController.COMMAND_DELETE, client.getClientId());

        return personDaoSpy;
    }

    private PersonDaoSpy attachPersonDaoSpy(List<Person> allPeople) {
        PersonDaoSpy personDaoSpy = new PersonDaoSpy(allPeople);
        personService.setPersonDao(personDaoSpy);

        return personDaoSpy;
    }

    private ClientForm makeClientForm(Client client, List<Integer> selectedPersonIds) {
        ClientForm clientForm = new ClientForm();
        clientForm.setClient(client);
        clientForm.setSelectedPersonIds(selectedPersonIds);

        return clientForm;
    }

    @Before
    public void setUp() {
        clientService = new DefaultClientService();
        clientService.setValidator(makeValidator());
        personService = new DefaultPersonService();
        personService.setValidator(makeValidator());
        personService.setPersonDao(makeListPeopleAndClientStub(new ArrayList<>(), new ArrayList<>()));
        clientController = new ClientController();
        clientController.setClientService(clientService);
        clientController.setPersonService(personService);
    }

    @Test
    public void list_SetsCorrectModelAndView() {
        clientService.setClientDao(makeListAllClientsStub(new ArrayList<>()));
        ModelAndView modelAndView = clientController.list();

        assertViewName("client/list", modelAndView);
        assertModelContainsKey("clients", modelAndView);
    }

    @Test
    public void create_GET_SetsCorrectModelAndView() {
        clientService.setClientDao(new ClientDaoDummy());
        ModelAndView modelAndView = clientController.create();

        assertViewName("client/create", modelAndView);
        assertModelContainsKey("clientForm", modelAndView);
        assertModelContainsKey("errors", modelAndView);
    }

    @Test
    public void createClient_POST_SetsCorrectModelAndView_ValidationFails() {
        clientService.setClientDao(makeCreateClientStub(1));
        ClientForm clientForm = makeClientForm(new Client(), asList());
        ModelAndView modelAndView = clientController.create(clientForm);

        assertViewName("client/create", modelAndView);
        assertModelContainsKey("clientForm", modelAndView);
        assertModelContainsKey("errors", modelAndView);
    }

    @Test
    public void createClient_POST_SetsCorrectModelAndView_ValidationSucceeds() {
        clientService.setClientDao(makeCreateClientStub(1));
        ClientForm clientForm = makeClientForm(new ClientStub(), asList());
        ModelAndView modelAndView = clientController.create(clientForm);

        assertViewName("redirect:/client/list", modelAndView);
        assertModelIsEmpty(modelAndView);
    }

    @Test
    public void createClient_POST_UpdatesPeople() {
        Person person = new PersonStub();
        Client client = new ClientStub();
        PersonDaoSpy personDaoSpy = createClientWithSpy(person, client);

        assertClientIdsMatch(client, person);
        assertEquals(1, personDaoSpy.getUpdateCallCount());
        assertThat(personDaoSpy.getUpdatedPeople(), containsInAnyOrder(person));
    }

    @Test
    public void edit_GET_SetsCorrectModelAndView() {
        Client client = new ClientStub();
        clientService.setClientDao(makeReadClientStub(client));
        personService.setPersonDao(makeListPeopleAndClientStub(new ArrayList<>(), new ArrayList<>()));
        ModelAndView modelAndView = clientController.edit(client.getClientId());

        assertViewName("client/edit", modelAndView);
        assertModelContainsKey("clientForm", modelAndView);
        assertModelContainsKey("errors", modelAndView);
    }

    @Test
    public void editClient_POST_SetsCorrectModelAndView_ValidationFails() {
        ClientForm clientForm = new ClientForm();
        clientForm.setClient(new Client());
        clientService.setClientDao(new ClientDaoDummy());
        ModelAndView modelAndView = clientController.edit(clientForm);

        assertViewName("client/edit", modelAndView);
        assertModelContainsKey("clientForm", modelAndView);
        assertModelContainsKey("errors", modelAndView);
    }

    @Test
    public void editClient_POST_SetsCorrectModelAndView_ValidationSucceeds() {
        ClientForm clientForm = new ClientForm();
        clientForm.setClient(new ClientStub());
        clientService.setClientDao(new ClientDaoDummy());
        ModelAndView modelAndView = clientController.edit(clientForm);

        assertViewName("redirect:/client/list", modelAndView);
        assertModelIsEmpty(modelAndView);
    }

    @Test
    public void editClient_POST_UpdatesAddedPerson() {
        Person person = new PersonStub();
        Client client = new ClientStub();
        PersonDaoSpy personDaoSpy = editClientWithSpy(asList(person.getPersonId()), asList(person), client);

        assertClientIdsMatch(client, person);
        assertEquals(1, personDaoSpy.getUpdateCallCount());
        assertThat(personDaoSpy.getUpdatedPeople(), containsInAnyOrder(person));
    }

    @Test
    public void editClient_POST_UpdatesRemovedPerson() {
        Person person = new PersonStub();
        Client client = new ClientStub();
        person.setClientId(client.getClientId().toString());
        PersonDaoSpy personDaoSpy = editClientWithSpy(asList(), asList(person), client);

        assertEquals(1, personDaoSpy.getUpdateCallCount());
        assertEquals("", person.getClientId());
        assertThat(personDaoSpy.getUpdatedPeople(), containsInAnyOrder(person));
    }

    @Test
    public void editClient_POST_DoesNotUpdatePreviouslySelectedPerson() {
        Person person = new PersonStub();
        Client client = new ClientStub();
        person.setClientId(client.getClientId().toString());
        PersonDaoSpy personDaoSpy = editClientWithSpy(asList(person.getPersonId()), asList(person), client);

        assertEquals(0, personDaoSpy.getUpdateCallCount());
    }

    @Test
    public void editClient_POST_DoesNotUpdatePreviouslyUnselectedPerson() {
        Person person = new PersonStub();
        Client client = new ClientStub();
        PersonDaoSpy personDaoSpy = editClientWithSpy(asList(), asList(person), client);

        assertEquals(0, personDaoSpy.getUpdateCallCount());
    }

    @Test
    public void delete_GET_SetsCorrectModelAndView() {
        Client client = new ClientStub();
        clientService.setClientDao(makeReadClientStub(client));
        ModelAndView modelAndView = clientController.delete(client.getClientId());

        assertViewName("client/delete", modelAndView);
        assertModelContainsKey("client", modelAndView);
    }

    @Test
    public void deleteClient_POST_SetsCorrectModelAndView() {
        Client client = new ClientStub();
        clientService.setClientDao(new ClientDaoDummy());
        String viewName = clientController.delete(ClientController.COMMAND_DELETE, client.getClientId());

        assertEquals("redirect:/client/list", viewName);
    }

    @Test
    public void deleteClient_POST_RemovesClientIdFromAssociatedPerson() {
        Person person = new PersonStub();
        Client client = new ClientStub();
        person.setClientId(client.getClientId().toString());
        PersonDaoSpy personDaoSpy = deleteClientWithSpy(asList(person), asList(person), client);

        assertEquals(1, personDaoSpy.getUpdateCallCount());
        assertEquals("", person.getClientId());
        assertThat(personDaoSpy.getUpdatedPeople(), containsInAnyOrder(person));
    }

    @Test
    public void deleteClient_POST_DoesNotRemoveClientIdForNonAssociatedPerson() {
        Person person = new PersonStub();
        Client client = new ClientStub();
        person.setClientId(client.getClientId().toString());
        PersonDaoSpy personDaoSpy = deleteClientWithSpy(asList(), asList(person), client);

        assertEquals(0, personDaoSpy.getUpdateCallCount());
    }

}
