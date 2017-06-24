package com.aquent.crudapp.data.dao;

import java.util.List;

import com.aquent.crudapp.domain.Client;

public class ClientDaoDummy implements ClientDao {

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

    @Override
    public void updateClient(Client client) {}

    @Override
    public void deleteClient(Integer clientId) {}

}