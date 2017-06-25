package com.aquent.crudapp.data.dao;

import java.util.List;

import com.aquent.crudapp.domain.Person;

public interface PersonDao {

    List<Person> listPeople();

    List<Person> listPeopleWithClient(Integer clientId);

    Integer createPerson(Person person);

    Person readPerson(Integer id);

    void updatePerson(Person person);

    void deletePerson(Integer id);

}
