package model.dao.factories;

import model.dao.DAOFactory;
import model.dao.interfaces.*;
import model.dao.rdb.*;

public class RDBDAOFactory implements DAOFactory {

    public DAODisc getDAODisc() {
        return new RDBDAODisc();
    }

    public DAOClient getDAOClient() {
        return new RDBDAOClient();
    }

}
