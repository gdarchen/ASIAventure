package fr.insarouen.asi.prog.asiaventure.elements.vivants;

public class CommandeImpossiblePourLeVivantException extends VivantException {

  public CommandeImpossiblePourLeVivantException(){
  }

  public CommandeImpossiblePourLeVivantException(String msg){
    super(msg);
  }

  public CommandeImpossiblePourLeVivantException(String msg, Throwable e){
    super(msg,e);
  }
}
