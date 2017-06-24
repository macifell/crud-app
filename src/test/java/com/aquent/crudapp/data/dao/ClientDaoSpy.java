package com.aquent.crudapp.data.dao;

import com.aquent.crudapp.domain.Client;

public class ClientDaoSpy extends ClientDaoDummy {

    private int updateCallCount = 0;
    private int deleteCallCount = 0;

    private Client lastUpdateClient = null;
    private Integer lastDeleteClientId = null;

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