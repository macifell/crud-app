package com.aquent.crudapp.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Client;

public class ClientServiceTest {

    private ClientService clientService;

    private ClientDao createClientDaoStub(List<Client> responseList) {
        return new ClientDao() {

            @Override
            public List<Client> listAllClients() {
                return responseList;
            }
        };
    }

    @Before
    public void setUp() {
        clientService = new DefaultClientService();
    }

    @Test
    public void listClient_ReturnsDaoList() {
        List<Client> responseList = new ArrayList<>();
        responseList.add(new Client());
        clientService.setClientDao(createClientDaoStub(responseList));

        assertEquals(responseList, clientService.listAllClients());
    }

}
