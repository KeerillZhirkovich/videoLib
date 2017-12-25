package model.dao.io;

import model.dao.interfaces.DaoDisc;
import model.dao.tools.ObjectAndRelevance;
import model.Disc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

import static model.dao.tools.Search.dataToArray;
import static model.dao.tools.Search.relevance;
import static model.dao.tools.WorkWithStrings.splitData;


/**
 * Класс, реализующий интерфейс DaoDisc. Работа @Override методов описана в интерфейсе.
 */
public class IoDaoDisc implements DaoDisc, Serializable {

    private IoDaoDisc() {
    }

    private static class IoDaoDiscHolder {
        private final static IoDaoDisc IO_DAO_DISC_KEEPER = new IoDaoDisc();
    }

    public static IoDaoDisc getIoDaoDisc() {
        return IoDaoDiscHolder.IO_DAO_DISC_KEEPER;
    }

    /**
     * @param disc
     */
    @Override
    public void setDisc(Disc disc) {
        ArrayList<Disc> discs = getDiscs();
        int id;

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
        
        boolean check = false;
        for (int i = 0; i < discs.size(); i++)
          if (discs.get(i).getDiskID() == disc.getDiskID()) {
            discs.get(i).setOriginalTitle(disc.getOriginalTitle());
            discs.get(i).setRussianTitle(disc.getRussianTitle());
            discs.get(i).setDirector(disc.getDirector());
            discs.get(i).setGenre(disc.getGenre());
            discs.get(i).setDuration(disc.getDuration());
            discs.get(i).setReleaseYear(disc.getReleaseYear());
            discs.get(i).setRating(disc.getRating());
            discs.get(i).setLanguages(disc.getLanguages());
            discs.get(i).setCountry(disc.getCountry());
            discs.get(i).setActors(disc.getActors());
            discs.get(i).setDescription(disc.getDescription());
            discs.get(i).setClientID(disc.getClientID());
            check = true;
            break;
          }
        
        LinkedHashSet<Disc> temp = new LinkedHashSet<>(discs);
        if (!check)        
          temp.add(disc);

        discs = new ArrayList<>(temp);
        DataLoad.writeDiscs(discs);
    }

    /**
     * @param id
     */
    @Override
    public void deleteDisc(int id) {
        ArrayList<Disc> discs = getDiscs();
        for (int i = 0; i < discs.size(); i++) {
            if (discs.get(i).getDiskID() == id) {
                deleteDiscByIndex(i);
                break;
            }
        }
    }

    /**
     * @param id, disc
     */
    public void setDiscByID(Disc disc) {
      ArrayList<Disc> discs = getDiscs();
      for (int i = 0; i < discs.size(); i++) {
        if (discs.get(i).getDiskID() == disc.getDiskID()) {
          setDiscByIndex(i, disc);
          break;
        }
      }
    }

    /**
     * Служебный метод, заменяющий экземпляр Disc по его расположению в коллекции.
     *
     * @param index
     */
    public void setDiscByIndex(int index, Disc disc) {
      ArrayList<Disc> discs = getDiscs();
      discs.set(index, disc);
      DataLoad.writeDiscs(discs);
    }
  
    /**
     * @return
     */
    @Override
    public ArrayList<Disc> getDiscs() {
        ArrayList<Disc> discs;
        discs = DataLoad.getDiscs();
        return discs;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Disc getDisc(int id) {
        ArrayList<Disc> discs = getDiscs();
        Disc d = null;
        for (Disc disc : discs) {
            if (disc.getDiskID() == id) {
                d = disc;
                break;
            }
        }
        return d;
    }

    /**
     * @param searchString
     * @return
     */
    @Override
    public ArrayList<Disc> getDiscsOnTheDataSet(String searchString) {

        ArrayList<Disc> discs = getDiscs();
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

    /**
     * @param newDiscs
     */
    @Override
    public void updateDiscs(ArrayList<Disc> newDiscs) {
        ArrayList<Disc> discs = getDiscs();
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
        DataLoad.writeDiscs(discs);
    }

    /**
     * Служебный метод, сортирующий коллекцию Discs по Id.
     */
//    private void sortDiscsByID() {
//        for (int i = 0; i < discs.size(); i++) {
//            for (int j = i + 1; j < discs.size(); j++) {
//                if (discs.get(i).getDiskID() > discs.get(j).getDiskID()) {
//                    int v = discs.get(i).getDiskID();
//                    discs.get(i).setDiskID(discs.get(j).getDiskID());
//                    discs.get(j).setDiskID(v);
//                }
//            }
//        }
//    }


    /**
     * Служебный метод, возвращающий экземпляр Disc по его расположению в коллекции.
     * @param index
     * @return
     */
    public Disc getDiscByIndex(int index) {
        ArrayList<Disc> discs = getDiscs();
        return discs.get(index);
    }

    /**
     * Служебный метод, удаляющий экземпляр Disc по его расположению в коллекции.
     * @param index
     */
    public void deleteDiscByIndex(int index) {
        ArrayList<Disc> discs = getDiscs();
        discs.remove(index);
        DataLoad.writeDiscs(discs);
    }

    /**
     * @param discID
     * @param clientID
     */
    @Override
    public void setClient(int discID, int clientID) {
        ArrayList<Disc> discs = getDiscs();
        for (int i = 0; i < discs.size(); i++) {
            if (discs.get(i).getDiskID() == discID) {
                discs.get(i).setClientID(clientID);
                break;
            }
        }
        DataLoad.writeDiscs(discs);
    }
}
