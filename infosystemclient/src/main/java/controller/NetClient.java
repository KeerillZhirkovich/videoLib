/**
 * Created by Keerill on 15.12.2017.
 */

package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Packet;


/**
 * Created by Keerill on 15.12.2017.
 */
public class NetClient {

  private static Socket socket;
  private static ObjectOutputStream oos;
  private static ObjectInputStream ois;
  private static boolean notReceived;

  static {
    try {
      socket = new Socket("127.0.0.1", 1111);
      oos = new ObjectOutputStream(socket.getOutputStream());
      ois = new ObjectInputStream(socket.getInputStream());
      notReceived = false;
    } catch (IOException e) {
      System.out.println("Server is not available");
    }
  }

  /**
   * @param data
   */
  public static void sendData(Packet data) {
    try {
      oos.writeObject("ready");
      oos.flush();
      oos.writeObject(data);
      oos.flush();
      //System.out.println("send");
      notReceived = true;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * @return Object data, полученная от сервера.
   */
  public static Packet getData() {
    try {
      Packet packet = (Packet) ois.readObject();
      //System.out.println("get");
      notReceived = false;
      return packet;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Packet handle(Packet packet) {
//    while (notReceived) {
//      try {
//        Thread.sleep(10);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }
    Packet gettingPacket;
    sendData(packet);
    gettingPacket = getData();
    return gettingPacket;
  }

  public static void closeConnection() {
    Packet packet = new Packet();
    packet.setCloseConnection(true);
    sendData(packet);
    getData();
  }

}
