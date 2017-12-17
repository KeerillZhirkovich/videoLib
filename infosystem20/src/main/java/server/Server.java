package server;

import model.Client;
import model.Disc;
import sun.text.resources.JavaTimeSupplementary;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static controller.Controller.*;

/**
 * Created by Keerill on 15.12.2017.
 */
public class Server {

    /**
     *
     */
    private static ServerSocket server;


    /**
     * @param args
     */
    public static void main(String[] args) {
        start();
        handle();
        end();
    }


    private static void start() {
        try {
            server = new ServerSocket(1111);
            System.out.println("Server is running ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        while (true) {
            try {
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

    private static void end() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}