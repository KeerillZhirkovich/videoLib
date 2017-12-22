package controller;

import model.Client;
import model.Disc;
import model.dao.EssenceForSave;
import model.dao.io.IODAOClient;
import model.dao.io.IODAODisc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

import static model.dao.io.DataLoad.loadNewBase;
import static model.dao.io.DataLoad.mergeBases;
import static model.dao.io.DataLoad.writeData;

/**
 * Класс - контроллер, реализующий совместную работу View и Model.
 */
public class Controller {

    private static IODAOClient daoClients = new IODAOClient();
    private static IODAODisc daoDiscs = new IODAODisc();
    //private static ArrayList<Disc> discs = daoDiscs.getDiscs();

    /**
     * Метод, осуществляющий заполнение таблицы Client для вывода на MainForm.
     * @param jTable Таблица типа JTable.
     * @return Обновленную таблицу типа JTable.
     */
    public static synchronized ArrayList<Client> showClients() {

        ArrayList<Client> clients = daoClients.getClients();

        return clients;
    }

    /**
     * Метод, осуществляющий заполнение таблицы Discs для вывода на MainForm.
     * @param jTable Таблица типа JTable.
     * @return Обновленную таблицу типа JTable.
     */
    public static synchronized ArrayList<Disc> showDiscs(String searchString) {
        daoDiscs.getDiscsOnTheDataSet(searchString);
        return daoDiscs.getDiscs();
    }

    /**
     * Метод, осуществляющий запись в файл текущих данных.
     */
    public static synchronized void saveChanges() {
        writeData(daoDiscs.getDiscs(), daoClients.getClients());
    }

    /**
     * Метод, возвращающий экземпляр Disc по номеру.
     * @param number Номер диска.
     * @return Диск типа Disc.
     */
    public static synchronized Disc getDiscByNumber(int number) {
        return daoDiscs.getDisc(number);
    }

    /**
     * Метод, запускающий поиск объектов Disc по данным из запроса пользователя.
     * @param searchString Строка, по которой осуществляется поиск.
     * @return 
     */
    public static synchronized ArrayList<Disc> search(String searchString) {
        //discs = daoDiscs.getDiscsOnTheDataSet(searchString);
        return daoDiscs.getDiscsOnTheDataSet(searchString);
    }

    /**
     * Метод, возвращающий коллекцию Discs.
     * @return Список дисков.
     */
    public static synchronized ArrayList<Disc> getDiscs() {
        return daoDiscs.getDiscs();
    }

    /**
     * Метод, возвращающий коллекцию Clients.
     * @return Список клиентов из daoClients.
     */
    public static synchronized ArrayList<Client> getClients() {
        return daoClients.getClients();
    }

    /**
     * Метод, возвращающий экземпляр Client по номеру.
     * @param id ID клиента.
     * @return Клиент типа Client.
     */
    public static synchronized Client getClient(int id) {
        return daoClients.getClient(id);
    }

    /**
     * Метод, записывающий в коллекцию передаваемый экземпляр Client.(новый)
     * @param client Клиента типа Client.
     */
    public static synchronized void setClient(Client client) {
        daoClients.setClient(client);
    }

    /**
     * Метод, записывающий в коллекцию передаваемый экземпляр Client по номеру.
     * @param discID ID диска.
     * @param clientID ID клиента.
     */
    public static synchronized void setClient(int discID, int clientID) {
        daoDiscs.getDisc(discID).setClientID(clientID);
    }

    /**
     * Метод, удаляющий из коллекции экземпляр Disc по номеру.
     * @param id ID диска.
     */
    public static synchronized void deleteDisc(int id) {
        daoDiscs.deleteDisc(id);
    }

    /**
     * Метод, записывающий в коллекцию передаваемый экземпляр Disc. (новый)
     * @param disc Диск типа Disc.
     */
    public static synchronized void setDisc(Disc disc) {
        daoDiscs.setDisc(disc);
    }

    /**
     * Метод, возвращающий экземпляр Disc по номеру.
     * @param id ID диска.
     * @return Диск по заданному ID типа Disc.
     */
    public static synchronized Disc getDisc(int id) {
        return daoDiscs.getDisc(id);
    }

    /**
     * Метод, удаляющий экземпляр Client по номеру.
     * @param id ID клиента.
     */
    public static synchronized void deleteClient(int id) {
        daoClients.deleteClient(id);
    }

    /**
     * Метод, загрущающий из файла коллекцию Disc и Client.
     * @param filePath Путь к файлу.
     */
    public static synchronized void openBase(String filePath) {
        loadNewBase(filePath);
        daoDiscs = new IODAODisc();
        daoClients = new IODAOClient();
    }


    /**
     * Метод, осуществляющий слияние двух баз.
     * @param filePath Путь к файлу.
     */
    public static synchronized void mergeBase(String filePath) {

        EssenceForSave newData = mergeBases(filePath);

        daoDiscs.updateDiscs(newData.getDiscs());
        daoClients.updateClients(newData.getClients());
    }

//    private static boolean checkNotEmpty(Disc disc) {
//        boolean check = true;
//        if ("".equals(disc.getOriginalTitle()) && "".equals(disc.getCountry()) && "".equals(disc.getDescription()) && "".equals(disc.getDirector()) && disc.getClientID() == 0 && disc.getDuration() == 0 && "".equals(disc.getGenre()) && "".equals(disc.getLanguages()) && "".equals(disc.getOriginalTitle()) && disc.getRating() == 0 && disc.getReleaseYear() == 0 && "".equals(disc.getRussianTitle())) {
//            check = false;
//        }
//        return check;
//    }
}
