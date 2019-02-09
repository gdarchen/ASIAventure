package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;

/**
 * Porte est une classe permettant de modéliser une porte entre deux Piece.<br>
 * Une Porte sépare deux Piece. <br>
 * Une Porte possède un certain Etat.
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public class Porte extends ElementStructurel implements Activable{

  /**
   * La première Piece, d'un côté de la Porte.
   */
  private Piece pieceA;

  /**
   * La seconde Piece, de l'autre côté de la Porte.
   */
  private Piece pieceB;

  /**
   * L'Etat de la Porte.
   */
  private Etat etat;

  /**
   * La serrure de la Porte.
   */
  private Serrure serrure;

  // constructeurs

  /**
   * Constructeur Porte.<br>
   * A la construction d'une Porte, on ajoute cette Porte dans les Piece de part et d'autre de
   * la Porte. On affecte aussi les attributs de pieceA et pieceB, et l'Etat, FERME de base.
   *
   * @param nom
   *        Le nom de la Porte
   * @param monde
   *        Le Monde dans lequel se situe la Porte
   * @param pieceA
   *        La Piece d'un côté de la Porte
   * @param pieceB
   *        La Piece de l'autre côté de la Porte
   */
  public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
    this.pieceA = pieceA;
    this.pieceA.addPorte(this);
    this.pieceB = pieceB;
    this.pieceB.addPorte(this);
    this.etat = Etat.FERME;
    this.serrure = null;
  }

  /**
   * Constructeur Porte.<br>
   * A la construction d'une Porte, on ajoute cette Porte dans les Piece de part et d'autre de
   * la Porte. On affecte aussi les attributs de pieceA et pieceB, et l'Etat, VERROUILLE de base.
   * <br> Ici, la Porte a une Serrure.
   * @param nom
   *        Le nom de la Porte
   * @param monde
   *        Le Monde dans lequel se situe la Porte
   * @param serrure
   *        La Serrure associée à la Porte
   * @param pieceA
   *        La Piece d'un côté de la Porte
   * @param pieceB
   *        La Piece de l'autre côté de la Porte
   */
  public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
    this(nom,monde,pieceA,pieceB);
    this.serrure = serrure;
    this.etat = Etat.VERROUILLE;
  }

  // méthodes

  /**
   * Renvoie un booléen indiquant si la Porte est activable avec l'Objet passé en paramètre.
   *
   * @param obj
   *        L'Objet qui peut potentiellement activer la Porte
   *
   * @return le booléen indiquant si la Porte est activable avec l'Objet
   */
  public boolean activableAvec(Objet obj){
    if (this.getEtat().equals(Etat.CASSE))
      return false;
    if (obj instanceof PiedDeBiche)
      return true;
    if (this.serrure != null)
      return this.serrure.activableAvec(obj);
    return true;
  }

  /**
   * Modifie l'Etat d'une Porte. Passe de "OUVERT" à "FERME" et réciproquement.
   */
  public void activer() throws ActivationImpossibleException{
    if (!((this.getEtat().equals(Etat.FERME))||(this.getEtat().equals(Etat.OUVERT))))
      throw new ActivationImpossibleException("Porte impossible à activer");

    this.etat = (this.etat.equals(Etat.FERME))? Etat.OUVERT : Etat.FERME;
  }

  /**
   * Permet d'activer la Porte avec l'Objet passé en paramètre, si cela est possible.
   * <br> Peut aussi permettre de verrouiller ou deverrouiller une Porte.
   *
   * @param obj
   *        L'Objet qui peut potentiellement activer la Porte
   */
  public void activerAvec(Objet obj)throws ActivationImpossibleAvecObjetException, ActivationImpossibleException{
    if (!activableAvec(obj))
      throw new ActivationImpossibleAvecObjetException(String.format("La porte %s ne peut pas être activée par l'Objet %s",this.getNom(),obj.getNom()));
    if (obj instanceof PiedDeBiche && ((this.getEtat()==Etat.FERME) ||(this.getEtat()==Etat.VERROUILLE))){
      this.etat = Etat.CASSE;
      this.serrure.activerAvec(obj);
      return;
    }
    if (this.serrure != null){
      this.serrure.activerAvec(obj);
      if (this.getEtat().equals(Etat.VERROUILLE))
        this.etat = Etat.OUVERT;
      else
        this.etat = Etat.VERROUILLE;
    }
  }

  /**
   * Retourne l'Etat d'une Porte.
   *
   * @return l'Etat de la Porte
   */
  public Etat getEtat(){
    return this.etat;
  }

  /**
   * Retourne la Piece se situant de l'autre côté de la Porte par rapport à la
   * Piece passée en paramètre
   *
   * @param piece
   *        La Piece de référence, d'un côté de la Porte
   *
   * @return la Piece de l'autre côté de la Porte par rapport à "piece"
   */
  public Piece getPieceAutreCote(Piece piece){
    if (piece.equals(pieceA))
      return pieceB;
    else
      return pieceA;
  }

  /**
   * Renvoie la Serrure associée à la Porte.
   *
   * @return la Serrure de la Porte
   */
  public Serrure getSerrure(){
    return this.serrure;
  }

  /**
   * Renvoie une chaine de caractères contenant les informations sur une Porte.
   *
   * @return la chaine de caractères contenant les informations sur la Porte
   */
  public String toString(){
    StringBuilder laChaine = new StringBuilder();
    laChaine.append(String.format("Porte : %s -- Monde : %s\n",this.getNom(),this.getMonde()));
    laChaine.append(String.format("Entre la pieceA : %s et la pieceB %s\n",this.pieceA.getNom(),this.pieceB.getNom()));
    laChaine.append(String.format("Etat : %s",this.getEtat().toString()));
    return laChaine.toString();
  }

}
