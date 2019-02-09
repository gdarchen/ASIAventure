package fr.insarouen.asi.prog.asiaventure;

import java.io.*;

/**
 * ConditionDeFin est une classe abstraite modélisant une condition associée à un EtatDuJeu.
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public abstract class ConditionDeFin implements Serializable {
  /**
   * L'EtatDuJeu associé à la ConditionDeFin
   */
  private EtatDuJeu etatDuJeu;

  // constructeur

  /**
   * Constructeur ConditionDeFin. <br>
   * A la construction d'une ConditionDeFin, un EtatDuJeu lui est associé.
   * @param etatDuJeu
   *        L'EtatDuJeu associé à la ConditionDeFin
   */
  public ConditionDeFin(EtatDuJeu etatDuJeu){
    this.etatDuJeu = etatDuJeu;
  }

  // méthodes

  /**
   * Cette méthode permet de renvoyer l'EtatDuJeu de la ConditionDeFin (au cas où la condition est vérifiée).
   * @return L'EtatDuJeu associé à la ConditionDeFin
   */
  public EtatDuJeu getEtatConditionVerifiee(){
    return this.etatDuJeu;
  }

  /**
   * Méthode abstraite permet de vérifier sur la ConditionDeFin est vérifiée.
   */
  public abstract EtatDuJeu verifierCondition();
}
