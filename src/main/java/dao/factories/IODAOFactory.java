package dao.factories;


import dao.DAOFactory;
import dao.interfaces.*;
import dao.io.*;
import java.io.IOException;

public class IODAOFactory implements DAOFactory {

    public DAODisc getDAODisc() {
        try {
            return new IODAODisc();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    public DAOClient getDAOClient() {
        return new IODAOClient();
    }

}
