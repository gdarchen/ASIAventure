package fr.insarouen.asi.prog.asiaventure.elements;

public class ActivationImpossibleAvecObjetException extends ActivationImpossibleException{
  
  public ActivationImpossibleAvecObjetException(){
  }

  public ActivationImpossibleAvecObjetException(String msg){
    super(msg);
  }

  public ActivationImpossibleAvecObjetException(String msg, Throwable e){
    super(msg,e);
  }
}
