/**
 * Created by Keerill on 15.12.2017.
 */

package controller;

import java.io.*;
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
  private static int port;
  private static String ip;

  public static void setPort(int port) {
    NetClient.port = port;
  }

  public static void setIp(String ip) {
    NetClient.ip = ip;
  }

  private static void readInfo() {
    try {
      Reader fileReader = new FileReader("serverinfo\\serverinfo");
      StreamTokenizer streamTokenizer = new StreamTokenizer(fileReader);
      streamTokenizer.nextToken();
      ip = streamTokenizer.sval;
      streamTokenizer.nextToken();
      port = (int) streamTokenizer.nval;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeInfo(String newIp, int newPort) {
    try {
      Writer fileWriter = new FileWriter("serverinfo\\serverinfo");
      PrintWriter printWriter = new PrintWriter(fileWriter);
      printWriter.print(newIp);
      printWriter.print(" ");
      printWriter.print(newPort);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static {
      readInfo();
  }

  public static String openConnection() {
    try {
      socket = new Socket(ip, port);
      oos = new ObjectOutputStream(socket.getOutputStream());
      ois = new ObjectInputStream(socket.getInputStream());
      System.out.println("Success");
      return "Success";
    } catch (IOException e) {
      System.out.println("Server is not available");
      return "Server is not available";
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
    try {
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
