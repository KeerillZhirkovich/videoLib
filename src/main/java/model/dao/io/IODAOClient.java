package model.dao.io;


import model.dao.interfaces.DAOClient;
import model.dao.tools.ObjectAndRelevance;
import model.Client;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import static model.dao.tools.Search.relevance;
import static model.dao.tools.WorkWithStrings.splitData;



public class IODAOClient implements DAOClient {

    private ArrayList<Client> clients = new ArrayList<>();

    public IODAOClient() {
        clients = DataLoad.getClients();
    }


    @Override
    public void setClient(Client client) {

        int id;

        if(clients.isEmpty()){
            id = 1;
        } else {
            if (client.getClientID()==0)
                id = clients.get(clients.size()-1).getClientID()+1;
            else
                id=client.getClientID();
        }
        client.setClientID(id);
        clients.add(client);
    }

    @Override
    public void deleteClient(int id) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getClientID() == id) {
                deleteByIndex(i);
            }
        }
    }

    @Override
    public ArrayList<Client> getClients() {
        return clients;
    }

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

    @Override
    public ArrayList<Client> getClientsOnTheDataSet(String searchString) {

        ArrayList<Client> result = new ArrayList<>();
        ArrayList<String> keywords = splitData(searchString);
        int maxRelevance = keywords.size();
        ArrayList<ObjectAndRelevance<Client>> clientAndRelevance = new ArrayList<>();

        for (Client client : clients) {
            ObjectAndRelevance<Client> clientR = new ObjectAndRelevance<>(client);
            clientR.setRelevance(relevance(keywords, client.toString().toLowerCase()));
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


    public void updateClients(ArrayList<Client> newClients) {

        LinkedHashSet<Client> updatedClients = new LinkedHashSet<>(clients);

        for (Client client : newClients){
            client.setClientID(client.getClientID()+clients.size());
            updatedClients.add(client);
        }

        clients = new ArrayList<>(updatedClients);
    }

    public Client getClientByIndex(int index) {
        return clients.get(index);
    }

    public void deleteByIndex(int index) {
        clients.remove(index);
    }
    
    public void saveClients() throws IOException
    {
        DataLoad.writeClients(clients);
    }
}
