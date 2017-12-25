package model.dao.tools;

import model.Client;

import java.util.Comparator;

/**
 * Created by Keerill on 25.12.2017.
 */
public class CompareClients implements Comparator<Client> {
  @Override
  public int compare(Client o1, Client o2) {
    return o1.getClientID() - o2.getClientID();
  }
}
