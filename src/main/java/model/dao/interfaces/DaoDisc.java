package model.dao.interfaces;

import model.Disc;

import java.util.ArrayList;


/**
 * Интерфейс, описывающий методы работы с коллекцией Discs.
 */
public interface DaoDisc {

    /**
     * Метод, добавляющий Disc в коллекцию.
     * @param disc
     */
    void setDisc(Disc disc);

    /**
     * Метод, удаляющий Disc по Id.
     * @param id
     */
    void deleteDisc(int id);

    /**
     * Метод, возвращающий коллекцию Disc.
     * @return
     */
    ArrayList<Disc> getDiscs();

    /**
     * Метод, возвращающий Disc по Id.
     * @param id
     * @return
     */
    Disc getDisc(int id);

    /**
     * Метод, возвращающий коллекцию Disc, сформированную по заданным для поиска данным.
     * @param keywords
     * @return
     */
    ArrayList<Disc> getDiscsOnTheDataSet(String keywords);

    /**
     * Метод, одновляющий коллекцию Discs.
     * @param newDiscs
     */
    void updateDiscs(ArrayList<Disc> newDiscs);

    /**
     * Метод, устанавливающий необходимому Disc clientId.
     * @param discID
     * @param clientID
     */
    void setClient(int discID, int clientID);
}