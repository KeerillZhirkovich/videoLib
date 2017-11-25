package dao.factories;


import dao.DAOFactory;
import dao.interfaces.*;
import dao.io.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IODAOFactory implements DAOFactory {

    public DAODisc getDAODisc() {
        try {
            return new IODAODisc();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    public DAOClient getDAOClient() {
        try {
            return new IODAOClient();
        } catch (IOException ex) {
            return null;
        }
    }

}
