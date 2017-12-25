package server;

import model.Client;
import model.Disc;
import sun.text.resources.JavaTimeSupplementary;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static controller.Controller.*;

/**
 * Created by Keerill on 15.12.2017.
 */
public class Server implements Runnable {

    private static int port;
    private static boolean check;
    private static int amount;
    private static boolean isStarted;
    /**
     *
     */
    private static ServerSocket server;

    static {
//        check = true;
        readPort();
//        go();
    }

    public static void decAmount() {
        amount--;
    }

    public static void setCheck(boolean check) {
        Server.check = check;
    }

    private static void readPort() {
        try {
            Reader fileReader = new FileReader("database\\port");
            StreamTokenizer streamTokenizer = new StreamTokenizer(fileReader);
            streamTokenizer.nextToken();
            port = (int) streamTokenizer.nval;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writePort(int newPort) {
        try {
            Writer fileWriter = new FileWriter("database\\port");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(newPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPort() {
        while (!isStarted) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return port;
    }

    /**
     * @param args
     */
    public static void go() {
        while (check) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        start(port);
        handle();
        end();
    }


    private static void start(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server is running on port: " + port);
            isStarted = true;
        } catch (IOException e) {
            //e.printStackTrace();
            Server.port++;
            start(Server.port);
        }
    }

    private static void handle() {
        while (true) {
            try {
                Socket client = server.accept();
                System.out.println("Connection is established");
                amount++;
                new ClientHandler(client).setIndex(amount);
            } catch (IOException e) {
                System.out.println("Server is closed!");
            }
            if (server.isClosed()) {
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void end() {
        try {
            server.close();
            isStarted = false;
            readPort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void dropClient(int index) {
//        clients.get(index).interrupt();
//        clients.remove(index);
//    }

    @Override
    public void run() {
        start(port);
        handle();
        end();
    }
}