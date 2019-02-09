package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.ASIAventureException;

public class ElementStructurelException extends ASIAventureException {

  public ElementStructurelException(){
  }

  public ElementStructurelException(String msg){
    super(msg);
  }

  public ElementStructurelException(String msg, Throwable e){
    super(msg,e);
  }
}
