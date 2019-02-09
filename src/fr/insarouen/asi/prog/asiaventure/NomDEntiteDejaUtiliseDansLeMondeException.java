package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.MondeException;

public class NomDEntiteDejaUtiliseDansLeMondeException extends MondeException {

  public NomDEntiteDejaUtiliseDansLeMondeException(){
  }

  public NomDEntiteDejaUtiliseDansLeMondeException(String msg){
    super(msg);
  }

  public NomDEntiteDejaUtiliseDansLeMondeException(String msg, Throwable e){
    super(msg,e);
  }
}
