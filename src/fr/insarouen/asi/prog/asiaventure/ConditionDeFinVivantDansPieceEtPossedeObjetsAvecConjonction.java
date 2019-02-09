package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;

/**
 * ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction est une classe modélisant une ConditionDeFin
 * associée au fait qu'un Vivant soit présent ou non dans une Piece et qu'il possède ou non une
 * certaine liste d'Objet, en utilisant le fait que cette ConditionDeFin soit une ConditionDeFinConjonctionConditionDeFin.
 * @author Darchen / Wang
 * @version 1.3
 */
public class ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction extends ConditionDeFinConjonctionConditionDeFin {
  /**
   * La ConditionDeFinVivantDansPiece associée à la ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction
   */
  private ConditionDeFinVivantDansPiece condPiece;

  /**
   * La ConditionDeFinVivantPossedeObjets associée à la ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction
   */
  private ConditionDeFinVivantPossedeObjets condObjets;

  // constructeur

  /**
   * Constructeur ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction. <br>
   * A la construction d'une ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction, le constructeur de la classe
   * supérieure est appelé, et la ConditionDeFinVivantDansPiece tout comme la ConditionDeFinVivantPossedeObjets
   * sont associées à la ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction
   * @param etatConditionVerifiee
   *        L'EtatDuJeu associé à la ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction
   * @param vivant
   *        Le Vivant à associer à la ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction
   * @param piece
   *        La Piece à associer à la ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction
   * @param objets
   *        Les Objet à associer à la ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction
   */
  public ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece, Objet... objets){
    super(etatConditionVerifiee);
    condPiece = new ConditionDeFinVivantDansPiece(etatConditionVerifiee,vivant,piece);
    condObjets = new ConditionDeFinVivantPossedeObjets(etatConditionVerifiee,vivant,objets);
  }

  // méthodes

  /**
   * Cette méthode a pour but de renvoyer l'EtatDuJeu associé à la ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction
   * si le Vivant est effectivement présent dans la Piece et si le Vivant possède tous les Objets associés à la
   * ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction, et l'EtatDuJeu "ENCOURS" si ce n'est pas le cas.
   * @return L'EtatDuJeu associé à la ConditionDeFinVivantDansPieceEtPossedeObjetsAvecConjonction, ou ENCOURS
   */
  public EtatDuJeu verifierCondition(){
    if (condPiece.verifierCondition() == EtatDuJeu.ENCOURS)
      return EtatDuJeu.ENCOURS;
    return condObjets.verifierCondition();
  }
}
