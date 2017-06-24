package com.aquent.crudapp.controller;

import static com.aquent.crudapp.testutil.ValidationTestTools.makeValidator;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import com.aquent.crudapp.data.dao.ClientDaoTestFactory;
import com.aquent.crudapp.service.*;

public class ClientControllerTest {

    ClientController clientController;
    ClientService clientService;

    private void assertViewName(String viewName) {
        assertEquals("client/list", clientController.list().getViewName());
    }

    private void assertModelContainsKey(String key) {
        assertTrue(clientController.list().getModel().containsKey(key));
    }

    @Before
    public void setUp() {
        clientService = new DefaultClientService();
        clientService.setValidator(makeValidator());
        clientController = new ClientController();
        clientController.setClientService(clientService);
    }

    @Test
    public void list_SetsCorrectModelAndView() {
        clientService.setClientDao(ClientDaoTestFactory.makeListAllClientsStub(new ArrayList<>()));

        assertViewName("client/list");
        assertModelContainsKey("clients");
    }

}
