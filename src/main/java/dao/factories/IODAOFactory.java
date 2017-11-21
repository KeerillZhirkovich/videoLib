package dao.factories;


import dao.DAOFactory;
import dao.interfaces.*;
import dao.io.*;

public class IODAOFactory implements DAOFactory {

    public DAODisc getDAODisc() {
        return new IODAODisc();
    }

    public DAOClient getDAOClient() {
        return new IODAOClient();
    }

}
