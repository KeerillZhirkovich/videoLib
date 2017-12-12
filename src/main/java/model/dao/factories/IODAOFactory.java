package model.dao.factories;

import model.dao.DaoFactory;
import model.dao.interfaces.*;
import model.dao.io.*;

/**
 * Фабрика, возвращающая объекты DaoDisc и DaoClient с реализацей,
 * осуществляющей ввод-вывод данных из локальных файлов.
 */
public class IODAOFactory implements DaoFactory {

    /**
     * @return
     */
    public DaoDisc getDaoDisc() {
        return new IODAODisc();
    }

    /**
     * @return
     */
    public DaoClient getDaoClient() {
        return new IODAOClient();
    }

}
