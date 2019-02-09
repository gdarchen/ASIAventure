package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * PiedDeBiche est une sous-classe de la classe Objet. <br> Cette classe permet de représenter
 * des Objet de type PiedDeBiche qui eux sont déplacables. <br>
 * Cette classe hérite de la classe Objet.
 *
 * @see Objet
 *
 * @author Darchen / Wang
 * @version 1.0
 */
public class PiedDeBiche extends Objet {

  // constructeurs

  /**
   * Constructeur PiedDeBiche. <br>
   * A la construction d'un objet PiedDeBiche, le constructeur de la classe mère Objet est appelé
   * avec les paramètres "nom" (nom du PiedDeBiche) et "monde" (le Monde associé au PiedDeBiche).
   *
   * @param nom
   *        Le nom du PiedDeBiche, affecté à la création
   * @param monde
   *        Le Monde associé au PiedDeBiche à la creation
   */
  public PiedDeBiche(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
  }

  // methodes

  /**
   * Renvoie toujours "true" puisqu'un PiedDeBiche, de base, est déplacable.
   *
   * @return Un booléen true : un PiedDeBiche est de base déplacable
   */
  public boolean estDeplacable(){
    return true;
  }
}
