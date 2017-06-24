package com.aquent.crudapp.service;

import static com.aquent.crudapp.data.dao.ClientDaoTestFactory.*;
import static com.aquent.crudapp.testutil.ValidationTestTools.makeValidator;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import com.aquent.crudapp.data.dao.ClientDaoSpy;
import com.aquent.crudapp.domain.*;

public class ClientServiceTest {

    private ClientService clientService;

    private void assertNoViolations(Client client) {
        assertViolations(client);
    }

    private void assertViolations(Client client, String... expectedViolationMessages) {
        List<String> violationMessages = clientService.validateClient(client);

        assertThat(violationMessages, containsInAnyOrder(expectedViolationMessages));
    }

    @Before
    public void setUp() {
        clientService = new DefaultClientService();
        clientService.setValidator(makeValidator());
    }

    @Test
    public void listAllClients_ReturnsDaoList() {
        List<Client> responseList = new ArrayList<>();
        responseList.add(new Client());
        clientService.setClientDao(makeListAllClientsStub(responseList));

        assertEquals(responseList, clientService.listAllClients());
    }

    @Test
    public void createClient_ReturnsDaoClientId() {
        Integer clientId = 0;
        clientService.setClientDao(makeCreateClientStub(clientId));

        assertEquals(clientId, clientService.createClient(new Client()));
    }

    @Test
    public void readClient_ReturnsDaoRead() {
        Client client = new Client();
        clientService.setClientDao(makeReadClientStub(client));

        assertEquals(client, clientService.readClient(0));
    }

    @Test
    public void updateClient_CallsDaoWithCorrectClient() {
        Client client = new Client();
        ClientDaoSpy clientSpy = makeClientSpy();
        clientService.setClientDao(clientSpy);
        clientService.updateClient(client);

        assertEquals(1, clientSpy.getUpdateCallCount());
        assertEquals(client, clientSpy.getLastUpdateClient());
    }

    @Test
    public void deleteClient_CallsDaoWithCorrecClientId() {
        Integer clientId = 12;
        ClientDaoSpy clientSpy = makeClientSpy();
        clientService.setClientDao(clientSpy);
        clientService.deleteClient(clientId);

        assertEquals(1, clientSpy.getDeleteCallCount());
        assertEquals(clientId, clientSpy.getLastDeleteClientId());
    }

    @Test
    public void validateClient_ViolationsAreSorted() {
        List<String> violationMessages = clientService.validateClient(new Client());
        List<String> expectedOrder = new ArrayList<>();

        expectedOrder.add(Client.COMPANY_NAME_NULL_MESSAGE);
        expectedOrder.add(Client.MAILING_ADDRESS_NULL_MESSAGE);
        expectedOrder.add(Client.PHONE_NUMBER_NULL_MESSAGE);
        expectedOrder.add(Client.WEBSITE_URI_NULL_MESSAGE);

        assertThat(violationMessages, contains(expectedOrder.toArray()));
    }

