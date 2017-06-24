package com.aquent.crudapp.data.dao.jdbc;

import java.sql.*;
import java.util.*;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.*;
import org.springframework.transaction.annotation.*;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Client;

public class ClientJdbcDao implements ClientDao {

    private static final String SQL_LIST_CLIENTS = "SELECT * FROM client ORDER BY company_name, client_id";

    private static final String SQL_CREATE_CLIENT = "INSERT INTO client (company_name, website_uri, phone_number, mailing_address)"
                                                    + " VALUES (:companyName, :websiteUri, :phoneNumber, :mailingAddress)";

    private static final String SQL_READ_CLIENT = "SELECT * FROM client WHERE client_id = :clientId";

    private static final String SQL_UPDATE_CLIENT = "UPDATE client SET (company_name, website_uri, phone_number, mailing_address)"
                                                    + " = (:companyName, :websiteUri, :phoneNumber, :mailingAddress)"
                                                    + " WHERE client_id = :clientId";

    private static final String SQL_DELETE_CLIENT = "DELETE FROM client WHERE client_id = :clientId";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listAllClients() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_CLIENTS, new ClientRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createClient(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_CLIENT, new BeanPropertySqlParameterSource(client), keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readClient(Integer clientId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_CLIENT,
                                                         Collections.singletonMap("clientId", clientId),
                                                         new ClientRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateClient(Client client) {
        namedParameterJdbcTemplate.update(SQL_UPDATE_CLIENT, new BeanPropertySqlParameterSource(client));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteClient(Integer clientId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_CLIENT, Collections.singletonMap("clientId", clientId));
    }

    private static final class ClientRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setClientId(rs.getInt("client_id"));
            client.setCompanyName(rs.getString("company_name"));
            client.setWebsiteUri(rs.getString("website_uri"));
            client.setPhoneNumber(rs.getString("phone_number"));
            client.setMailingAddress(rs.getString("mailing_address"));

            return client;
        }
    }

}
