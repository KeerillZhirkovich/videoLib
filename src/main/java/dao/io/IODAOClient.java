package dao.io;


import dao.interfaces.DAOClient;
import model.Client;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import static dao.tools.FileChecker.fileIsEmpty;
//import static dao.tools.WorkWithStrings.ifContainsSplit;

public class IODAOClient implements DAOClient {

    private ArrayList<Client> clients;
    private static final String FILE_PATH = "data\\clients";

    public IODAOClient() throws IOException {
        clients = readClients();
    }
    
    public Client getClientByID(int ID)
    {
        for (int i=0;i<clients.size();i++)
            if (clients.get(i).getClientID()==ID)
                return clients.get(i);
        return null;
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
    public void setClient(Client client) throws IOException{

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
        saveChanges();
    }

    @Override
    public void deleteClient(int id) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getClientID() == id) {
                deleteByIndex(i);
            }
        }
        try {
            saveChanges();
        } catch (IOException ex) { }
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
    public ArrayList<Client> getClientsOnTheDataSet(String[] data) {

        ArrayList<Client> result = new ArrayList<>();

        for (int i = 0; i < 3; i++){
            data[i] = data[i].trim();
        }

        for (Client client : clients){
            boolean b = !data[2].isEmpty() && data[2].equals(client.getPhone());
           // b = b && !data[0].isEmpty() && ifContainsSplit(client.getName(), data[0]);
           // b = b && !data[1].isEmpty() && ifContainsSplit(client.getSurname(), data[1]);
            if(b){
                result.add(client);
            }
        }

        return result;
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
