package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.elements.structure.ElementStructurelException;

public class PieceException extends ElementStructurelException {

  public PieceException(){
  }

  public PieceException(String msg){
    super(msg);
  }

  public PieceException(String msg, Throwable e){
    super(msg,e);
  }
}