    @Test
    public void validateClient_FailsWithNullCompanyName() {
        Client client = new ClientStub();
        client.setCompanyName(null);

        assertViolations(client, Client.COMPANY_NAME_NULL_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithEmptyCompanyName() {
        Client client = new ClientStub();
        client.setCompanyName("");

        assertViolations(client, Client.COMPANY_NAME_LENGTH_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithTooLongCompanyName() {
        Client client = new ClientStub();
        client.setCompanyName(String.join("", Collections.nCopies(51, "x")));

        assertViolations(client, Client.COMPANY_NAME_LENGTH_MESSAGE);
    }

    @Test
    public void validateClient_SucceedsWithValidShortCompanyName() {
        Client client = new ClientStub();
        client.setCompanyName("x");

        assertNoViolations(client);
    }

    @Test
    public void validateClient_SucceedsWithValidLongCompanyName() {
        Client client = new ClientStub();
        client.setCompanyName(String.join("", Collections.nCopies(50, "x")));

        assertNoViolations(client);
    }

    @Test
    public void validateClient_FailsWithNullWebsiteUri() {
        Client client = new ClientStub();
        client.setWebsiteUri(null);

        assertViolations(client, Client.WEBSITE_URI_NULL_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithEmptyWebsiteUri() {
        Client client = new ClientStub();
        client.setWebsiteUri("");

        assertViolations(client, Client.WEBSITE_URI_LENGTH_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithTooLongWebsiteUri() {
        Client client = new ClientStub();
        client.setWebsiteUri(String.join("", Collections.nCopies(51, "x")));

        assertViolations(client, Client.WEBSITE_URI_LENGTH_MESSAGE);
    }

    @Test
    public void validateClient_SucceedsWithValidShortWebsiteUri() {
        Client client = new ClientStub();
        client.setWebsiteUri("x");

        assertNoViolations(client);
    }

    @Test
    public void validateClient_SucceedsWithValidLongWebsiteUri() {
        Client client = new ClientStub();
        client.setWebsiteUri(String.join("", Collections.nCopies(50, "x")));

        assertNoViolations(client);
    }

    @Test
    public void validateClient_FailsWithNullPhoneNumber() {
        Client client = new ClientStub();
        client.setPhoneNumber(null);

        assertViolations(client, Client.PHONE_NUMBER_NULL_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithEmptyPhoneNumber() {
        Client client = new ClientStub();
        client.setPhoneNumber("");

        assertViolations(client, Client.PHONE_NUMBER_INVALID_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithInvalidPhoneNumber_Letters() {
        Client client = new ClientStub();
        client.setPhoneNumber("zyx-wvu-tsrq");

        assertViolations(client, Client.PHONE_NUMBER_INVALID_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithInvalidPhoneNumber_NoDelimiters() {
        Client client = new ClientStub();
        client.setPhoneNumber("1234567890");

        assertViolations(client, Client.PHONE_NUMBER_INVALID_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithInvalidPhoneNumber_SpacesAfterDash() {
        Client client = new ClientStub();
        client.setPhoneNumber("123- 456-7890");

        assertViolations(client, Client.PHONE_NUMBER_INVALID_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithInvalidPhoneNumber_Parenthesis() {
        Client client = new ClientStub();
        client.setPhoneNumber("(123) 456-7890");

        assertViolations(client, Client.PHONE_NUMBER_INVALID_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithInvalidPhoneNumber_IncorrectNumberOfDigits() {
        Client client = new ClientStub();
        client.setPhoneNumber("123-456-789");

        assertViolations(client, Client.PHONE_NUMBER_INVALID_MESSAGE);
    }

    @Test
    public void validateClient_SucceedsWithValidPhoneNumber() {
        Client client = new ClientStub();
        client.setPhoneNumber("123-456-7890");

        assertNoViolations(client);
    }

    @Test
    public void validateClient_FailsWithNullMailingAddress() {
        Client client = new ClientStub();
        client.setMailingAddress(null);

        assertViolations(client, Client.MAILING_ADDRESS_NULL_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithEmptyMailingAddress() {
        Client client = new ClientStub();
        client.setMailingAddress("");

        assertViolations(client, Client.MAILING_ADDRESS_LENGTH_MESSAGE);
    }

    @Test
    public void validateClient_FailsWithTooLongMailingAddress() {
        Client client = new ClientStub();
        client.setMailingAddress(String.join("", Collections.nCopies(51, "x")));

        assertViolations(client, Client.MAILING_ADDRESS_LENGTH_MESSAGE);
    }

    @Test
    public void validateClient_SucceedsWithValidShortMailingAddress() {
        Client client = new ClientStub();
        client.setMailingAddress("x");

        assertNoViolations(client);
    }

    @Test
    public void validateClient_SucceedsWithValidLongMailingAddress() {
        Client client = new ClientStub();
        client.setMailingAddress(String.join("", Collections.nCopies(50, "x")));

        assertNoViolations(client);
    }

}
