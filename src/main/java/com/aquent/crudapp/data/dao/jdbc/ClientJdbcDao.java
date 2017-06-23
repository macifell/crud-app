package com.aquent.crudapp.data.dao.jdbc;

import java.util.List;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Client;

public class ClientJdbcDao implements ClientDao {

    @Override
    public List<Client> listAllClients() {
        return null;
    }

    @Override
    public Integer createClient(Client client) {
        return null;
    }

    @Override
    public Client readClient(Integer clientId) {
        return null;
    }

}
