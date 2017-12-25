package controller;

import model.Client;
import model.Disc;
import model.Packet;
import model.dao.EssenceForSave;
import model.dao.tools.CompareClients;
import model.dao.tools.CompareDiscs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import static controller.NetClient.handle;
import static model.dao.tools.FileChecker.fileIsEmpty;

/**
 * Класс - контроллер, реализующий совместную работу View и Model.
 */
public class Controller {

  private static final int FIRST_COLUMN = 0;
  private static final int SECOND_COLUMN = 1;
  private static final int THIRD_COLUMN = 2;
  private static final int FOURTH_COLUMN = 3;
  private static boolean search;
  private static boolean searchIsDone;
  private static Packet fromSearch;
  /**
   * Метод, осуществляющий заполнение таблицы NetClient для вывода на MainForm.
   *
   * @param jTable Таблица типа JTable.
   * @return Обновленную таблицу типа JTable.
   */
  public static JTable showClients(JTable jTable) {

    Packet packet = new Packet();

      packet.setMethod("showClients");
      packet = handle(packet);

    ArrayList<Client> clients = packet.getClients();
    CompareClients compareClients = new CompareClients();
    Collections.sort(clients, compareClients);
    DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();

    for (int j = 0; j < clients.size(); j++) {
      dtm.insertRow(j, new Vector(0));
      jTable.setValueAt(clients.get(j).getClientID(), j, FIRST_COLUMN);
      jTable.setValueAt(clients.get(j).getName(), j, SECOND_COLUMN);
      jTable.setValueAt(clients.get(j).getSurname(), j, THIRD_COLUMN);
      jTable.setValueAt(clients.get(j).getPhone(), j, FOURTH_COLUMN);
    }

    return jTable;
  }

  /**
   * Метод, осуществляющий заполнение таблицы Discs для вывода на MainForm.
   *
   * @param jTable Таблица типа JTable.
   * @return Обновленную таблицу типа JTable.
   */
  public static JTable showDiscs(JTable jTable, String searchString) {

    ArrayList<Disc> discs = search(searchString);
    CompareDiscs compareDiscs = new CompareDiscs();
    Collections.sort(discs, compareDiscs);
    DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();

    for (int j = 0; j < discs.size(); j++) {
      dtm.insertRow(j, new Vector(0));
      jTable.setValueAt(discs.get(j).getDiskID(), j, FIRST_COLUMN);
      jTable.setValueAt(discs.get(j).getRussianTitle(), j, SECOND_COLUMN);
    }

    return jTable;
  }

  /**
   * Метод, возвращающий экземпляр Disc по номеру.
   *
   * @param number Номер диска.
   * @return Диск типа Disc.
   */
  public static Disc getDiscByNumber(int number) {

    Packet packet = new Packet();

    packet.setMethod("getDiscByNumber");
    packet.setNumber(number);

    packet = handle(packet);

    return packet.getDisc();
  }

  /**
   * Метод, запускающий поиск объектов Disc по данным из запроса пользователя.
   *
   * @param searchString Строка, по которой осуществляется поиск.
   */
  public static ArrayList<Disc> search(String searchString) {

    Packet packet = new Packet();

    packet.setMethod("search");
    packet.setInfo(searchString);

    packet = handle(packet);

    return packet.getDiscs();
  }

  /**
   * Метод, возвращающий коллекцию Discs.
   *
   * @return Список дисков.
   */
  public static ArrayList<Disc> getDiscs() {

    Packet packet = new Packet();

    packet.setMethod("getDiscs");
    packet = handle(packet);

    return packet.getDiscs();
  }

  /**
   * Метод, возвращающий коллекцию Clients.
   *
   * @return Список клиентов из daoClients.
   */
  public static ArrayList<Client> getClients() {

    Packet packet = new Packet();

    packet.setMethod("getClients");
    packet = handle(packet);

    return packet.getClients();
  }

