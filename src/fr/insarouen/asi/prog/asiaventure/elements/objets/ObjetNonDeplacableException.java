package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetException;

public class ObjetNonDeplacableException extends ObjetException {

  public ObjetNonDeplacableException(){
  }

  public ObjetNonDeplacableException(String msg){
    super(msg);
  }

  public ObjetNonDeplacableException(String msg, Throwable e){
    super(msg,e);
  }
}
