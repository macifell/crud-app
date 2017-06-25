package com.aquent.crudapp.data.dao;

import java.util.*;

import com.aquent.crudapp.domain.Person;

public class PersonDaoSpy extends PersonDaoDummy {

    private int updateCallCount;
    private List<Person> updatedPeople;
    private List<Person> allPeople;
    private List<Person> peopleWithClient;
    private Person personToRead;

    public PersonDaoSpy(List<Person> allPeople) {
        this.updateCallCount = 0;
        this.updatedPeople = new ArrayList<>();
        this.allPeople = allPeople;
        this.peopleWithClient = null;
        this.personToRead = null;
    }

    public void setPeopleWithClient(List<Person> peopleWithClient) {
        this.peopleWithClient = peopleWithClient;
    }

    public void setReadPerson(Person personToRead) {
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
    public List<Person> listPeopleWithClient(Integer clientId) {
        return peopleWithClient;
    }

    @Override
    public void updatePerson(Person person) {
        updateCallCount++;
        updatedPeople.add(person);
    }

}