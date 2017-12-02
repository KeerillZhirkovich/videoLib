package model.dao;


import model.dao.interfaces.DAOClient;
import model.dao.interfaces.DAODisc;


public interface DAOFactory {

    DAODisc getDAODisc();
    DAOClient getDAOClient();

}
