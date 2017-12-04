package model.dao.io;

import model.Client;
import model.Disc;
import model.dao.EssenceForSave;

import java.io.*;
import java.util.ArrayList;

import static model.dao.tools.FileChecker.fileIsEmpty;

public class DataLoad {

    private static String filePath;
    private static EssenceForSave data;
    public static final String file = "src\\main\\java\\model\\dao\\io\\lastdirectory";

    static {
        readLastDirectory();
        data = readData(filePath);
    }

    private static void readLastDirectory() {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            filePath = (String) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeLastDirectory(String filePath) {
        DataLoad.filePath = filePath;
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EssenceForSave readData(String filePath) {

        EssenceForSave data = new EssenceForSave();

        if (fileIsEmpty(filePath)) {
            return data;
        } else {
            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                ObjectInputStream ois = new ObjectInputStream(fileInputStream);
                data = (EssenceForSave) ois.readObject();
                ois.close();
                return data;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }
    }

    public static void writeDiscs(ArrayList<Disc> discs) {
        data.setDiscs(discs);
        writeData(DataLoad.filePath);
    }

    public static void writeClients(ArrayList<Client> clients) {
        data.setClients(clients);
        writeData(DataLoad.filePath);
    }

    public static void writeData(ArrayList<Disc> discs, ArrayList<Client> clients) {
        data.setClients(clients);
        data.setDiscs(discs);
        writeData(DataLoad.filePath);
    }

    private static void writeData(String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Disc> getDiscs() {
        return data.getDiscs();
    }

    public static ArrayList<Client> getClients() {
        return data.getClients();
    }

    public static void loadNewBase(String filePath) {
        writeLastDirectory(filePath);
        data = readData(DataLoad.filePath);
    }

    public static EssenceForSave mergeBases(String filePath) {

        writeData("src\\database\\backup\\dataBackup");

        return readData(filePath);
    }
}
