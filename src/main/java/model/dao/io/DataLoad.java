package model.dao.io;

import model.Client;
import model.Disc;
import model.dao.EssenceForSave;

import java.io.*;
import java.util.ArrayList;

import static model.dao.tools.FileChecker.fileIsEmpty;


public class DataLoad {

    private static final String filePath = "src\\database\\data";
    private static String loadPath;
    private static EssenceForSave data;

    static {
        try {
            data = readData(filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setLoadPath(String filePath) {
        DataLoad.loadPath = filePath;
    }

    public static EssenceForSave readData(String filePath) throws IOException, ClassNotFoundException {

        EssenceForSave data = new EssenceForSave();

        if(fileIsEmpty(filePath)){
            return data;
        } else {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            data = (EssenceForSave) ois.readObject();
            return data;
        }

    }


    public static void writeDiscs(ArrayList<Disc> discs) throws IOException {
        data.setDiscs(discs);
        writeData();
    }

    public static void writeClients(ArrayList<Client> clients) throws IOException {
        data.setClients(clients);
        writeData();
    }

    public static void writeData(ArrayList<Disc> discs, ArrayList<Client> clients) throws IOException {
        data.setClients(clients);
        data.setDiscs(discs);
        writeData();
    }

    private static void writeData() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(data);
    }

    public static ArrayList<Disc> getDiscs() {
        return data.getDiscs();
    }

    public static ArrayList<Client> getClients() {
        return data.getClients();
    }
}
