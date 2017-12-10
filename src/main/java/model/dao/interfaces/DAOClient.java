package model.dao.interfaces;

import java.io.IOException;

import model.Client;

import java.util.ArrayList;


/**
 * Интерфейс, описывающий методы работы с коллекцией Client.
 */
public interface DAOClient {

    /**
     * Метод, добавляющий Client в коллекцию.
     */
    void setClient(Client client);

    /**
     * Метод, удаляющий Client по Id.
     */
    void deleteClient(int id);

    /**
     * Метод, возвращающий коллекцию Client.
     */
    ArrayList<Client> getClients();

    /**
     * Метод, возвращающий Client по Id.
     */
    Client getClient(int id);

    /**
     * Метод, возвращающий коллекцию Client, сформированную по заданным для поиска данным.
     */
    ArrayList<Client> getClientsOnTheDataSet(String data);

    /**
     * Метод, одновляющий коллекцию Clients.
     */
    void updateClients(ArrayList<Client> newClients);

}
