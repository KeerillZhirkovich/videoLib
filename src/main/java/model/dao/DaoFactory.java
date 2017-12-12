/**
 * package model.dao;
 */

package model.dao;

import model.dao.interfaces.DaoClient;
import model.dao.interfaces.DaoDisc;

/**
 * Абстрактная фабрика объектов DAO.
 */
public interface DaoFactory {

    /**
     * @return - DaoDisc type.
     */
  DaoDisc getDaoDisc();

    /**
     * @return - DaoClient type.
     */
  DaoClient getDaoClient();

}
