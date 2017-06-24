package com.aquent.crudapp.controller;

import static com.aquent.crudapp.data.dao.ClientDaoTestFactory.*;
import static com.aquent.crudapp.testutil.ValidationTestTools.makeValidator;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
import org.springframework.web.servlet.ModelAndView;

import com.aquent.crudapp.data.dao.ClientDaoDummy;
import com.aquent.crudapp.domain.*;
import com.aquent.crudapp.service.*;

public class ClientControllerTest {

    ClientController clientController;
    ClientService clientService;

    private void assertViewName(String viewName, ModelAndView modelAndView) {
        assertEquals(viewName, modelAndView.getViewName());
    }

    private void assertModelContainsKey(String key, ModelAndView modelAndView) {
        assertTrue(modelAndView.getModel().containsKey(key));
    }

    private void assertModelIsEmpty(ModelAndView modelAndView) {
        assertTrue(modelAndView.getModel().isEmpty());
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
        clientService.setClientDao(makeListAllClientsStub(new ArrayList<>()));
        ModelAndView modelAndView = clientController.list();

        assertViewName("client/list", modelAndView);
        assertModelContainsKey("clients", modelAndView);
    }

    @Test
    public void create_SetsCorrectModelAndView() {
        clientService.setClientDao(new ClientDaoDummy());
        ModelAndView modelAndView = clientController.create();

        assertViewName("client/create", modelAndView);
        assertModelContainsKey("client", modelAndView);
        assertModelContainsKey("errors", modelAndView);
    }

    @Test
    public void createClient_SetsCorrectModelAndView_ValidationFails() {
        clientService.setClientDao(makeCreateClientStub(1));
        Client client = new Client();
        ModelAndView modelAndView = clientController.create(client);

        assertViewName("client/create", modelAndView);
        assertModelContainsKey("client", modelAndView);
        assertModelContainsKey("errors", modelAndView);
    }

    @Test
    public void createClient_SetsCorrectModelAndView_ValidationSucceeds() {
        clientService.setClientDao(makeCreateClientStub(1));
        Client client = new ClientStub();
        ModelAndView modelAndView = clientController.create(client);

        assertViewName("redirect:/client/list", modelAndView);
        assertModelIsEmpty(modelAndView);
    }

    @Test
    public void edit_SetsCorrectModelAndView() {
        Client client = new ClientStub();
        clientService.setClientDao(makeReadClientStub(client));
        ModelAndView modelAndView = clientController.edit(client.getClientId());

        assertViewName("client/edit", modelAndView);
        assertModelContainsKey("client", modelAndView);
        assertModelContainsKey("errors", modelAndView);
    }

    @Test
    public void editClient_SetsCorrectModelAndView_ValidationFails() {
        clientService.setClientDao(new ClientDaoDummy());
        ModelAndView modelAndView = clientController.edit(new Client());

        assertViewName("client/edit", modelAndView);
        assertModelContainsKey("client", modelAndView);
        assertModelContainsKey("errors", modelAndView);
    }

    @Test
    public void editClient_SetsCorrectModelAndView_ValidationSucceeds() {
        clientService.setClientDao(new ClientDaoDummy());
        ModelAndView modelAndView = clientController.edit(new ClientStub());

        assertViewName("redirect:/client/list", modelAndView);
        assertModelIsEmpty(modelAndView);
    }

    @Test
    public void delete_SetsCorrectModelAndView() {
        Client client = new ClientStub();
        clientService.setClientDao(makeReadClientStub(client));
        ModelAndView modelAndView = clientController.delete(client.getClientId());

        assertViewName("client/delete", modelAndView);
        assertModelContainsKey("client", modelAndView);
    }

    @Test
    public void deleteClient_SetsCorrectModelAndView() {
        Client client = new ClientStub();
        clientService.setClientDao(new ClientDaoDummy());
        String viewName = clientController.delete(ClientController.COMMAND_DELETE, client.getClientId());

        assertEquals("redirect:/client/list", viewName);
    }

}
