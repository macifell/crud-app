package com.aquent.crudapp.controller;

import static java.util.Arrays.asList;

import java.util.*;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.aquent.crudapp.domain.*;
import com.aquent.crudapp.service.*;

@Controller
@RequestMapping("client")
public class ClientController {

    public static final String COMMAND_DELETE = "Delete";

    @Inject
    private ClientService clientService;

    @Inject
    private PersonService personService;

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("client/list");
        mav.addObject("clients", clientService.listAllClients());

        return mav;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("client/create");
        mav.addObject("clientForm", makeClientForm(new Client(), asList()));
        mav.addObject("errors", new ArrayList<String>());

        return mav;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create(ClientForm clientForm) {
        List<String> errors = clientService.validateClient(clientForm.getClient());

        if (errors.isEmpty())
            return createClient(clientForm);
        else
            return displayCreationErrors(clientForm, errors);
    }

    private ModelAndView createClient(ClientForm clientForm) {
        Integer clientId = clientService.createClient(clientForm.getClient());
        clientForm.setClientId(clientId);
        updateClientContacts(clientForm);

        return new ModelAndView("redirect:/client/list");
    }

    private ModelAndView displayCreationErrors(ClientForm clientForm, List<String> errors) {
        ModelAndView mav = new ModelAndView("client/create");
        mav.addObject("clientForm", reloadPeopleInForm(clientForm));
        mav.addObject("errors", errors);

        return mav;
    }

    private ClientForm reloadPeopleInForm(ClientForm clientForm) {
        return makeClientForm(clientForm.getClient(), clientForm.getSelectedPersonIds());
    }

    @RequestMapping(value = "edit/{clientId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView("client/edit");
        Client client = clientService.readClient(clientId);
        List<Integer> selectedPersonIds = personService.listPersonIdsForClient(clientId);

        mav.addObject("clientForm", makeClientForm(client, selectedPersonIds));
        mav.addObject("errors", new ArrayList<String>());

        return mav;
    }

    private ClientForm makeClientForm(Client client, List<Integer> selectedPersonIds) {
        ClientForm clientForm = new ClientForm();
        clientForm.setClient(client);
        clientForm.setPeople(personService.listPeople());
        clientForm.setSelectedPersonIds(selectedPersonIds);

        return clientForm;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView edit(ClientForm clientForm) {
        List<String> errors = clientService.validateClient(clientForm.getClient());

        if (errors.isEmpty())
            return updateClient(clientForm);
        else
            return displayUpdateErrors(clientForm, errors);
    }

    private ModelAndView updateClient(ClientForm clientForm) {
        clientService.updateClient(clientForm.getClient());
        updateClientContacts(clientForm);

        return new ModelAndView("redirect:/client/list");
    }

    private void updateClientContacts(ClientForm clientForm) {
        for (Person person : personService.listPeople())
            if (isPersonLeavingClient(clientForm, person))
                clearClientForPerson(person);
            else if (isPersonJoiningClient(clientForm, person))
                setClientForPerson(person, clientForm.getClientIdString());
    }

    private boolean isPersonLeavingClient(ClientForm clientForm, Person person) {
        return isPersonCurrentlyWithClient(clientForm, person) && !isPersonSelected(clientForm, person);
    }

    private boolean isPersonJoiningClient(ClientForm clientForm, Person person) {
        return !isPersonCurrentlyWithClient(clientForm, person) && isPersonSelected(clientForm, person);
    }

    private boolean isPersonCurrentlyWithClient(ClientForm clientForm, Person person) {
        return Objects.equals(clientForm.getClientIdString(), person.getClientId());
    }

    private boolean isPersonSelected(ClientForm clientForm, Person person) {
        return clientForm.getSelectedPersonIds() != null
               && clientForm.getSelectedPersonIds().contains(person.getPersonId());
    }

    private void clearClientForPerson(Person person) {
        setClientForPerson(person, "");
    }

    private void setClientForPerson(Person person, String clientId) {
        person.setClientId(clientId);
        personService.updatePerson(person);
    }

    private ModelAndView displayUpdateErrors(ClientForm clientForm, List<String> errors) {
        ModelAndView mav = new ModelAndView("client/edit");
        mav.addObject("clientForm", reloadPeopleInForm(clientForm));
        mav.addObject("errors", errors);

        return mav;
    }

    @RequestMapping(value = "delete/{clientId}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView("client/delete");
        mav.addObject("client", clientService.readClient(clientId));

        return mav;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam String command, @RequestParam Integer clientId) {
        if (COMMAND_DELETE.equals(command)) {
            for (Integer personId : personService.listPersonIdsForClient(clientId))
                clearClientForPerson(personService.readPerson(personId));

            clientService.deleteClient(clientId);
        }

        return "redirect:/client/list";
    }

}
