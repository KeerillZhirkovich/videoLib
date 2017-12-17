package server;

import model.EssenceForSend;

import java.io.*;
import java.net.Socket;

import static controller.Controller.*;

/**
 * Created by Keerill on 16.12.2017.
 */
public class ClientHandler extends Thread {

    private final Socket client;
//    private Socket socket;
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
    private EssenceForSend essenceForSend;

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
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private EssenceForSend handler(EssenceForSend essenceForSend) {
        if (essenceForSend.getMethod().contains(showClients)) {
            essenceForSend.setClients(showClients());
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(showDiscs)) {
            essenceForSend.setDiscs(showDiscs());
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(getDiscs)) {
            essenceForSend.setDiscs(getDiscs());
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(getClients)) {
            essenceForSend.setClients(getClients());
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(saveChanges)) {
            saveChanges();
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(getDiscByNumber)) {
            essenceForSend.setDisc(getDiscByNumber(essenceForSend.getNumber()));
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(search)) {
            search(essenceForSend.getInfo());
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(getClient)) {
            essenceForSend.setClient(getClient(essenceForSend.getNumber()));
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(setClient)) {
            setClient(essenceForSend.getClient());
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(setClientByID)) {
            setClient(essenceForSend.getNumber(), essenceForSend.getOneMoreNumber());
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(deleteDisc)) {
            deleteDisc(essenceForSend.getNumber());
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(setDisc)) {
            deleteDisc(essenceForSend.getNumber());
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(getDisc)) {
            essenceForSend.setDisc(getDisc(essenceForSend.getNumber()));
            return essenceForSend;
        }

        if (essenceForSend.getMethod().contains(deleteClient)) {
            deleteClient(essenceForSend.getNumber());
            return essenceForSend;
        }

        return essenceForSend;
    }


    @Override
    public void run() {
        while (true) {
            try {
                check = (String) ois.readObject();
                if (check.contains("ready")) {
                   essenceForSend = (EssenceForSend) ois.readObject();
                   essenceForSend = handler(essenceForSend);
                   oos.writeObject("ready");
                   oos.flush();
                    System.out.println("ready");
                   oos.writeObject(essenceForSend);
                   oos.flush();
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
