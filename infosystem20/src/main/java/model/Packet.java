/**
 * Created by Keerill on 17.12.2017.
 */

package model;

import model.Client;
import model.Disc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Keerill on 17.12.2017.
 */
public class Packet implements Serializable {

    private ArrayList<Disc> discs;
    private ArrayList<Client> clients;
    private Client client;
    private Disc disc;
    private int number;
    private int oneMoreNumber;
    private String info;
    private String method;
    private boolean closeConnection = false;

    public ArrayList<Disc> getDiscs() {
        return discs;
    }

    public void setDiscs(ArrayList<Disc> discs) {
        this.discs = discs;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Disc getDisc() {
        return disc;
    }

    public void setDisc(Disc disc) {
        this.disc = disc;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getOneMoreNumber() {
        return oneMoreNumber;
    }

    public void setOneMoreNumber(int oneMoreNumber) {
        this.oneMoreNumber = oneMoreNumber;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isCloseConnection() {
        return closeConnection;
    }

    public void setCloseConnection(boolean closeConnection) {
        this.closeConnection = closeConnection;
    }
}
