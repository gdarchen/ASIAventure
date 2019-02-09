package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.ASIAventureException;

public class ObjetException extends ASIAventureException {

  public ObjetException(){
  }

  public ObjetException(String msg){
    super(msg);
  }

  public ObjetException(String msg, Throwable e){
    super(msg,e);
  }
}
