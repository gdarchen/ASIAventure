package fr.insarouen.asi.prog.asiaventure.elements.objets;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Objet est la classe de base pour représenter les différents objets manipulables.<br>
 * Cette classe hérite de la classe Entite. En plus d'une Entite, un Objet a une notion de
 * déplacabilité. <br>
 * Cette classe est abstraite.
 *
 * @see Entite
 *
 * @author Darchen / Wang
 * @version 1.1
 */
public abstract class Objet extends Entite {

  // constructeurs

  /**
   * Constructeur Objet. <br>
   * A la construction d'un objet Objet, le constructeur de la classe mère Entite est appelé
   * avec les paramètres "nom" (nom de l'Objet) et "monde" (le Monde associé à l'Objet).
   *
   * @param nom
   *        Le nom de l'Objet, affecté à la création
   * @param monde
   *        Le Monde associé à l'Objet à la création
   */
  public Objet(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
  }

  // methodes

  /**
   * Méthode abstraite qui retourne un booléen selon la déplacabilité d'un Objet.
   */
  public abstract boolean estDeplacable();
}
