package com.aquent.crudapp.data.dao;

import java.util.List;

import com.aquent.crudapp.domain.Person;

public class PersonDaoTestFactory {

    public static PersonDao makeListPeopleStub(List<Person> responseList) {
        return new PersonDaoDummy() {

            @Override
            public List<Person> listPeople() {
                return responseList;
            }
        };
    }

    public static PersonDao makeListPeopleWithClientStub(List<Person> responseList) {
        return new PersonDaoDummy() {

            @Override
            public List<Person> listPeopleWithClient(Integer clientId) {
                return responseList;
            }
        };
    }

    public static PersonDao makeListPeopleAndClientStub(List<Person> listPeopleResponse,
                                                        List<Person> listPeopleWithClientResponse) {
        return new PersonDaoDummy() {

            @Override
            public List<Person> listPeople() {
                return listPeopleResponse;
            }

            @Override
            public List<Person> listPeopleWithClient(Integer clientId) {
                return listPeopleWithClientResponse;
            }
        };
    }

    public static PersonDaoSpy makePersonSpy(List<Person> allPeople) {
        return new PersonDaoSpy(allPeople);
    }

}
