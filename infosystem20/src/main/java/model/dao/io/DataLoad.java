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
public class DataLoad implements Serializable {

    private static boolean isRead;
    private static boolean isWrite;

    private static String filePath;
    //private static EssenceForSave data;
    public static final String file = "src\\main\\java\\model\\dao\\io\\lastdirectory";

    static {
        readLastDirectory();
        //data = readData(filePath);
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
     * @param filePath
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
     * @param filePath
     * @return
     */
    public static EssenceForSave readData() {
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
     * @param discs
     */
    public static boolean writeDiscs(ArrayList<Disc> discs) {
        EssenceForSave data;
        data = readData();
        data.setDiscs(discs);
        writeData(filePath, data);
        return true;
    }


    /**
     * Метод, записывающий изменения Clients в файл базы.
     * @param clients
     */
    public static boolean writeClients(ArrayList<Client> clients) {
        EssenceForSave data;
        data = readData();
        data.setClients(clients);
        writeData(filePath, data);
        return true;
    }


    /**
     * Метод, записывающий изменения Discs и Clients в файл базы.
     * @param discs
     * @param clients
     */
    public static boolean writeData(ArrayList<Disc> discs, ArrayList<Client> clients) {
        EssenceForSave data;
        data = readData();
        data.setClients(clients);
        data.setDiscs(discs);
        writeData(filePath, data);
        return true;
    }


    /**
     * Метод, осуществляющий непосредственное сохранение базы в файл.
     * @param filePath
     */
    private static boolean writeData(String filePath, EssenceForSave data) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * Метод, возвращающий коллекцию Disc.
     * @return
     */
    public static ArrayList<Disc> getDiscs() {
        EssenceForSave data;
        data = readData();
        return data.getDiscs();
    }

    /**
     * Метод, возвращающий коллекцию Client.
     * @return ArrayList of Client
     */
    public static ArrayList<Client> getClients() {
        EssenceForSave data;
        data = readData();
        return data.getClients();
    }

    /**
     * Метод, загружающий новую базу из выбранной директории.
     * @return boolean checker
     */
    public static boolean backupBase() {
        EssenceForSave data = readData();
        data = readData();
        writeData("database\\backup\\dataBackup", data);
        return true;
    }

}
