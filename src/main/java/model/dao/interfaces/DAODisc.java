package model.dao.interfaces;

import model.Disc;

import java.util.ArrayList;


/**
 * Интерфейс, описывающий методы работы с коллекцией Discs.
 */
public interface DAODisc {

    /**
     * Метод, добавляющий Disc в коллекцию.
     */
    void setDisc(Disc disc);

    /**
     * Метод, удаляющий Disc по Id.
     */
    void deleteDisc(int id);

    /**
     * Метод, возвращающий коллекцию Disc.
     */
    ArrayList<Disc> getDiscs();

    /**
     * Метод, возвращающий Disc по Id.
     */
    Disc getDisc(int id);

    /**
     * Метод, возвращающий коллекцию Disc, сформированную по заданным для поиска данным.
     */
    ArrayList<Disc> getDiscsOnTheDataSet(String keywords);

    /**
     * Метод, одновляющий коллекцию Discs.
     */
    void updateDiscs(ArrayList<Disc> newDiscs);

    /**
     * Метод, устанавливающий необходимому Disc clientId.
     */
    void setClient(int discID, int clientID);
}