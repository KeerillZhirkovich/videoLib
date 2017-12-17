package model.dao.interfaces;

import java.io.IOException;

import model.Client;

import java.util.ArrayList;


/**
 * Интерфейс, описывающий методы работы с коллекцией Client.
 */
public interface DaoClient {


    /**
     * Метод, добавляющий Client в коллекцию.
     * @param client
     */
    void setClient(Client client);

    /**
     * Метод, удаляющий Client по Id.
     * @param id
     */
    void deleteClient(int id);

    /**
     * Метод, возвращающий коллекцию Client.
     * @return
     */
    ArrayList<Client> getClients();

    /**
     * Метод, возвращающий Client по Id.
     * @param id
     * @return
     */
    Client getClient(int id);

    /**
     * Метод, возвращающий коллекцию Client, сформированную по заданным для поиска данным.
     * @param data
     * @return
     */
    ArrayList<Client> getClientsOnTheDataSet(String data);

    /**
     * Метод, одновляющий коллекцию Clients.
     * @param newClients
     */
    void updateClients(ArrayList<Client> newClients);

}
