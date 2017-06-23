package com.aquent.crudapp.service;

import java.util.List;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Client;

public class DefaultClientService implements ClientService {

    private ClientDao clientDao;

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public List<Client> listAllClients() {
        return clientDao.listAllClients();
    }

    @Override
    public Integer createClient(Client client) {
        return clientDao.createClient(client);
    }

    @Override
    public Client readClient(Integer clientId) {
        return clientDao.readClient(clientId);
    }

    @Override
    public void updateClient(Client client) {
        clientDao.updateClient(client);
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientDao.deleteClient(clientId);
    }

}
