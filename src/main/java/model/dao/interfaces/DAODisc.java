package model.dao.interfaces;

import model.Disc;

import java.util.ArrayList;

public interface DAODisc {

    void setDisc(Disc disc);

    void deleteDisc(int id);

    ArrayList<Disc> getDiscs();

    Disc getDisc(int id);

    ArrayList<Disc> getDiscsOnTheDataSet(String keywords);

    void updateDiscs(ArrayList<Disc> newDiscs);
}
