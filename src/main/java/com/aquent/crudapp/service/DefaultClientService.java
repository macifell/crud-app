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

}
