package com.aquent.crudapp.data.dao;

import java.util.*;

import com.aquent.crudapp.domain.Person;

public class PersonDaoSpy extends PersonDaoDummy {

    private int updateCallCount;
    private List<Person> updatedPeople;
    private List<Person> allPeople;

    public PersonDaoSpy(List<Person> allPeople) {
        this.updateCallCount = 0;
        this.updatedPeople = new ArrayList<>();
        this.allPeople = allPeople;
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
    public void updatePerson(Person person) {
        updateCallCount++;
        updatedPeople.add(person);
    }

}