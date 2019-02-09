package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.MondeException;

public class EntiteDejaDansUnAutreMondeException extends MondeException {

  public EntiteDejaDansUnAutreMondeException(){
  }

  public EntiteDejaDansUnAutreMondeException(String msg){
    super(msg);
  }

  public EntiteDejaDansUnAutreMondeException(String msg, Throwable e){
    super(msg,e);
  }
}
