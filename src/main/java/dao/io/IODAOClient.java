package dao.io;


import dao.interfaces.DAOClient;
import dao.tools.ObjectAndRelevance;
import model.Client;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import static dao.tools.FileChecker.fileIsEmpty;
import static dao.tools.Search.relevance;
import static dao.tools.WorkWithStrings.splitData;



public class IODAOClient implements DAOClient {

    private ArrayList<Client> clients = new ArrayList<Client>();
    private static final String FILE_PATH = "src\\data\\clients";

    public IODAOClient() {
        try {
            clients = readClients();
        }
        catch (IOException e) {
        }
    }

    public void saveChanges () throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
        oos.writeObject(clients);
    }

    private ArrayList<Client> readClients() throws IOException   {

        ArrayList<Client> clients = new ArrayList<>();
        try {
            if (fileIsEmpty(FILE_PATH)) {
                return clients;
            } else {          
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
                    clients.addAll((ArrayList<Client>) ois.readObject());
                    return clients;           
            }
        } catch (IOException | ClassNotFoundException ex) {     
                throw new IOException(ex);            
        }
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


    public void loadFromFile(String url) {

        LinkedHashSet<Client> updatedClients = new LinkedHashSet<>(clients);
        ArrayList<Client> newClients = new ArrayList<>();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(url))){
            newClients = (ArrayList<Client>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

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
}
