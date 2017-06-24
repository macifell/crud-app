package com.aquent.crudapp.service;

import static com.aquent.crudapp.service.ViolationUtilities.extractViolationMessages;

import java.util.List;

import javax.validation.Validator;

import org.springframework.transaction.annotation.*;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listAllClients() {
        return clientDao.listAllClients();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createClient(Client client) {
        return clientDao.createClient(client);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readClient(Integer clientId) {
        return clientDao.readClient(clientId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateClient(Client client) {
        clientDao.updateClient(client);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteClient(Integer clientId) {
        clientDao.deleteClient(clientId);
    }

    @Override
    public List<String> validateClient(Client client) {
        return extractViolationMessages(validator.validate(client));
    }

}
