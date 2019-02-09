package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;

/**
 * ConditionDeFinVivantDansPiece est une classe modélisant une ConditionDeFin
 * associée au fait qu'un Vivant soit présent ou non dans une Piece.
 * @author Darchen / Wang
 * @version 1.3
 */
public class ConditionDeFinVivantDansPiece extends ConditionDeFin {
  /**
   * Le Vivant associé à la ConditionDeFinVivantDansPiece.
   */
  private Vivant vivant;

  /**
   * La Piece associée à la ConditionDeFinVivantDansPiece.
   */
  private Piece piece;

  // constructeur

  /**
   * Constructeur ConditionDeFinVivantDansPiece. <br>
   * A la construction d'une ConditionDeFinVivantDansPiece, le constructeur de la classe
   * supérieure est appelée, et le Vivant et la Piece sont associées à la ConditionDeFinVivantDansPiece.
   * @param etatConditionVerifiee
   *        L'EtatDuJeu associé à la ConditionDeFinVivantDansPiece
   * @param vivant
   *        Le Vivant à associer à la ConditionDeFinVivantDansPiece
   * @param piece
   *        La Piece à associer à la ConditionDeFinVivantDansPiece
   */
  public ConditionDeFinVivantDansPiece(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece){
    super(etatConditionVerifiee);
    this.vivant = vivant;
    this.piece = piece;
  }

  // méthodes

  /**
   * Cette méthode a pour but de renvoyer l'EtatDuJeu associée à la ConditionDeFinVivantDansPiece
   * si le Vivant est effectivement présent dans la Piece, et l'EtatDuJeu "ENCOURS" si ce n'est pas le cas.
   * @return L'EtatDuJeu associé à la ConditionDeFinVivantDansPiece, ou ENCOURS
   */
  public EtatDuJeu verifierCondition(){
    if (this.vivant.getPiece().equals(this.piece))
      return getEtatConditionVerifiee();
    else
      return EtatDuJeu.ENCOURS;
  }
}
