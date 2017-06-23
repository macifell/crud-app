package com.aquent.crudapp.service;

import java.util.List;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Client;

public interface ClientService {

    public void setClientDao(ClientDao clientDao);

    public List<Client> listAllClients();

}
