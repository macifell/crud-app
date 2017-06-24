package com.aquent.crudapp.service;

import java.util.List;

import javax.validation.Validator;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.*;

public interface ClientService {

    public void setClientDao(ClientDao clientDao);

    public void setValidator(Validator validator);

    public List<Client> listAllClients();

    public Integer createClient(Client client);

    public Client readClient(Integer clientId);

    public void updateClient(Client client);

    public void deleteClient(Integer clientId);

    List<String> validateClient(Client client);

}
