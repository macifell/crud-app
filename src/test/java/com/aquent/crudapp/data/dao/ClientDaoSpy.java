package com.aquent.crudapp.data.dao;

import com.aquent.crudapp.domain.Client;

public class ClientDaoSpy extends ClientDaoDummy {

    private int updateCallCount;
    private int deleteCallCount;
    private Client lastUpdateClient;
    private Integer lastDeleteClientId;

    public ClientDaoSpy() {
        updateCallCount = 0;
        deleteCallCount = 0;
        lastUpdateClient = null;
        lastDeleteClientId = null;
    }

    public int getUpdateCallCount() {
        return updateCallCount;
    }

    public int getDeleteCallCount() {
        return deleteCallCount;
    }

    public Client getLastUpdateClient() {
        return lastUpdateClient;
    }

    public Integer getLastDeleteClientId() {
        return lastDeleteClientId;
    }

    @Override
    public void updateClient(Client client) {
        updateCallCount++;
        lastUpdateClient = client;
    }

    @Override
    public void deleteClient(Integer clientId) {
        deleteCallCount++;
        lastDeleteClientId = clientId;
    }

}