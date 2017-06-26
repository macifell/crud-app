package com.aquent.crudapp.data.dao;

import java.util.*;

import com.aquent.crudapp.domain.Person;

public class PersonDaoSpy extends PersonDaoDummy {

    private int updateCallCount;
    private List<Person> updatedPeople;
    private List<Person> allPeople;
    private List<Person> clientContacts;

    public PersonDaoSpy() {
        this.updateCallCount = 0;
        this.updatedPeople = new ArrayList<>();
        this.allPeople = new ArrayList<>();
        this.clientContacts = null;
    }

    public void setAllPeople(List<Person> allPeople) {
        this.allPeople = allPeople;
    }

    public void setClientContacts(List<Person> clientContacts) {
        this.clientContacts = clientContacts;
    }

    public int getUpdateCallCount() {
        return updateCallCount;
    }

    public List<Person> getUpdatedPeople() {
        return updatedPeople;
    }

    @Override
    public List<Person> listPeople() {
        return allPeople;
    }

    @Override
    public Person readPerson(Integer personId) {
        Person match = null;
        
        for (Person person : allPeople)
            if (person.getPersonId() == personId)
                match = person;
        
        return match;
    }

    @Override
    public List<Person> listClientContacts(Integer clientId) {
        return clientContacts;
    }

    @Override
    public void updatePerson(Person person) {
        updateCallCount++;
        updatedPeople.add(person);
    }

}