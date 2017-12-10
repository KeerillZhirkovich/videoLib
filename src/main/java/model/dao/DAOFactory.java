package model.dao;

import model.dao.interfaces.DAOClient;
import model.dao.interfaces.DAODisc;

/**
 * Абстрактная фабрика объектов DAO.
 */
public interface DAOFactory {

    DAODisc getDAODisc();

    DAOClient getDAOClient();

}
