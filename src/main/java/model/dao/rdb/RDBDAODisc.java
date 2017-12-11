package model.dao.rdb;

import model.dao.interfaces.DAODisc;

import java.util.ArrayList;

import model.Disc;


/**
 * Интерфейс, не имеющий реализации в текущей версии программы.
 */
public class RDBDAODisc implements DAODisc {

    /**
     * @param disc
     */
    @Override
    public void setDisc(Disc disc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param id
     */
    @Override
    public void deleteDisc(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Disc> getDiscs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Disc getDisc(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param data
     * @return
     */
    @Override
    public ArrayList<Disc> getDiscsOnTheDataSet(String data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param newDiscs
     */
    @Override
    public void updateDiscs(ArrayList<Disc> newDiscs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param discID
     * @param clientID
     */
    @Override
    public void setClient(int discID, int clientID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
