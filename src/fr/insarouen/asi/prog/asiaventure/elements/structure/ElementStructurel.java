package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * ElementStructurel est une classe permettant de décrire un élement (au sens de structure). <br>
 * ElementStructurel est une classe héritée de Entite.
 *
 * @see Entite
 *
 * @author Darchen / Wang
 * @version 1.0
 */
public abstract class ElementStructurel extends Entite {

  // constructeurs

  /**
   * Constructeur ElementStructurel. <br>
   * A la construction d'un objet ElementStructurel, le constructeur de la classe mère Entite est appelé
   * avec les paramètres "nom" (nom de l'ElementStructurel) et "monde" (le Monde associé à l'ElementStructurel).
   *
   * @param nom
   *        Le nom de l'ElementStructurel, affecté à la création
   * @param monde
   *        Le Monde associé à l'ElementStructurel à la creation
   */
  public ElementStructurel(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
  }
}
