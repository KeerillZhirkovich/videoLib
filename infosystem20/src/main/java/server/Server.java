package server;

import model.Client;
import model.Disc;
import sun.text.resources.JavaTimeSupplementary;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static controller.Controller.*;

/**
 * Created by Keerill on 15.12.2017.
 */
public class Server {

    private static int port;

    /**
     *
     */
    private static ServerSocket server;


    static {
        readPort();
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
        return port;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        start(port);
        handle();
        end();
    }


    private static void start(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server is running on port: " + port);
        } catch (IOException e) {
            //e.printStackTrace();
            Server.port++;
            start(Server.port);
        }
    }

    private static void handle() {
        while (true) {
            try {
                if (server.isClosed()) {
                    break;
                }
                Socket client = server.accept();
                System.out.println("Connection is established");
                new ClientHandler(client);
            } catch (IOException e) {
                e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}