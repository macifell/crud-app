package com.aquent.crudapp.service;

import static com.aquent.crudapp.data.dao.ClientDaoTestFactory.*;
import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

import com.aquent.crudapp.data.dao.ClientDaoSpy;
import com.aquent.crudapp.domain.Client;

public class ClientServiceTest {

    private ClientService clientService;

    @Before
    public void setUp() {
        clientService = new DefaultClientService();
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

}
