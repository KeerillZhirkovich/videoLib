package model.dao.factories;

import model.dao.DAOFactory;
import model.dao.interfaces.*;
import model.dao.io.*;

/**
 * Фабрика, возвращающая объекты DAODisc и DAOClient с реализацей,
 * осуществляющей ввод-вывод данных из локальных файлов.
 */
public class IODAOFactory implements DAOFactory {

    /**
     * @return
     */
    public DAODisc getDAODisc() {
        return new IODAODisc();
    }

    /**
     * @return
     */
    public DAOClient getDAOClient() {
        return new IODAOClient();
    }

}
