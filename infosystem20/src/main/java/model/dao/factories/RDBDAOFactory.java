package model.dao.factories;

import model.dao.DaoFactory;
import model.dao.interfaces.*;
import model.dao.rdb.*;

/**
 * Фабрика, возвращающая объекты DaoDisc и DaoClient с реализацей,
 * осуществляющей ввод-вывод данных из реляционных баз данных.
 * Не реализована в данной версии программы.
 */
public class RDBDAOFactory implements DaoFactory {

    /**
     * @return
     */
    public DaoDisc getDaoDisc() {
        return new RDBDAODisc();
    }

    /**
     * @return
     */
    public DaoClient getDaoClient() {
        return new RDBDAOClient();
    }

}
