package com.aquent.crudapp.service;

import static com.aquent.crudapp.service.ViolationUtilities.extractViolationMessages;

import java.util.*;

import javax.validation.Validator;

import org.springframework.transaction.annotation.*;

import com.aquent.crudapp.data.dao.PersonDao;
import com.aquent.crudapp.domain.Person;

public class DefaultPersonService implements PersonService {

    private PersonDao personDao;
    private Validator validator;

    @Override
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listPeople() {
        return personDao.listPeople();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Integer> listPersonIdsForClient(Integer clientId) {
        List<Person> peopleForClient = personDao.listPeopleWithClient(clientId);
        List<Integer> personIds = new ArrayList<>(peopleForClient.size());

        for (Person person : peopleForClient) {
            personIds.add(person.getPersonId());
        }

        return personIds;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person readPerson(Integer id) {
        return personDao.readPerson(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPerson(Person person) {
        return personDao.createPerson(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updatePerson(Person person) {
        personDao.updatePerson(person);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deletePerson(Integer id) {
        personDao.deletePerson(id);
    }

    @Override
    public List<String> validatePerson(Person person) {
        return extractViolationMessages(validator.validate(person));
    }

}
