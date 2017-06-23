package com.aquent.crudapp.service;

import static com.aquent.crudapp.data.dao.ClientDaoStubFactory.*;
import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

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
        clientService.setClientDao(createListAllClientsStub(responseList));

        assertEquals(responseList, clientService.listAllClients());
    }

    @Test
    public void createClient_ReturnsDaoClientId() {
        Integer clientId = 0;
        clientService.setClientDao(createCreateClientStub(clientId));

        assertEquals(new Integer(clientId), clientService.createClient(new Client()));
    }

    @Test
    public void readClient_ReturnsDaoRead() {
        Client client = new Client();
        clientService.setClientDao(createReadClientStub(client));

        assertEquals(client, clientService.readClient(0));
    }

}
