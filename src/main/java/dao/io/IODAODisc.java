package dao.io;

import dao.interfaces.DAODisc;
import model.Disc;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static dao.tools.FileChecker.fileIsEmpty;
import static dao.tools.Search.similarDisc;
import static dao.tools.WorkWithStrings.ifContainsSplit;

public class IODAODisc implements DAODisc {

    private ArrayList<Disc> discs = new ArrayList<>();
    private static final String FILE_PATH = "data\\discs";

    public IODAODisc() {
        try {
            discs = readDiscs();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveDiscs(ArrayList<Disc> discs) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
            oos.writeObject(discs);
        }
    }

    private ArrayList<Disc> readDiscs() throws IOException, ClassNotFoundException {

        ArrayList<Disc> discs = new ArrayList<>();

        if(fileIsEmpty(FILE_PATH)){
            return discs;
        } else {
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                discs.addAll((ArrayList<Disc>) ois.readObject());
                return discs;
            }
        }
    }

    @Override
    public void setDisc(Disc disc) {

        int id;
        LinkedHashSet<Disc> temp = new LinkedHashSet<>(discs);

        if(discs.size() == 0){
            id = 1;
        } else {
            id = discs.get(discs.size()-1).getDiskID()+1;
        }
        disc.setDiskID(id);
        temp.add(disc);

        discs = new ArrayList<>(temp);

        try {
            saveDiscs(discs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDisc(int id) {
        discs.remove(id);
        try {
            saveDiscs(discs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Disc> getDiscs() {
        return discs;
    }

    @Override
    public Disc getDisc(int id) {
        return discs.get(id);
    }

    @Override
    public ArrayList<Disc> getDiscsOnTheDataSet(Disc disc) {

        Set<Disc> result = new LinkedHashSet<>();

        for (Disc d: discs) {
            if(similarDisc(disc, d)) {
                result.add(d);
            }
        }

        return new ArrayList<>(result);
    }

    public void loadFromFile(String url) {

        LinkedHashSet<Disc> updatedDiscs = new LinkedHashSet<>(discs);
        ArrayList<Disc> newDiscs = new ArrayList<>();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(url))){
            newDiscs = (ArrayList<Disc>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Disc disc : newDiscs){
            disc.setDiskID(disc.getDiskID()+discs.size());
            updatedDiscs.add(disc);
        }

        discs = new ArrayList<>(updatedDiscs);
        try {
            saveDiscs(discs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortDiscs() {
        Collections.sort(discs);
    }

}