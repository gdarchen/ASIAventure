package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;

/**
 * Coffre est une classe permettant de modéliser un coffre...
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public class Coffre extends Objet implements Activable{
  /**
   * L'Etat du Coffre.
   */
  private Etat etat;

  //constructeur

  /**
   * Constructeur Coffre.<br>
   * A la construction d'un Coffre, il y appel du constructeur de la classe supérieure.
   * @param nom
   *        Le nom du Coffre
   * @param monde
   *        Le Monde dans lequel se situe le Coffre
   */
  public Coffre(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
    this.etat = Etat.FERME;
  }

  /**
   * Renvoie un booléen "false", un Coffre n'est pas déplacable.
   *
   * @return un booléen "false"
   */
  public boolean estDeplacable(){
    return false;
  }

  /**
   * Renvoie un booléen indiquant si le Coffre est activable avec l'Objet passé en paramètre.
   *
   * @param obj
   *        L'Objet qui peut potentiellement activer le Coffre
   *
   * @return le booléen indiquant si le Coffre est activable avec l'Objet
   */
  public boolean activableAvec(Objet obj){
    if (this.getEtat().equals(Etat.CASSE))
      return false;
    if (obj instanceof PiedDeBiche)
      return true;
    return false;
  }

  /**
   * Modifie l'Etat d'un Coffre. Passe de "OUVERT" à "FERME" et réciproquement.
   */
  public void activer() throws ActivationException{
    if (!((this.getEtat().equals(Etat.FERME))||(this.getEtat().equals(Etat.OUVERT))))
      throw new ActivationImpossibleException("Coffre impossible à activer");

    this.etat = (this.etat.equals(Etat.FERME))? Etat.OUVERT : Etat.FERME;
  }

  /**
   * Permet d'activer le Coffre avec l'Objet passé en paramètre, si cela est possible.
   * <br> Peut aussi permettre de verrouiller ou deverrouiller un Coffre.
   *
   * @param obj
   *        L'Objet qui peut potentiellement activer le Coffre
   */
  public void activerAvec(Objet obj) throws ActivationException{
    if (!activableAvec(obj))
      throw new ActivationImpossibleAvecObjetException(String.format("Le coffre %s ne peut pas être activé par l'Objet %s",this.getNom(),obj.getNom()));
    if (obj instanceof PiedDeBiche)
      this.etat = Etat.CASSE;
    this.activer();
  }

  /**
   * Retourne l'Etat d'un Coffre.
   *
   * @return l'Etat du Coffre
   */
  public Etat getEtat(){
    return this.etat;
  }

  /**
   * Renvoie une chaine de caractères contenant les informations sur un Coffre.
   *
   * @return la chaine de caractères contenant les informations sur le Coffre
   */
  public String toString(){
    StringBuilder laChaine = new StringBuilder("Coffre : ");
    laChaine.append(String.format("%s ",getNom()));
    return laChaine.toString();
  }
}
