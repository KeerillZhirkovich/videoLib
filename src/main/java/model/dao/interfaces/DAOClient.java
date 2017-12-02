package model.dao.interfaces;


import java.io.IOException;
import model.Client;

import java.util.ArrayList;

public interface DAOClient {

    void setClient (Client client) throws IOException;
    void deleteClient (int id); /*- метод должен выбрасывать исключение, если у клиента на руках есть диск???
                                    или лучше делать проверку? */
    ArrayList<Client> getClients();
    Client getClient (int id);
    ArrayList<Client> getClientsOnTheDataSet (String data);
    void loadFromFile(String url);

}
