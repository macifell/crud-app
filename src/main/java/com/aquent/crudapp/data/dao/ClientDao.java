package com.aquent.crudapp.data.dao;

import java.util.List;

import com.aquent.crudapp.domain.Client;

public interface ClientDao {

    public List<Client> listAllClients();

    public Integer createClient(Client client);

    public Client readClient(Integer clientId);

    public void updateClient(Client client);

    public void deleteClient(Integer clientId);

}
