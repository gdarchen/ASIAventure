package fr.insarouen.asi.prog.asiaventure.elements.structure;

public class PorteFermeException extends ElementStructurelException {

  public PorteFermeException(){
  }

  public PorteFermeException(String msg){
    super(msg);
  }

  public PorteFermeException(String msg, Throwable e){
    super(msg,e);
  }
}
