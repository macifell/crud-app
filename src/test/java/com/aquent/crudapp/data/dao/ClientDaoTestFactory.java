package com.aquent.crudapp.data.dao;

import java.util.List;

import com.aquent.crudapp.domain.Client;

public class ClientDaoTestFactory {

    public static ClientDao makeListAllClientsStub(List<Client> responseList) {
        return new ClientDaoDummy() {

            @Override
            public List<Client> listAllClients() {
                return responseList;
            }
        };
    }

    public static ClientDao makeReadClientStub(Client client) {
        return new ClientDaoDummy() {

            @Override
            public Client readClient(Integer clientId) {
                return client;
            }

        };
    }

    public static ClientDao makeCreateClientStub(Integer clientId) {
        return new ClientDaoDummy() {

            @Override
            public Integer createClient(Client client) {
                return clientId;
            }
        };
    }

    public static ClientDaoSpy makeClientSpy() {
        return new ClientDaoSpy();
    }

}
