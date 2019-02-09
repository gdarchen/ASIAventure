package fr.insarouen.asi.prog.asiaventure;

public class MondeException extends ASIAventureException {

  public MondeException(){
  }

  public MondeException(String msg){
    super(msg);
  }

  public MondeException(String msg, Throwable e){
    super(msg,e);
  }
}
