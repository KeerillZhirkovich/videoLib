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

import static model.dao.tools.FileChecker.fileIsEmpty;

public class Controller {
    private static IODAOClient daoClient = new IODAOClient();
    private static IODAODisc daoDiscs = new IODAODisc();

    public static JTable ShowClients(JTable jTable) {

        ArrayList<Client> clients = daoClient.getClients();

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

        ArrayList<Disc> discs = daoDiscs.getDiscs();
        DefaultTableModel dtm =(DefaultTableModel)jTable.getModel();

        for (int j = 0; j < discs.size(); j++) {
            dtm.insertRow(j , new Vector(0));
            jTable.setValueAt(discs.get(j).getDiskID(), j, 0);
            jTable.setValueAt(discs.get(j).getRussianTitle(), j, 1);
        }

        return jTable;
    }

    public static Disc getDiscByNumber(int number) {
        return daoDiscs.getDisc(number);
    }


}
