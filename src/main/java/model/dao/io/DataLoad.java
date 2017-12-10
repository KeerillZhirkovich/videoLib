package model.dao.io;

import model.Client;
import model.Disc;
import model.dao.EssenceForSave;

import java.io.*;
import java.util.ArrayList;

import static model.dao.tools.FileChecker.fileIsEmpty;

/**
 * Класс, реализующий работу с файлами.
 */
public class DataLoad {

    private static String filePath;
    private static EssenceForSave data;
    public static final String file = "src\\main\\java\\model\\dao\\io\\lastdirectory";

    static {
        readLastDirectory();
        data = readData(filePath);
    }

    /**
     * Метод, считывающий из файл lastdirectory расположение последней используемой базы.
     */
    private static void readLastDirectory() {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            filePath = (String) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод, записывающий в файл lastdirectory расположение последней используемой базы.
     */
    public static void writeLastDirectory(String filePath) {
        DataLoad.filePath = filePath;
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод, считывающий базу из файла.
     */
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

    /**
     * Метод, записывающий изменения Discs в файл базы.
     */
    public static void writeDiscs(ArrayList<Disc> discs) {
        data.setDiscs(discs);
        writeData(DataLoad.filePath);
    }

    /**
     * Метод, записывающий изменения Clients в файл базы.
     */
    public static void writeClients(ArrayList<Client> clients) {
        data.setClients(clients);
        writeData(DataLoad.filePath);
    }

    /**
     * Метод, записывающий изменения Discs и Clients в файл базы.
     */
    public static void writeData(ArrayList<Disc> discs, ArrayList<Client> clients) {
        data.setClients(clients);
        data.setDiscs(discs);
        writeData(DataLoad.filePath);
    }

    /**
     * Метод, осуществляющий непосредственное сохранение базы в файл.
     */
    private static void writeData(String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод, возвращающий коллекцию Disc.
     */
    public static ArrayList<Disc> getDiscs() {
        return data.getDiscs();
    }

    /**
     * Метод, возвращающий коллекцию Client.
     */
    public static ArrayList<Client> getClients() {
        return data.getClients();
    }

    /**
     * Метод, загружающий новую базу из выбранной директории.
     */
    public static void loadNewBase(String filePath) {
        writeLastDirectory(filePath);
        data = readData(DataLoad.filePath);
    }

    /**
     * Метод, осуществляющий слияние двух баз.
     */
    public static EssenceForSave mergeBases(String filePath) {

        writeData("src\\database\\backup\\dataBackup");

        return readData(filePath);
    }
}
