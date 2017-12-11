package model.dao;

import model.dao.interfaces.DAOClient;
import model.dao.interfaces.DAODisc;

/**
 * Абстрактная фабрика объектов DAO.
 */
public interface DAOFactory {

    /**
     * @return
     */
    DAODisc getDAODisc();

    /**
     * @return
     */
    DAOClient getDAOClient();

}
