package com.aquent.crudapp.service;

import java.util.*;

import javax.validation.*;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Client;

public class DefaultClientService implements ClientService {

    private ClientDao clientDao;
    private Validator validator;

    @Override
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public void setValidator(Validator validator) {
        this.validator = validator;
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

    @Override
    public List<String> validateClient(Client client) {
        List<String> violationMessages = new ArrayList<>();
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        for (ConstraintViolation<Client> violation : violations) {
            violationMessages.add(violation.getMessage());
        }

        return violationMessages;
    }

}
