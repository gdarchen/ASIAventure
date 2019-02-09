package fr.insarouen.asi.prog.asiaventure.elements;

public class ActivationImpossibleException extends ActivationException{
  
  public ActivationImpossibleException(){
  }

  public ActivationImpossibleException(String msg){
    super(msg);
  }

  public ActivationImpossibleException(String msg, Throwable e){
    super(msg,e);
  }
}
