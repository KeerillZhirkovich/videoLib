package dao.interfaces;


import model.Client;

import java.util.ArrayList;

public interface DAOClient {

    void setClient (Client client);
    void deleteClient (int id); /*- метод должен выбрасывать исключение, если у клиента на руках есть диск???
                                    или лучше делать проверку? */
    ArrayList<Client> getClients();
    Client getClient (int id);
    ArrayList<Client> getClientsOnTheDataSet (String[] data); //Принимает массив значений всех полей Disc
    void loadFromFile(String url);

}
