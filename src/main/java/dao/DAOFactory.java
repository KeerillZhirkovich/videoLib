package dao;


import dao.interfaces.DAOClient;
import dao.interfaces.DAODisc;


public interface DAOFactory {

    DAODisc getDAODisc();
    DAOClient getDAOClient();

}
