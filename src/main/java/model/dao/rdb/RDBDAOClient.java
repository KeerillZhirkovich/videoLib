package model.dao.rdb;

import model.dao.interfaces.DAOClient;

import java.util.ArrayList;

import model.Client;


/**
 * Интерфейс, не имеющий реализации в текущей версии программы.
 */
public class RDBDAOClient implements DAOClient {

    /**
     * @param client
     */
    @Override
    public void setClient(Client client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param id
     */
    @Override
    public void deleteClient(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Client> getClients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Client getClient(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param data
     * @return
     */
    @Override
    public ArrayList<Client> getClientsOnTheDataSet(String data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param newClients
     */
    @Override
    public void updateClients(ArrayList<Client> newClients) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
