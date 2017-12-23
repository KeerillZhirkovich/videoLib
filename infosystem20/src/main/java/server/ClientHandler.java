package server;

import model.Packet;
import model.dao.EssenceForSave;

import java.io.*;
import java.net.Socket;

import static controller.Controller.*;

/**
 * Created by Keerill on 16.12.2017.
 */
public class ClientHandler extends Thread {

    private final Socket client;
    private String method;
    private String check;
    private static final String showClients = "showClients";
    private static final String showDiscs = "showDiscs";
    private static final String saveChanges = "saveChanges";
    private static final String getDiscByNumber = "getDiscByNumber";
    private static final String search = "search";
    private static final String getDiscs = "getDiscs";
    private static final String getClients = "getClients";
    private static final String getClient = "getClient";
    private static final String setClient = "setClient";
    private static final String setClientByID = "setClientByID";
    private static final String deleteDisc = "deleteDisc";
    private static final String setDisc = "setDisc";
    private static final String getDisc = "getDisc";
    private static final String deleteClient = "deleteClient";
    private static final String openBase = "openBase";
    private static final String mergeBase = "mergeBase";
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Packet packet;

    public ClientHandler(Socket client) {
        this.client = client;
        try {
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    private void sendData(Object data, ObjectOutputStream objectOutputStream,
                          ObjectInputStream objectInputStream) {
        try {
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Packet handler(Packet packet) {
        if (packet.getMethod().contains(showClients)) {
            packet.setClients(getClients());
            return packet;
        }


        if (packet.getMethod().contains(getDiscs)) {
            packet.setDiscs(getDiscs());
            return packet;
        }

        if (packet.getMethod().contains(getClients)) {
            packet.setClients(getClients());
            return packet;
        }


        if (packet.getMethod().contains(getDiscByNumber)) {
            packet.setDisc(getDiscByNumber(packet.getNumber()));
            return packet;
        }

        if (packet.getMethod().contains(search)) {
            packet.setDiscs(search(packet.getInfo()));
            return packet;
        }

        if (packet.getMethod().contains(getClient)) {
            packet.setClient(getClient(packet.getNumber()));
            return packet;
        }

        if (packet.getMethod().contains(setClient)) {
            setClient(packet.getClient());
            return packet;
        }

        if (packet.getMethod().contains(setClientByID)) {
            setClient(packet.getNumber(), packet.getOneMoreNumber());
            return packet;
        }

        if (packet.getMethod().contains(deleteDisc)) {
            deleteDisc(packet.getNumber());
            return packet;
        }

        if (packet.getMethod().contains(setDisc)) {
            setDisc(packet.getDisc());
            return packet;
        }

        if (packet.getMethod().contains(getDisc)) {
            packet.setDisc(getDisc(packet.getNumber()));
            return packet;
        }

        if (packet.getMethod().contains(deleteClient)) {
            deleteClient(packet.getNumber());
            return packet;
        }

        if (packet.getMethod().contains(openBase)) {
            EssenceForSave data = packet.getData();
            openBase(data);
            return packet;
        }

        if (packet.getMethod().contains(mergeBase)) {
            EssenceForSave data = packet.getData();
            mergeBase(data);
            return packet;
        }

        if (packet.isCloseConnection()) {
            stop();
        }

        return packet;
    }


    @Override
    public void run() {
        while (true) {
            try {
                check = (String) ois.readObject();
                if (check.contains("ready")) {
                    packet = (Packet) ois.readObject();
                    //System.out.println("get");
                    packet = handler(packet);
                    oos.writeObject(packet);
                    oos.flush();
                    //System.out.println("send");
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
