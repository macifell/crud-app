package com.aquent.crudapp.data.dao;

import java.util.List;

import com.aquent.crudapp.domain.Client;

public class ClientDaoStubFactory {

    public static ClientDao createListAllClientsStub(List<Client> responseList) {
        return new ClientDao() {

            @Override
            public List<Client> listAllClients() {
                return responseList;
            }

            @Override
            public Integer createClient(Client client) {
                return null;
            }

            @Override
            public Client readClient(Integer clientId) {
                return null;
            }

        };
    }

    public static ClientDao createReadClientStub(Client client) {
        return new ClientDao() {

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
                return client;
            }

        };
    }

    public static ClientDao createCreateClientStub(Integer clientId) {
        return new ClientDao() {

            @Override
            public List<Client> listAllClients() {
                return null;
            }

            @Override
            public Integer createClient(Client client) {
                return clientId;
            }

            @Override
            public Client readClient(Integer clientId) {
                return null;
            }

        };
    }
}
