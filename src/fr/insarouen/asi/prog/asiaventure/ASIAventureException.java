package fr.insarouen.asi.prog.asiaventure;

public class ASIAventureException extends Exception {

  public ASIAventureException(){
  }

  public ASIAventureException(String msg){
    super(msg);
  }

  public ASIAventureException(String msg, Throwable e){
    super(msg,e);
  }
}
