package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Clef est une classe permettant de modéliser la clef qui peut ouvrir une Serrure.
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public final class Clef extends Objet{

  //constructeur

  /**
   * Constructeur Clef<br>
   * A la construction d'une Clef, le constructeur de la classe supérieure Objet est appelé.
   * @param nom
   *        Le nom de la Clef
   * @param monde
   *        Le Monde dans lequel se situe la Clef
   */
  protected Clef (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
  }

  /**
   * Renvoie un booléen "true", un Clef est déplacable.
   *
   * @return un booléen "true"
   */
  public boolean estDeplacable(){
    return true;
  }
}
