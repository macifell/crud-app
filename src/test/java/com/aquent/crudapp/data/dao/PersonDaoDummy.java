package com.aquent.crudapp.data.dao;

import java.util.List;

import com.aquent.crudapp.domain.Person;

public class PersonDaoDummy implements PersonDao {

    @Override
    public List<Person> listPeople() {
        return null;
    }

    @Override
    public List<Person> listClientContacts(Integer clientId) {
        return null;
    }

    @Override
    public Integer createPerson(Person person) {
        return null;
    }

    @Override
    public Person readPerson(Integer id) {
        return null;
    }

    @Override
    public void updatePerson(Person person) {}

    @Override
    public void deletePerson(Integer id) {}

}