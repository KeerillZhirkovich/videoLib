package controller;

import model.EssenceForSend;

import static controller.RunClient.getData;
import static controller.RunClient.sendData;

/**
 * Created by Keerill on 17.12.2017.
 */
public class Handler {

  private EssenceForSend essenceForSend;
  private boolean check = false;

  public Handler(EssenceForSend essenceForSend) {
    this.essenceForSend = essenceForSend;
  }

  public void setEssenceForSend(EssenceForSend essenceForSend) {
    this.essenceForSend = essenceForSend;
  }

  public EssenceForSend getEssenceForSend() {
    return essenceForSend;
  }
}
