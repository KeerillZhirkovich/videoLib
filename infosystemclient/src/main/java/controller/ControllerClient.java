package controller;

import model.Client;
import model.Disc;
import model.EssenceForSend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

import static controller.RunClient.getData;
import static controller.RunClient.handle;
import static controller.RunClient.sendData;

/**
 * Класс - контроллер, реализующий совместную работу View и Model.
 */
public class ControllerClient {

  private static final int FIRST_COLUMN = 0;
  private static final int SECOND_COLUMN = 1;
  private static final int THIRD_COLUMN = 2;
  private static final int FOURTH_COLUMN = 3;

  /**
   * Метод, осуществляющий заполнение таблицы RunClient для вывода на MainForm.
   *
   * @param jTable Таблица типа JTable.
   * @return Обновленную таблицу типа JTable.
   */
  public static JTable showClients(JTable jTable) {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("showClients");
    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);

    ArrayList<Client> clients = essenceForSend.getClients();
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

  public static JTable showDiscs(JTable jTable) {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("showDiscs");
    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);

    ArrayList<Disc> discs = essenceForSend.getDiscs();
    DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();

    for (int j = 0; j < discs.size(); j++) {
      dtm.insertRow(j, new Vector(0));
      jTable.setValueAt(discs.get(j).getDiskID(), j, FIRST_COLUMN);
      jTable.setValueAt(discs.get(j).getRussianTitle(), j, SECOND_COLUMN);
     }

     return jTable;
  }

  /**
   * Метод, осуществляющий запись в файл текущих данных.
   */
  public static void saveChanges() {
    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("saveChanges");

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);
  }

  /**
   * Метод, возвращающий экземпляр Disc по номеру.
   *
   * @param number Номер диска.
   * @return Диск типа Disc.
   */
  public static Disc getDiscByNumber(int number) {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("getDiscByNumber");
    essenceForSend.setNumber(number);

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);

    return essenceForSend.getDisc();
  }

  /**
   * Метод, запускающий поиск объектов Disc по данным из запроса пользователя.
   *
   * @param searchString Строка, по которой осуществляется поиск.
   */
  public static void search(String searchString) {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("search");
    essenceForSend.setInfo(searchString);

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);

  }

  /**
   * Метод, возвращающий коллекцию Discs.
   *
   * @return Список дисков.
   */
  public static ArrayList<Disc> getDiscs() {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("getDiscs");
    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);

    return essenceForSend.getDiscs();
  }

  /**
   * Метод, возвращающий коллекцию Clients.
   *
   * @return Список клиентов из daoClients.
   */
  public static ArrayList<Client> getClients() {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("getClients");
    sendData(essenceForSend);
    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);

    return essenceForSend.getClients();
  }

  /**
   * Метод, возвращающий экземпляр RunClient по номеру.
   *
   * @param id ID клиента.
   * @return Клиент типа RunClient.
   */
  public static Client getClient(int id) {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("getClient");
    essenceForSend.setNumber(id);

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);

    return essenceForSend.getClient();
  }

  /**
   * Метод, записывающий в коллекцию передаваемый экземпляр RunClient.(новый)
   *
   * @param client Клиента типа RunClient.
   */
  public static void setClient(Client client) {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("setClient");
    essenceForSend.setClient(client);

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);
  }

  /**
   * Метод, записывающий в коллекцию передаваемый экземпляр RunClient по номеру.
   *
   * @param discID   ID диска.
   * @param clientID ID клиента.
   */
  public static void setClient(int discID, int clientID) {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("setClient");
    essenceForSend.setNumber(discID);
    essenceForSend.setOneMoreNumber(clientID);

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);
  }

  /**
   * Метод, удаляющий из коллекции экземпляр Disc по номеру.
   *
   * @param id ID диска.
   */
  public static void deleteDisc(int id) {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("deleteDisc");
    essenceForSend.setNumber(id);

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);
  }

  /**
   * Метод, записывающий в коллекцию передаваемый экземпляр Disc. (новый)
   *
   * @param disc Диск типа Disc.
   */
  public static void setDisc(Disc disc) {
    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("setDisc");
    essenceForSend.setDisc(disc);

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);
  }

  /**
   * Метод, возвращающий экземпляр Disc по номеру.
   *
   * @param id ID диска.
   * @return Диск по заданному ID типа Disc.
   */
  public static Disc getDisc(int id) {

    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("getDisc");
    essenceForSend.setNumber(id);

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);

    return essenceForSend.getDisc();
  }

  /**
   * Метод, удаляющий экземпляр RunClient по номеру.
   *
   * @param id ID клиента.
   */
  public static void deleteClient(int id) {
    EssenceForSend essenceForSend = new EssenceForSend();

    essenceForSend.setMethod("deleteClient");
    essenceForSend.setNumber(id);

    //Handler handler = new Handler(essenceForSend);
    essenceForSend = handle(essenceForSend);
  }

  /**
   * Метод, загрущающий из файла коллекцию Disc и RunClient.
   *
   * @param filePath Путь к файлу.
   */
  public static void openBase(String filePath) {
//    sendData("openBase");
//    getData();
//    sendData(filePath);
  }


  /**
   * Метод, осуществляющий слияние двух баз.
   *
   * @param filePath Путь к файлу.
   */
  public static void mergeBase(String filePath) {
//    sendData("mergeBase");
//    getData();
//    sendData(filePath);
  }


//    private static boolean checkNotEmpty(Disc disc) {
//        boolean check = true;
//        if ("".equals(disc.getOriginalTitle()) && "".equals(disc.getCountry()) && "".equals(disc.getDescription()) && "".equals(disc.getDirector()) && disc.getClientID() == 0 && disc.getDuration() == 0 && "".equals(disc.getGenre()) && "".equals(disc.getLanguages()) && "".equals(disc.getOriginalTitle()) && disc.getRating() == 0 && disc.getReleaseYear() == 0 && "".equals(disc.getRussianTitle())) {
//            check = false;
//        }
//        return check;
//    }
}
