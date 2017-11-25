package dao.io;

import dao.interfaces.DAODisc;
import model.Disc;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static dao.tools.FileChecker.fileIsEmpty;
//import static dao.tools.Search.similarDisc;
//import static dao.tools.WorkWithStrings.ifContainsSplit;

public class IODAODisc implements DAODisc {

    private ArrayList<Disc> discs;
    private static final String FILE_PATH= "data\\discs";

    public IODAODisc() throws IOException, ClassNotFoundException {
        discs = readDiscs();
    }

    public void saveChanges() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
        oos.writeObject(discs);
    }

    private ArrayList<Disc> readDiscs() throws IOException, ClassNotFoundException {

        ArrayList<Disc> discs = new ArrayList<>();

        if(fileIsEmpty(FILE_PATH)){
            return discs;
        } else {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
                discs.addAll((ArrayList<Disc>) ois.readObject());
                //sortDiscs();
                return discs;          
        }
              
    }

    @Override
    public void setDisc(Disc disc) {
        int id;
        LinkedHashSet<Disc> temp = new LinkedHashSet<>(discs);

        if(discs.isEmpty()){
            id = 1;
        } else {
            if (disc.getDiskID()==0)
                id = discs.get(discs.size()-1).getDiskID()+1;
            else
                id =disc.getDiskID();
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
        for (Disc disc : discs)
            if (disc.getDiskID() == id) {
                d = disc;
                break;
            }
        return d;
    }

    @Override
    public ArrayList<Disc> getDiscsOnTheDataSet(Disc disc) {

        Set<Disc> result = new LinkedHashSet<>();

        for (Disc d: discs) {
          //  if(similarDisc(disc, d)) {
            //    result.add(d);
          //  }
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

    }

    private void sortDiscsByID()
    {
        for (int i=0;i<discs.size();i++)
            for (int j=i+1;j<discs.size();j++)
                   if (discs.get(i).getDiskID()>discs.get(j).getDiskID())
                   {
                       int v=discs.get(i).getDiskID();
                       discs.get(i).setDiskID(discs.get(j).getDiskID());
                       discs.get(j).setDiskID(v);
                   }
    }
    
    public Disc getDiscByIndex(int index) {
        return discs.get(index);
    }
    
    public void deleteDiscByIndex(int index) {
        discs.remove(index);
    }
    
    public void setClient(int discID, int clientID)
    {
        for (int i=0;i<discs.size();i++)
            if (discs.get(i).getDiskID()==discID)
            {
                discs.get(i).setClientID(clientID);
                break;
            }
    }
}