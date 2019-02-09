package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.elements.structure.PieceException;

public class VivantAbsentDeLaPieceException extends PieceException {

  public VivantAbsentDeLaPieceException(){
  }

  public VivantAbsentDeLaPieceException(String msg){
    super(msg);
  }

  public VivantAbsentDeLaPieceException(String msg, Throwable e){
    super(msg,e);
  }
}
