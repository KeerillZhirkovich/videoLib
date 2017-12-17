/**
 * Created by Keerill on 15.12.2017.
 */

package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.EssenceForSend;


/**
 * Created by Keerill on 15.12.2017.
 */
public class RunClient implements Runnable {

  private static Socket socket;
  private static ObjectOutputStream oos;
  private static ObjectInputStream ois;
  private static Object objectToSend;
  private static EssenceForSend objectToGet;
  private static boolean send = false;
  private static boolean get = false;
  private static String check;

  public RunClient() {
    try {
      socket = new Socket("127.0.0.1", 1111);
      oos = new ObjectOutputStream(socket.getOutputStream());
      ois = new ObjectInputStream(socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param data
   */
  public static void sendData(Object data) {
    objectToSend = data;
    send = true;
  }

  /**
   * @return Object data, полученная от сервера.
   */
  public static void getData() {
    objectToGet = null;
    get = true;
  }

  public static EssenceForSend handle(EssenceForSend essenceForSend) {
    int i = 0;
    sendData(essenceForSend);
    getData();
    while (get) {
      System.out.print("");
    }
    System.out.println();
    return objectToGet;
  }

  /**
   * Running server.
   */
  @Override
  public void run() {
    while (true) {
      try {
        if (send) {
          oos.writeObject("ready");
          System.out.println("ready");
          oos.flush();
          oos.writeObject(objectToSend);
          oos.flush();
          send = false;
        }
        if (get) {
          check = (String) ois.readObject();
          System.out.println(check + " check");
          if (check.contains("ready")) {
            objectToGet = (EssenceForSend) ois.readObject();
            get = false;
          }
          check = "";
        }
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
