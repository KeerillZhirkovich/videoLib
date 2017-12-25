package model.dao.tools;

import model.Disc;

import java.util.Comparator;

/**
 * Created by Keerill on 25.12.2017.
 */
public class CompareDiscs implements Comparator<Disc> {
  @Override
  public int compare(Disc o1, Disc o2) {
    return o1.getDiskID() - o2.getDiskID();
  }
}
