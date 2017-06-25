package com.aquent.crudapp.service;

import java.util.List;

import javax.validation.Validator;

import com.aquent.crudapp.data.dao.PersonDao;
import com.aquent.crudapp.domain.Person;

public interface PersonService {

    void setValidator(Validator validator);

    void setPersonDao(PersonDao personDao);

    List<Person> listPeople();

    List<Person> listPeopleWithClient(Integer clientId);

    Integer createPerson(Person person);

    Person readPerson(Integer id);

    void updatePerson(Person person);

    void deletePerson(Integer id);

    List<String> validatePerson(Person person);

}
