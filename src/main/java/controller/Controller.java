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
    private static ArrayList<Disc> discs = daoDiscs.getDiscs();

    private static final int firstColumn = 0;
    private static final int secondColumn = 1;
    private static final int thirdColumn = 2;
    private static final int fourthColumn = 3;

    /**
     * Метод, осуществляющий заполнение таблицы Client для вывода на MainForm.
     */
    public static JTable ShowClients(JTable jTable) {

        ArrayList<Client> clients = daoClients.getClients();

        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        for (int j = 0; j < clients.size(); j++) {
            dtm.insertRow(j, new Vector(0));
            jTable.setValueAt(clients.get(j).getClientID(), j, firstColumn);
            jTable.setValueAt(clients.get(j).getName(), j, secondColumn);
            jTable.setValueAt(clients.get(j).getSurname(), j, thirdColumn);
            jTable.setValueAt(clients.get(j).getPhone(), j, fourthColumn);
        }

        return jTable;
    }

    /**
     * Метод, осуществляющий заполнение таблицы Discs для вывода на MainForm.
     */
    public static JTable ShowDiscs(JTable jTable) {

        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        for (int j = 0; j < discs.size(); j++) {
            dtm.insertRow(j, new Vector(0));
            jTable.setValueAt(discs.get(j).getDiskID(), j, firstColumn);
            jTable.setValueAt(discs.get(j).getRussianTitle(), j, secondColumn);
        }

        return jTable;
    }

    /**
     * Метод, осуществляющий запись в файл текущих данных.
     */
    public static void saveChanges() {
        writeData(daoDiscs.getDiscs(), daoClients.getClients());
    }

    /**
     * Метод, возвращающий экземпляр Disc по номеру.
     */
    public static Disc getDiscByNumber(int number) {
        return daoDiscs.getDisc(number);
    }

    /**
     * Метод, запускающий поиск объектов Disc по данным из запроса пользователя.
     */
    public static void Search(String searchString) {
        discs = daoDiscs.getDiscsOnTheDataSet(searchString);
    }

    /**
     * Метод, возвращающий коллекцию Discs.
     */
    public static ArrayList<Disc> getDiscs() {
        return discs;
    }

    /**
     * Метод, возвращающий коллекцию Clients.
     */
    public static ArrayList<Client> getClients() {
        return daoClients.getClients();
    }

    /**
     * Метод, возвращающий экземпляр Client по номеру.
     */
    public static Client getClient(int id) {
        return daoClients.getClient(id);
    }

    /**
     * Метод, записывающий в коллекцию передаваемый экземпляр Client.(новый)
     */
    public static void setClient(Client client) {
        daoClients.setClient(client);
    }

    /**
     * Метод, записывающий в коллекцию передаваемый экземпляр Client по номеру.
     */
    public static void setClient(int discID, int clientID) {
        daoDiscs.getDisc(discID).setClientID(clientID);
        discs = daoDiscs.getDiscs();
    }

    /**
     * Метод, удаляющий из коллекции экземпляр Disc по номеру.
     */
    public static void deleteDisc(int id) {
        daoDiscs.deleteDisc(id);
        discs = daoDiscs.getDiscs();
    }

    /**
     * Метод, записывающий в коллекцию передаваемый экземпляр Disc. (новый)
     */
    public static void setDisc(Disc disc) {
        daoDiscs.setDisc(disc);
        discs = daoDiscs.getDiscs();
    }

    /**
     * Метод, возвращающий экземпляр Disc по номеру.
     */
    public static Disc getDisc(int id) {
        return daoDiscs.getDisc(id);
    }

    /**
     * Метод, удаляющий экземпляр Client по номеру.
     */
    public static void deleteClient(int id) {
        daoClients.deleteClient(id);
    }

    /**
     * Метод, загрущающий из файла коллекцию Disc и Client.
     */
    public static void openBase(String filePath) {
        loadNewBase(filePath);
        daoDiscs = new IODAODisc();
        daoClients = new IODAOClient();
        discs = daoDiscs.getDiscs();
    }


    /**
     * Метод, осуществляющий слияние двух баз.
     */
    public static void mergeBase(String filePath) {

        EssenceForSave newData = mergeBases(filePath);

        daoDiscs.updateDiscs(newData.getDiscs());
        daoClients.updateClients(newData.getClients());

        discs = daoDiscs.getDiscs();
    }

//    private static boolean checkNotEmpty(Disc disc) {
//        boolean check = true;
//        if ("".equals(disc.getOriginalTitle()) && "".equals(disc.getCountry()) && "".equals(disc.getDescription()) && "".equals(disc.getDirector()) && disc.getClientID() == 0 && disc.getDuration() == 0 && "".equals(disc.getGenre()) && "".equals(disc.getLanguages()) && "".equals(disc.getOriginalTitle()) && disc.getRating() == 0 && disc.getReleaseYear() == 0 && "".equals(disc.getRussianTitle())) {
//            check = false;
//        }
//        return check;
//    }
}