  /**
   * Метод, возвращающий экземпляр NetClient по номеру.
   *
   * @param id ID клиента.
   * @return Клиент типа NetClient.
   */
  public static Client getClient(int id) {

    Packet packet = new Packet();

    packet.setMethod("getClient");
    packet.setNumber(id);

    packet = handle(packet);

    return packet.getClient();
  }

  /**
   * Метод, записывающий в коллекцию передаваемый экземпляр NetClient.(новый)
   *
   * @param client Клиента типа NetClient.
   */
  public static void setClient(Client client) {

    Packet packet = new Packet();

    packet.setMethod("setClient");
    packet.setClient(client);

    packet = handle(packet);
  }

  /**
   * Метод, записывающий в коллекцию передаваемый экземпляр NetClient по номеру.
   *
   * @param discID   ID диска.
   * @param clientID ID клиента.
   */
  public static void setClient(int discID, int clientID) {

    Packet packet = new Packet();

    packet.setMethod("setClient");
    packet.setNumber(discID);
    packet.setOneMoreNumber(clientID);

    packet = handle(packet);
  }

  /**
   * Метод, удаляющий из коллекции экземпляр Disc по номеру.
   *
   * @param id ID диска.
   */
  public static void deleteDisc(int id) {

    Packet packet = new Packet();

    packet.setMethod("deleteDisc");
    packet.setNumber(id);

    packet = handle(packet);
  }

  /**
   * Метод, записывающий в коллекцию передаваемый экземпляр Disc. (новый)
   *
   * @param disc Диск типа Disc.
   */
  public static void setDisc(Disc disc) {
    Packet packet = new Packet();

    packet.setMethod("setDisc");
    packet.setDisc(disc);

    packet = handle(packet);
  }

  public static void setDiscByID(Disc disc) {
    Packet packet = new Packet();

    packet.setMethod("setDiscByID");
    packet.setDisc(disc);

    packet = handle(packet);
  }

  /**
   * Метод, возвращающий экземпляр Disc по номеру.
   *
   * @param id ID диска.
   * @return Диск по заданному ID типа Disc.
   */
  public static Disc getDisc(int id) {

    Packet packet = new Packet();

    packet.setMethod("getDisc");
    packet.setNumber(id);

    packet = handle(packet);

    return packet.getDisc();
  }

  /**
   * Метод, удаляющий экземпляр NetClient по номеру.
   *
   * @param id ID клиента.
   */
  public static void deleteClient(int id) {
    Packet packet = new Packet();

    packet.setMethod("deleteClient");
    packet.setNumber(id);

    packet = handle(packet);
  }

  /**
   * Метод, загрущающий из файла коллекцию Disc и NetClient.
   *
   * @param filePath Путь к файлу.
   */
  public static void openBase(String filePath) {
    Packet packet = new Packet();

    packet.setMethod("openBase");
    packet.setData(openFile(filePath));
    packet = handle(packet);
  }


  /**
   * Метод, осуществляющий слияние двух баз.
   *
   * @param filePath Путь к файлу.
   */
  public static void mergeBase(String filePath) {
    Packet packet = new Packet();

    packet.setMethod("mergeBase");
    packet.setData(openFile(filePath));
    packet = handle(packet);
  }


  private static EssenceForSave openFile(String filePath) {
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


//    private static boolean checkNotEmpty(Disc disc) {
//        boolean check = true;
//        if ("".equals(disc.getOriginalTitle()) && "".equals(disc.getCountry()) && "".equals(disc.getDescription()) && "".equals(disc.getDirector()) && disc.getClientID() == 0 && disc.getDuration() == 0 && "".equals(disc.getGenre()) && "".equals(disc.getLanguages()) && "".equals(disc.getOriginalTitle()) && disc.getRating() == 0 && disc.getReleaseYear() == 0 && "".equals(disc.getRussianTitle())) {
//            check = false;
//        }
//        return check;
//    }
}
