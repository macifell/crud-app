package com.aquent.crudapp.data.dao.jdbc;

import java.sql.*;
import java.util.*;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.*;
import org.springframework.transaction.annotation.*;

import com.aquent.crudapp.data.dao.PersonDao;
import com.aquent.crudapp.domain.Person;

/**
 * Spring JDBC implementation of {@link PersonDao}.
 */
public class PersonJdbcDao implements PersonDao {

    private static final String SQL_LIST_PEOPLE = "SELECT * FROM person ORDER BY first_name, last_name, person_id";
    private static final String SQL_LIST_PEOPLE_WITH_CLIENT = "SELECT * FROM person WHERE client_id = :clientId ORDER BY first_name, last_name, person_id";
    private static final String SQL_READ_PERSON = "SELECT * FROM person WHERE person_id = :personId";
    private static final String SQL_DELETE_PERSON = "DELETE FROM person WHERE person_id = :personId";
    private static final String SQL_UPDATE_PERSON = "UPDATE person SET (first_name, last_name, email_address, street_address, city, state, zip_code, client_id)"
                                                    + " = (:firstName, :lastName, :emailAddress, :streetAddress, :city, :state, :zipCode, :clientId)"
                                                    + " WHERE person_id = :personId";
    private static final String SQL_CREATE_PERSON = "INSERT INTO person (first_name, last_name, email_address, street_address, city, state, zip_code, client_id)"
                                                    + " VALUES (:firstName, :lastName, :emailAddress, :streetAddress, :city, :state, :zipCode, :clientId)";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listPeople() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_PEOPLE, new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listClientContacts(Integer clientId) {
        return namedParameterJdbcTemplate.query(SQL_LIST_PEOPLE_WITH_CLIENT,
                                                Collections.singletonMap("clientId", clientId), new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person readPerson(Integer personId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_PERSON,
                                                         Collections.singletonMap("personId", personId),
                                                         new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deletePerson(Integer personId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_PERSON, Collections.singletonMap("personId", personId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updatePerson(Person person) {
        namedParameterJdbcTemplate.update(SQL_UPDATE_PERSON, new BeanPropertySqlParameterSource(person));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPerson(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_PERSON, new BeanPropertySqlParameterSource(person), keyHolder);
        return keyHolder.getKey().intValue();
    }

    private static final class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setPersonId(rs.getInt("person_id"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setEmailAddress(rs.getString("email_address"));
            person.setStreetAddress(rs.getString("street_address"));
            person.setCity(rs.getString("city"));
            person.setState(rs.getString("state"));
            person.setZipCode(rs.getString("zip_code"));
            person.setClientId(rs.getString("client_id"));
            return person;
        }
    }

}
