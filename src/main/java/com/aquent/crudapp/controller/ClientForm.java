package com.aquent.crudapp.controller;

import java.util.List;

import com.aquent.crudapp.domain.*;

public class ClientForm {

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

    public void setClientId(Integer clientId) {
        this.client.setClientId(clientId);
    }

    public String getClientIdString() {
        Integer clientId = client.getClientId();

        return clientId != null ? clientId.toString() : null;
    }
}