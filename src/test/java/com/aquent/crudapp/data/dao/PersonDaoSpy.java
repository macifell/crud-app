package com.aquent.crudapp.data.dao;

import java.util.*;

import com.aquent.crudapp.domain.Person;

public class PersonDaoSpy extends PersonDaoDummy {

    private int updateCallCount;
    private List<Person> updatedPeople;
    private List<Person> allPeople;
    private List<Person> peopleWithClient;
    private Person personToRead;

    public PersonDaoSpy() {
        this.updateCallCount = 0;
        this.updatedPeople = new ArrayList<>();
        this.allPeople = null;
        this.peopleWithClient = null;
        this.personToRead = null;
    }

    public void setAllPeople(List<Person> allPeople) {
        this.allPeople = allPeople;
    }

    public void setPeopleWithClient(List<Person> peopleWithClient) {
        this.peopleWithClient = peopleWithClient;
    }

    public void setPersonToRead(Person personToRead) {
        this.personToRead = personToRead;
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
    public Person readPerson(Integer id) {
        return personToRead;
    }

    @Override
    public List<Person> listClientContacts(Integer clientId) {
        return peopleWithClient;
    }

    @Override
    public void updatePerson(Person person) {
        updateCallCount++;
        updatedPeople.add(person);
    }

}