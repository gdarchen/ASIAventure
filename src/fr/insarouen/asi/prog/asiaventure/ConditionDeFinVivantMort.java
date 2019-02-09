package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;

/**
 * ConditionDeFinVivantMort est une classe modélisant une ConditionDeFin
 * associée au fait qu'un Vivant soit mort ou non.
 * @author Darchen / Wang
 * @version 1.3
 */
public class ConditionDeFinVivantMort extends ConditionDeFin {
  /**
   * Le Vivant associé à la ConditionDeFinVivantMort.
   */
  private Vivant vivant;

  // constructeur

  /**
   * Constructeur ConditionDeFinVivantMort. <br>
   * A la construction d'une ConditionDeFinVivantMort, le constructeur de la classe
   * supérieure est appelé, et le Vivant est associé à la ConditionDeFinVivantMort.
   * @param etatConditionVerifiee
   *        L'EtatDuJeu associé à la ConditionDeFinVivantMort
   * @param vivant
   *        Le Vivant à associer à la ConditionDeFinVivantMort
   */
  public ConditionDeFinVivantMort(EtatDuJeu etatConditionVerifiee, Vivant vivant){
    super(etatConditionVerifiee);
    this.vivant = vivant;
  }

  // méthodes

  /**
   * Cette méthode a pour but de renvoyer l'EtatDuJeu associé à la ConditionDeFinVivantMort
   * si le Vivant est effectivement mort, et l'EtatDuJeu "ENCOURS" si ce n'est pas le cas.
   * @return L'EtatDuJeu associé à la ConditionDeFinVivantMort, ou ENCOURS
   */
  public EtatDuJeu verifierCondition(){
    if (this.vivant.estMort())
      return getEtatConditionVerifiee();
    else
      return EtatDuJeu.ENCOURS;
  }
}
