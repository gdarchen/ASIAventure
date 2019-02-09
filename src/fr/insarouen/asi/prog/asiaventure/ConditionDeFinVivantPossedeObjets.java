package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

/**
 * ConditionDeFinVivantPossedeObjets est une classe modélisant une ConditionDeFin
 * associée au fait qu'un Vivant possède ou non une certaine liste d'Objet.
 * @author Darchen / Wang
 * @version 1.3
 */
public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin {
  /**
  * Le Vivant associé à la ConditionDeFinVivantPossedeObjets.
   */
  private Vivant vivant;

  /**
   * Les Objet associés à la ConditionDeFinVivantPossedeObjets.
   */
  private Objet[] objets;

  // constructeur

  /**
   * Constructeur ConditionDeFinVivantPossedeObjets. <br>
   * A la construction d'une ConditionDeFinVivantPossedeObjets, le constructeur de la classe
   * supérieure est appelé, et le Vivant est associé à la ConditionDeFinVivantPossedeObjets,
   * tout comme les Objet que le Vivant est susceptible de posséder ou non.
   * @param etatConditionVerifiee
   *        L'EtatDuJeu associé à la ConditionDeFinVivantPossedeObjets
   * @param vivant
   *        Le Vivant à associer à la ConditionDeFinVivantPossedeObjets
   * @param objets
   *        Les Objet à associer à la ConditionDeFinVivantPossedeObjets
   */
  public ConditionDeFinVivantPossedeObjets(EtatDuJeu etatConditionVerifiee, Vivant vivant, Objet... objets){
    super(etatConditionVerifiee);
    this.vivant = vivant;
    this.objets = objets;
  }

  // méthodes

  /**
   * Cette méthode a pour but de renvoyer l'EtatDuJeu associé à la ConditionDeFinVivantPossedeObjets
   * si le Vivant possède effectivement tous les Objets associés à la ConditionDeFinVivantPossedeObjets,
   * et l'EtatDuJeu "ENCOURS" si ce n'est pas le cas.
   * @return L'EtatDuJeu associé à la ConditionDeFinVivantPossedeObjets, ou ENCOURS
   */
  public EtatDuJeu verifierCondition(){
    for (Objet obj : this.objets)
      if (!vivant.possede(obj))
        return EtatDuJeu.ENCOURS;

    return getEtatConditionVerifiee();
  }
}
