package com.aquent.crudapp.controller;

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
        mav.addObject("client", new Client());
        mav.addObject("people", personService.listPeople());
        mav.addObject("errors", new ArrayList<String>());

        return mav;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ModelAndView create(Client client) {
        List<String> errors = clientService.validateClient(client);

        if (errors.isEmpty()) {
            clientService.createClient(client);

            return new ModelAndView("redirect:/client/list");
        } else {
            ModelAndView mav = new ModelAndView("client/create");
            mav.addObject("client", client);
            mav.addObject("people", personService.listPeople());
            mav.addObject("errors", errors);

            return mav;
        }
    }

    @RequestMapping(value = "edit/{clientId}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView("client/edit");
        ClientForm clientForm = new ClientForm();
        clientForm.setClient(clientService.readClient(clientId));
        clientForm.setPeople(personService.listPeople());
        clientForm.setSelectedPersonIds(personService.listPersonIdsForClient(clientId));

        mav.addObject("clientForm", clientForm);
        mav.addObject("errors", new ArrayList<String>());

        return mav;
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
        updatePeopleWithClient(clientForm);

        return new ModelAndView("redirect:/client/list");
    }

    private void updatePeopleWithClient(ClientForm clientForm) {
        for (Person person : personService.listPeople())
            if (isPersonLeavingClient(clientForm, person))
                setClientForPerson(person, "");
            else if (isPersonJoiningClient(clientForm, person))
                setClientForPerson(person, clientForm.getClientIdString());
    }

    private void setClientForPerson(Person person, String clientId) {
        person.setClientId(clientId);
        List<String> personErrors = personService.validatePerson(person);
        personService.updatePerson(person);
    }

    private boolean isPersonLeavingClient(ClientForm clientForm, Person person) {
        return isPersonCurrentlyWithClient(clientForm, person) && !isPersonSelected(clientForm, person);
    }

    private boolean isPersonJoiningClient(ClientForm clientForm, Person person) {
        return !isPersonCurrentlyWithClient(clientForm, person) && isPersonSelected(clientForm, person);
    }

    private boolean isPersonCurrentlyWithClient(ClientForm clientForm, Person person) {
        String clientId = clientForm.getClientIdString();

        return clientId.equals(person.getClientId());
    }

    private boolean isPersonSelected(ClientForm clientForm, Person person) {
        return clientForm.getSelectedPersonIds() != null
               && clientForm.getSelectedPersonIds().contains(person.getPersonId());
    }

    private ModelAndView displayUpdateErrors(ClientForm clientForm, List<String> errors) {
        ModelAndView mav = new ModelAndView("client/edit");
        mav.addObject("clientForm", clientForm);
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
        if (COMMAND_DELETE.equals(command))
            clientService.deleteClient(clientId);

        return "redirect:/client/list";
    }

    public static class ClientForm {

        public Client client;
        public List<Person> people;
        public List<Integer> selectedPersonIds;

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public List<Person> getPeople() {
            return people;
        }

        public void setPeople(List<Person> people) {
            this.people = people;
        }

        public List<Integer> getSelectedPersonIds() {
            return selectedPersonIds;
        }

        public void setSelectedPersonIds(List<Integer> selectedPersonIds) {
            this.selectedPersonIds = selectedPersonIds;
        }

        public String getClientIdString() {
            return client.getClientId().toString();
        }
    }

}
