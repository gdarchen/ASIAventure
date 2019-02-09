package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;

/**
 * ConditionDeFinVivantDansPieceEtPossedeObjets est une classe modélisant une ConditionDeFin
 * associée au fait qu'un Vivant soit présent ou non dans une Piece et qu'il possède ou non une
 * certaine liste d'Objet.
 * @author Darchen / Wang
 * @version 1.3
 */
public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFin {
  /**
   *
   */
  private ConditionDeFinVivantDansPiece dansPiece;

  /**
   *
   */
  private ConditionDeFinVivantPossedeObjets possedeObjets;


  // constructeur

  /**
   * Constructeur ConditionDeFinVivantDansPieceEtPossedeObjets. <br>
   * A la construction d'une ConditionDeFinVivantDansPieceEtPossedeObjets, le constructeur de la classe
   * supérieure est appelé, et le Vivant et la Piece sont associés à la ConditionDeFinVivantDansPieceEtPossedeObjets,
   * tout comme les Objet que le Vivant est susceptible de posséder ou non.
   * @param etatConditionVerifiee
   *        L'EtatDuJeu associé à la ConditionDeFinVivantDansPieceEtPossedeObjets
   * @param vivant
   *        Le Vivant à associer à la ConditionDeFinVivantDansPieceEtPossedeObjets
   * @param piece
   *        La Piece à associer à la ConditionDeFinVivantDansPieceEtPossedeObjets
   * @param objets
   *        Les Objet à associer à la ConditionDeFinVivantDansPieceEtPossedeObjets
   */
  public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece, Objet... objets){
    super(etatConditionVerifiee);
    dansPiece = new ConditionDeFinVivantDansPiece(etatConditionVerifiee,vivant,piece);
    possedeObjets = new ConditionDeFinVivantPossedeObjets(etatConditionVerifiee,vivant,objets);
  }

  // méthodes

  /**
   * Cette méthode a pour but de renvoyer l'EtatDuJeu associé à la ConditionDeFinVivantDansPieceEtPossedeObjets
   * si le Vivant est effectivement présent dans la Piece et si le Vivant possède tous les Objets associés à la
   * ConditionDeFinVivantDansPieceEtPossedeObjets, et l'EtatDuJeu "ENCOURS" si ce n'est pas le cas.
   * @return L'EtatDuJeu associé à la ConditionDeFinVivantDansPieceEtPossedeObjets, ou ENCOURS
   */
  public EtatDuJeu verifierCondition(){
    EtatDuJeu etat = dansPiece.verifierCondition();
    if(etat != EtatDuJeu.ENCOURS)
      return etat;
    return possedeObjets.verifierCondition();
  }
}
