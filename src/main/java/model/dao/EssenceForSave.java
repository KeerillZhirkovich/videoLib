package model.dao;

import model.Client;
import model.Disc;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Служебный класс, объединяющий коллекции Disc и Client
 * в единое целое для сохранения в один файл - базу.
 */
public class EssenceForSave implements Serializable {

    private ArrayList<Disc> discs;
    private ArrayList<Client> clients;

    /**
     * @param disc
     * @param clients
     */
    public EssenceForSave(ArrayList<Disc> disc, ArrayList<Client> clients) {
        this.discs = disc;
        this.clients = clients;
    }

    /**
     * Конструктор по умолчанию.
     */
    public EssenceForSave() {
        discs = new ArrayList<>();
        clients = new ArrayList<>();
    }

    /**
     * @return
     */
    public ArrayList<Disc> getDiscs() {
        return discs;
    }

    /**
     * @return
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * @param discs
     */
    public void setDiscs(ArrayList<Disc> discs) {
        this.discs = discs;
    }

    /**
     * @param clients
     */
    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
}
