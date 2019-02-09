package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.ASIAventureException;

public class ActivationException extends ASIAventureException {

  public ActivationException(){
  }

  public ActivationException(String msg){
    super(msg);
  }

  public ActivationException(String msg, Throwable e){
    super(msg,e);
  }
}
