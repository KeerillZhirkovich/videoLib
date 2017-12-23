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
public class IoDaoClient implements DaoClient, Serializable {

    private IoDaoClient() {
    }

    private static class IoDaoClientHolder {
        private final static IoDaoClient IO_DAO_CLIENT_KEEPER = new IoDaoClient();
    }

    public static IoDaoClient getIoDaoClient() {
        return IoDaoClientHolder.IO_DAO_CLIENT_KEEPER;
    }

    /**
     * @param client
     */
    @Override
    public void setClient(Client client) {
        ArrayList<Client> clients = getClients();
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
        DataLoad.writeClients(clients);
    }

    /**
     * @param id
     */
    @Override
    public void deleteClient(int id) {
        ArrayList<Client> clients = getClients();
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getClientID() == id) {
                clients = deleteByIndex(i, clients);
            }
        }
        DataLoad.writeClients(clients);
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Client> getClients() {
        ArrayList<Client> clients;
        clients = DataLoad.getClients();
        return clients;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Client getClient(int id) {
        ArrayList<Client> clients = getClients();

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
        ArrayList<Client> clients = getClients();

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
        ArrayList<Client> clients = getClients();
        LinkedHashSet<Client> updatedClients = new LinkedHashSet<>(clients);
        int last = clients.get(clients.size() - 1).getClientID();
        ArrayList<Client> temp;
        for (Client disc : newClients) {
            if (updatedClients.add(disc)) {
                temp = new ArrayList<>(updatedClients);
                temp.get(temp.size() - 1).setClientID(++last);
                updatedClients = new LinkedHashSet<>(temp);
            }
        }

        clients = new ArrayList<>(updatedClients);
        DataLoad.writeClients(clients);
    }

    /**
     * Служебный метод, возвращающий экземпляр Client по его расположению в коллекции.
     *
     * @param index
     * @return
     */
    public Client getClientByIndex(int index) {
        ArrayList<Client> clients = getClients();
        return clients.get(index);
    }

    /**
     * Служебный метод, удаляющий экземпляр Client по его расположению в коллекции.
     *
     * @param index
     */
    public ArrayList<Client> deleteByIndex(int index, ArrayList<Client> clients) {
        clients.remove(index);
        return clients;
    }

}
