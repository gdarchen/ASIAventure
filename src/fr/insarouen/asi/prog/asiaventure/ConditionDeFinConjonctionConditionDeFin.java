package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;

/**
 * ConditionDeFinConjonctionConditionDeFin est une classe modélisant une ConditionDeFin constituée de plusieurs
 * ConditionDeFin.
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public class ConditionDeFinConjonctionConditionDeFin extends ConditionDeFin {
  /**
   * Les ConditionDeFin associées à la ConditionDeFinConjonctionConditionDeFin
   */
  private ConditionDeFin[] condFin;

  // constructeur

  /**
   * Constructeur ConditionDeFinConjonctionConditionDeFin. <br>
   * A la construction d'une ConditionDeFinConjonctionConditionDeFin, un EtatDuJeu lui est associé
   * ainsi que plusieurs ConditionDeFin.
   * @param etatConditionVerifiee
   *        L'EtatDuJeu associé à la ConditionDeFinConjonctionConditionDeFin
   * @param cfs
   *        Les ConditionDeFin associées à la ConditionDeFinConjonctionConditionDeFin
   */
  public ConditionDeFinConjonctionConditionDeFin(EtatDuJeu etatConditionVerifiee, ConditionDeFin... cfs){
    super(etatConditionVerifiee);
    condFin = cfs;
  }

  // méthodes

  /**
   * Cette méthode permet de vérifier si toutes les ConditionDeFin de la ConditionDeFinConjonctionConditionDeFin
   * sont vérifiées, auquel cas l'EtatDuJeu de la ConditionDeFinConjonctionConditionDeFin est retourné, sinon
   * si au moins une des ConditionDeFin n'est pas vérifiée, l'EtatDuJeu "ENCOURS" est retourné.
   * @return L'EtatDuJeu associé à la ConditionDeFinConjonctionConditionDeFin, ou ENCOURS
   */
  public EtatDuJeu verifierCondition(){
    for (ConditionDeFin cfs : condFin){
      if ((cfs.verifierCondition()) == EtatDuJeu.ENCOURS)
        return EtatDuJeu.ENCOURS;
    }

    return getEtatConditionVerifiee();
  }
}
