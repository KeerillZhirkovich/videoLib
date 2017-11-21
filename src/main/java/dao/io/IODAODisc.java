package dao.io;

import dao.interfaces.DAODisc;
import model.Disc;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import static dao.tools.WorkWithStrings.ifContainsSplit;

public class IODAODisc implements DAODisc {

    private ArrayList<Disc> discs = new ArrayList<>();

    public IODAODisc() {
        try {
            discs = readDiscs();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveDiscs(ArrayList<Disc> discs) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data\\discs"))){
            oos.writeObject(discs);
        }
    }

    private ArrayList<Disc> readDiscs() throws IOException, ClassNotFoundException {
        ArrayList<Disc> discs = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data\\discs"))){
            discs.addAll((ArrayList<Disc>) ois.readObject());
            return discs;
        }
    }

    @Override
    public void setDisc(Disc disc) {

        int id;

        if(discs.size() == 0){
            id = 1;
        } else {
            id = discs.get(discs.size()-1).getDiskID()+1;
        }
        disc.setDiskID(id);
        discs.add(disc);
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
    public ArrayList<Disc> getDiscsOnTheDataSet(String[] data) {

        Set<Disc> result = new LinkedHashSet<>();

        for(int i = 0; i < 9; i++){
            data[i] = data[i].trim();
        }

        for (Disc disc: discs) {
            boolean b = !data[4].isEmpty() && (Integer.parseInt(data[4]) == disc.getReleaseYear());
            b = b && (!data[5].isEmpty() && ((Double.parseDouble(data[5]) <= disc.getRating())));
            b = b && !data[0].isEmpty() && ifContainsSplit(disc.getOriginalTitle(), data[0]);
            b = b && !data[1].isEmpty() && ifContainsSplit(disc.getRussianTitle(), data[1]);
            b = b && !data[3].isEmpty() && ifContainsSplit(disc.getGenre(), data[3]);
            b = b && !data[8].isEmpty() && ifContainsSplit(disc.getActors(), data[8]);
            b = b && !data[2].isEmpty() && ifContainsSplit(disc.getDirector(), data[2]);
            b = b && !data[6].isEmpty() && ifContainsSplit(disc.getLanguages(), data[6]);
            b = b && !data[7].isEmpty() && ifContainsSplit(disc.getCountry(), data[7]);
            if(b){
                result.add(disc);
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

}