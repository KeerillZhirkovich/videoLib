package model.dao.io;

import model.dao.interfaces.DAODisc;
import model.dao.tools.ObjectAndRelevance;
import model.Disc;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

import static model.dao.tools.Search.dataToArray;
import static model.dao.tools.Search.relevance;
import static model.dao.tools.WorkWithStrings.splitData;


/**
 * Класс, реализующий интерфейс DAODisc. Работа @Override методов описана в интерфейсе.
 */
public class IODAODisc implements DAODisc {

    private ArrayList<Disc> discs = new ArrayList<>();

    public IODAODisc() {
        discs = DataLoad.getDiscs();
    }

    @Override
    public void setDisc(Disc disc) {
        int id;
        LinkedHashSet<Disc> temp = new LinkedHashSet<>(discs);

        if (discs.isEmpty()) {
            id = 1;
        } else {
            if (disc.getDiskID() == 0) {
                id = discs.get(discs.size() - 1).getDiskID() + 1;
            } else {
                id = disc.getDiskID();
            }
        }
        disc.setDiskID(id);
        temp.add(disc);

        discs = new ArrayList<>(temp);
    }

    @Override
    public void deleteDisc(int id) {
        for (int i = 0; i < discs.size(); i++) {
            if (discs.get(i).getDiskID() == id) {
                deleteDiscByIndex(i);
                break;
            }
        }
    }

    @Override
    public ArrayList<Disc> getDiscs() {
        return discs;
    }

    @Override
    public Disc getDisc(int id) {
        Disc d = null;
        for (Disc disc : discs) {
            if (disc.getDiskID() == id) {
                d = disc;
                break;
            }
        }
        return d;
    }

    @Override
    public ArrayList<Disc> getDiscsOnTheDataSet(String searchString) {

        ArrayList<Disc> result = new ArrayList<>();
        ArrayList<String> keywords = splitData(searchString);

        int maxRelevance = keywords.size();
        ArrayList<ObjectAndRelevance<Disc>> discAndRelevance = new ArrayList<>();

        if (maxRelevance == 0) {
            return discs;
        }

        for (Disc disc : discs) {
            ObjectAndRelevance<Disc> discR = new ObjectAndRelevance<>(disc);
            discR.setRelevance(relevance(dataToArray(disc), keywords));
            if (discR.getRelevance() != 0) {
                discAndRelevance.add(discR);
            }
        }

        if (discAndRelevance.isEmpty()) {
            Disc emptyDisc = new Disc();

            emptyDisc.setRussianTitle("Ничего не найдено");
            result.add(emptyDisc);

            return result;
        }

        Collections.sort(discAndRelevance);

        if (discAndRelevance.get(0).getRelevance() == maxRelevance) {
            for (ObjectAndRelevance<Disc> disc : discAndRelevance) {
                if (disc.getRelevance() < maxRelevance) break;
                result.add(disc.getData());
            }
            return result;
        } else {
            for (ObjectAndRelevance<Disc> dar : discAndRelevance) {
                result.add(dar.getData());
            }
            return result;
        }
    }

    @Override
    public void updateDiscs(ArrayList<Disc> newDiscs) {

        LinkedHashSet<Disc> updatedDiscs = new LinkedHashSet<>(discs);
        int last = discs.get(discs.size() - 1).getDiskID();
        ArrayList<Disc> temp;
        for (Disc disc : newDiscs) {
            if (updatedDiscs.add(disc)) {
                temp = new ArrayList<>(updatedDiscs);
                temp.get(temp.size() - 1).setDiskID(++last);
                updatedDiscs = new LinkedHashSet<>(temp);
            }
        }

        discs = new ArrayList<>(updatedDiscs);
    }

    /**
     * Служебный метод, сортирующий коллекцию Discs по Id.
     */
    private void sortDiscsByID() {
        for (int i = 0; i < discs.size(); i++) {
            for (int j = i + 1; j < discs.size(); j++) {
                if (discs.get(i).getDiskID() > discs.get(j).getDiskID()) {
                    int v = discs.get(i).getDiskID();
                    discs.get(i).setDiskID(discs.get(j).getDiskID());
                    discs.get(j).setDiskID(v);
                }
            }
        }
    }

    /**
     * Служебный метод, возвращающий экземпляр Disc по его расположению в коллекции.
     */
    public Disc getDiscByIndex(int index) {
        return discs.get(index);
    }

    /**
     * Служебный метод, удаляющий экземпляр Disc по его расположению в коллекции.
     */
    public void deleteDiscByIndex(int index) {
        discs.remove(index);
    }

    @Override
    public void setClient(int discID, int clientID) {
        for (int i = 0; i < discs.size(); i++) {
            if (discs.get(i).getDiskID() == discID) {
                discs.get(i).setClientID(clientID);
                break;
            }
        }
    }

}
