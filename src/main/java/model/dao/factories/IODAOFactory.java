package model.dao.factories;

import model.dao.DAOFactory;
import model.dao.interfaces.*;
import model.dao.io.*;

public class IODAOFactory implements DAOFactory {

    public DAODisc getDAODisc() {
        return new IODAODisc();
    }

    public DAOClient getDAOClient() {
        return new IODAOClient();
    }

}
