package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.VivantException;

public class ObjetNonPossedeParLeVivantException extends VivantException {

  public ObjetNonPossedeParLeVivantException(){
  }

  public ObjetNonPossedeParLeVivantException(String msg){
    super(msg);
  }

  public ObjetNonPossedeParLeVivantException(String msg, Throwable e){
    super(msg,e);
  }
}
