package fr.insarouen.asi.prog.asiaventure.elements.structure;

public class PorteInexistanteDansLaPieceException extends PieceException {

  public PorteInexistanteDansLaPieceException(){
  }

  public PorteInexistanteDansLaPieceException(String msg){
    super(msg);
  }

  public PorteInexistanteDansLaPieceException(String msg, Throwable e){
    super(msg,e);
  }
}
