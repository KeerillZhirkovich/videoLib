package model.dao.factories;

import model.dao.DAOFactory;
import model.dao.interfaces.*;
import model.dao.rdb.*;

/**
 * Фабрика, возвращающая объекты DAODisc и DAOClient с реализацей,
 * осуществляющей ввод-вывод данных из реляционных баз данных.
 * Не реализована в данной версии программы.
 */
public class RDBDAOFactory implements DAOFactory {

    public DAODisc getDAODisc() {
        return new RDBDAODisc();
    }

    public DAOClient getDAOClient() {
        return new RDBDAOClient();
    }

}
