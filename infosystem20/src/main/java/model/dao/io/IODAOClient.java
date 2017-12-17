package model.dao.io;

import model.dao.interfaces.DaoClient;
import model.dao.tools.ObjectAndRelevance;
import model.Client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

import static model.dao.tools.Search.dataToArray;
import static model.dao.tools.Search.relevance;
import static model.dao.tools.WorkWithStrings.splitData;


/**
 * Класс, реализующий интерфейс DaoClient. Работа @Override методов описана в интерфейсе.
 */
public class IODAOClient implements DaoClient, Serializable {

    private ArrayList<Client> clients = new ArrayList<>();

    public IODAOClient() {
        clients = DataLoad.getClients();
    }

    /**
     * @param client
     */
    @Override
    public void setClient(Client client) {
        int id;
        LinkedHashSet<Client> temp = new LinkedHashSet<>(clients);

        if (clients.isEmpty()) {
            id = 1;
        } else {
            if (client.getClientID() == 0) {
                id = clients.get(clients.size() - 1).getClientID() + 1;
            } else {
                id = client.getClientID();
            }
        }
        client.setClientID(id);
        temp.add(client);

        clients = new ArrayList<>(temp);
    }

    /**
     * @param id
     */
    @Override
    public void deleteClient(int id) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getClientID() == id) {
                deleteByIndex(i);
            }
        }
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Client getClient(int id) {

        Client client = null;

        for (Client c : clients) {
            if (c.getClientID() == id) {
                client = c;
            }
        }
        return client;
    }

    /**
     * @param searchString
     * @return
     */
    @Override
    public ArrayList<Client> getClientsOnTheDataSet(String searchString) {

        ArrayList<Client> result = new ArrayList<>();
        ArrayList<String> keywords = splitData(searchString);
        int maxRelevance = keywords.size();
        ArrayList<ObjectAndRelevance<Client>> clientAndRelevance = new ArrayList<>();

        for (Client client : clients) {
            ObjectAndRelevance<Client> clientR = new ObjectAndRelevance<>(client);
            clientR.setRelevance(relevance(dataToArray(client), keywords));
            clientAndRelevance.add(clientR);
        }

        Collections.sort(clientAndRelevance);

        if (clientAndRelevance.get(0).getRelevance() == maxRelevance) {
            int i = 0;
            while (clientAndRelevance.get(i).getRelevance() == maxRelevance) {
                result.add(clientAndRelevance.get(i).getData());
                i++;
            }
            return result;
        } else {
            for (ObjectAndRelevance<Client> car : clientAndRelevance) {
                result.add(car.getData());
            }
            return result;
        }
    }

    /**
     * @param newClients
     */
    @Override
    public void updateClients(ArrayList<Client> newClients) {

        LinkedHashSet<Client> updatedClients = new LinkedHashSet<>(clients);

        for (Client client : newClients) {
            client.setClientID(client.getClientID() + clients.size());
            updatedClients.add(client);
        }

        clients = new ArrayList<>(updatedClients);
    }

    /**
     * Служебный метод, возвращающий экземпляр Client по его расположению в коллекции.
     * @param index
     * @return
     */
    public Client getClientByIndex(int index) {
        return clients.get(index);
    }

    /**
     * Служебный метод, удаляющий экземпляр Client по его расположению в коллекции.
     * @param index
     */
    public void deleteByIndex(int index) {
        clients.remove(index);
    }

}
