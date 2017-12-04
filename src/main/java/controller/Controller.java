package controller;

import model.Client;
import model.Disc;
import model.dao.EssenceForSave;
import model.dao.interfaces.DAOClient;
import model.dao.io.IODAOClient;
import model.dao.io.IODAODisc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Vector;

import static model.dao.io.DataLoad.loadNewBase;
import static model.dao.io.DataLoad.mergeBases;
import static model.dao.io.DataLoad.writeData;
import static model.dao.tools.FileChecker.fileIsEmpty;

public class Controller {
    private static IODAOClient daoClients = new IODAOClient();
    private static IODAODisc daoDiscs = new IODAODisc();
    private static ArrayList<Disc> discs=daoDiscs.getDiscs();

    public static JTable ShowClients(JTable jTable) {

        ArrayList<Client> clients = daoClients.getClients();

        DefaultTableModel dtm = (DefaultTableModel)jTable.getModel();
        for (int j = 0; j<clients.size();j++) {
            dtm.insertRow(j,new Vector(0));
            jTable.setValueAt(clients.get(j).getClientID(), j, 0);
            jTable.setValueAt(clients.get(j).getName(), j, 1);
            jTable.setValueAt(clients.get(j).getSurname(), j, 2);
            jTable.setValueAt(clients.get(j).getPhone(), j, 3);
        }

        return jTable;
    }


    public static JTable ShowDiscs(JTable jTable) {

        DefaultTableModel dtm =(DefaultTableModel)jTable.getModel();

        for (int j = 0; j < discs.size(); j++) {
            dtm.insertRow(j , new Vector(0));
            jTable.setValueAt(discs.get(j).getDiskID(), j, 0);
            jTable.setValueAt(discs.get(j).getRussianTitle(), j, 1);
        }

        return jTable;
    }

    public static void saveChanges() {
        writeData(daoDiscs.getDiscs(), daoClients.getClients());
    }

    public static Disc getDiscByNumber(int number) {
        return daoDiscs.getDisc(number);
    }

    public static void Search(String searchString) {
        discs = daoDiscs.getDiscsOnTheDataSet(searchString);
    }
    
    public static ArrayList<Disc> getDiscs() {
        return discs;
    }
    
    public static ArrayList<Client> getClients() {
        return daoClients.getClients();
    }
    
    public static Client getClient(int id) {
        return daoClients.getClient(id);
    }
    
    public static void setClient(Client client) {
        daoClients.setClient(client);
    }
    
    public static void setClient(int discID, int clientID) {
        daoDiscs.getDisc(discID).setClientID(clientID);
        discs = daoDiscs.getDiscs();
    }
    
    public static void deleteDisc(int id) {
        daoDiscs.deleteDisc(id);
    }
    
    public static void setDisc(Disc disc) {
        daoDiscs.setDisc(disc);
    }
    
    public static Disc getDisc(int id) {
        return daoDiscs.getDisc(id);
    }
    
    public static void deleteClient(int id)
    {
        daoClients.deleteClient(id);
    }
    
    public static void openBase(String filePath) {
        loadNewBase(filePath);
    }
    
    public static void mergeBase(String filePath) {

        EssenceForSave newData = mergeBases(filePath);


        daoDiscs.updateDiscs(newData.getDiscs());
        daoClients.updateClients(newData.getClients());

        writeData(daoDiscs.getDiscs(), daoClients.getClients());
    }
}
