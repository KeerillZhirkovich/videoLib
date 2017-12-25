/**
 * Created by Keerill on 15.12.2017.
 */
package controller;

import java.io.*;
import java.net.InetAddress;
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

  static {
    readInfo();
  }
  
  public static void setPort(int port) {
    NetClient.port = port;
  }

  public static void setIp(String ip) {
    NetClient.ip = ip;
  }

  public static int getPort() {
    return port;
  }

  public static String getIp() {
    return ip;
  }

 private static void readInfo() {
    try {
      String[] buffer = new String[2];
      InputStream in = new FileInputStream("serverinfo\\serverinfo");
      DataInputStream dataInputStream = new DataInputStream(in);
      ip = dataInputStream.readLine();
      buffer = ip.split(" ");
      ip = buffer[0];
      port = Integer.parseInt(buffer[1]);
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
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static {
    readInfo();
  }

  public static String openConnection() {
    try {
      socket = new Socket(InetAddress.getByName(ip), port);
      oos = new ObjectOutputStream(socket.getOutputStream());
      ois = new ObjectInputStream(socket.getInputStream());
      System.out.println("Success");
      return "Success";
    } catch (IOException e) {
      //e.printStackTrace();
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
    packet.setMethod("Close");
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
