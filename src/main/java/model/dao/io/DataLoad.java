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
    public static final String file = "lastdirectory";

    static {
        try {
            readLastDirectory();
            data = readData(filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readLastDirectory() {
        try (FileInputStream in = new FileInputStream(file)) {
            DataInputStream dataInputStream = new DataInputStream(in);
            filePath = dataInputStream.readUTF();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }

    private static void writeLastDirectory(String filePath) {
        DataLoad.filePath = filePath;
        try (FileOutputStream out = new FileOutputStream(file)) {
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            dataOutputStream.writeChars(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        writeData(DataLoad.filePath);
    }

    public static void writeClients(ArrayList<Client> clients) throws IOException {
        data.setClients(clients);
        writeData(DataLoad.filePath);
    }

    public static void writeData(ArrayList<Disc> discs, ArrayList<Client> clients) throws IOException {
        data.setClients(clients);
        data.setDiscs(discs);
        writeData(DataLoad.filePath);
    }

    private static void writeData(String filePath) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(data);
    }

    public static ArrayList<Disc> getDiscs() {
        return data.getDiscs();
    }

    public static ArrayList<Client> getClients() {
        return data.getClients();
    }

    public static void loadNewBase(String filePath) throws IOException, ClassNotFoundException {
        writeLastDirectory(filePath);
        data = readData(DataLoad.filePath);
    }

    public static EssenceForSave mergeBases(String filePath) throws IOException, ClassNotFoundException {

        writeData("src\\database\\backup\\dataBackup");

        return readData(filePath);
    }

}
