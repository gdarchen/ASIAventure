package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.elements.structure.PieceException;

public class ObjetAbsentDeLaPieceException extends PieceException {

  public ObjetAbsentDeLaPieceException(){
  }

  public ObjetAbsentDeLaPieceException(String msg){
    super(msg);
  }

  public ObjetAbsentDeLaPieceException(String msg, Throwable e){
    super(msg,e);
  }
}
