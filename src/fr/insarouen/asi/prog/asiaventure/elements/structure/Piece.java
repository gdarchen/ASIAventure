package fr.insarouen.asi.prog.asiaventure.elements.structure;

import java.util.*;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;

/**
 * Piece est une classe permettant de représenter une pièce d'un Monde.<br>
 * Une pièce contient des Vivant, ou des Objet.
 *
 * @author Darchen / Wang
 * @version 1.1
 */
public class Piece extends ElementStructurel{

  /**
   * Les Objet contenus dans la Piece.
   */
  private Map<String,Objet> lesObjets;

  /**
   * Les Vivant présents dans la Piece.
   */
  private Map<String,Vivant> lesVivants;

  /**
   * Les Porte présentes dans la Piece.
   */
  private Map<String,Porte> lesPortes;

  // constructeurs

  /**
   * Constructeur Piece. <br>
   * A la construction d'un objet Piece, le constructeur de la classe mère ElementStructurel est appelé.<br>
   * Par défaut, une Piece ne contient pas d'Objet ni de Vivant, ni de Porte.
   *
   * @param nom
   *        Le nom de la Piece, affecté à la création
   * @param monde
   *        Le Monde associé à la Piece à la création
   */
  public Piece(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
    lesObjets = new HashMap<>();
    lesVivants = new HashMap<>();
    lesPortes = new HashMap<>();
  }

  //methodes

  /**
   * Ajoute une Porte aux portes de la Piece.
   *
   * @param porte
   *        La Porte à ajouter
   */
  protected void addPorte(Porte porte){
    this.lesPortes.put(porte.getNom(),porte);
  }

  /**
   * Renvoie un booléen indiquant si la Piece possède la Porte passée en paramètre.
   *
   * @param porte
   *        La Porte dont on regarde la présence dans la Piece
   *
   * @return le booléen indiquant si la Porte est présente dans la Piece
   */
  public boolean aLaPorte(Porte porte){
    return aLaPorte(porte.getNom());
  }

  /**
   * Renvoie un booléen indiquant si la Piece possède la Porte
   * dont le nom est passé en paramètre.
   *
   * @param nomPorte
   *        Le nom de la Porte dont on regarde la présence dans la Piece
   *
   * @return le booléen indiquant si la Porte est présente dans la Piece
   */
  public boolean aLaPorte(String nomPorte){
    return this.lesPortes.containsKey(nomPorte);
  }

  /**
   * Renvoie un booléen indiquant si la Piece contient l'Objet "obj" passé en paramètre.
   *
   * @param obj
   *        L'Objet dont on veut regarder la présence dans la Piece
   *
   * @return Un booleen indiquant "true" si l'Objet est présent dans la Piece, "false" sinon
   */
  public boolean contientObjet(Objet obj){
    return lesObjets.containsValue(obj);
  }

  /**
   * Renvoie un booléen indiquant si la Piece contient l'Objet de nom "nomObj" passé en paramètre.
   *
   * @param nomObj
   *        Le nom de l'Objet dont on veut regarder la présence dans la Piece
   *
   * @return Un booleen indiquant "true" si l'Objet est présent dans la Piece, "false" sinon
   */
  public boolean contientObjet(String nomObj){
    return lesObjets.containsKey(nomObj);
  }

  /**
   * Renvoie un booléen indiquant si le Vivant de nom "nomViv" passé en paramètre est présent dans la Piece.
   *
   * @param nomViv
   *        Le nom du Vivant dont on veut regarder la présence dans la Piece
   *
   * @return Un booleen indiquant "true" si le Vivant est présent dans la Piece, "false" sinon
   */
  public boolean contientVivant(String nomViv){
    return lesVivants.containsKey(nomViv);
  }

  /**
   * Renvoie un booléen indiquant si le Vivant "viv" passé en paramètre est présent dans la Piece.
   *
   * @param viv
   *        Le Vivant dont on veut regarder la présence dans la Piece
   *
   * @return Un booleen indiquant "true" si le Vivant est présent dans la Piece, "false" sinon
   */
  public boolean contientVivant(Vivant viv){
    return lesVivants.containsValue(viv);
  }

  /**
   * Permet d'ajouter un Objet "obj" aux Objet contenus dans la Piece.
   *
   * @param obj
   *        L'Objet à déposer dans la Piece
   */
  public void deposer(Objet obj){
    lesObjets.put(obj.getNom(),obj);
  }

  /**
   * Permet à un Vivant de rentrer dans une Piece. Cette méthode ajoute donc un Vivant aux Vivant
   * déjà présents dans la Piece.
   *
   * @param vivant
   *        Le Vivant à faire entrer dans la Piece
   */
  public void entrer(Vivant vivant){
    lesVivants.put(vivant.getNom(),vivant);
  }

  /**
   * Renvoie l'ensemble des Objet contenus dans une Piece.
   *
   * @return L'ensemble des Objet contenus dans la Piece
   */
  public Map<String,Objet> getObjets(){
    return lesObjets;
  }

  /**
   * Renvoie la Porte associée au paramètre "nomPorte" située dans la Piece.
   *
   * @return la Porte associée
   */
  public Porte getPorte(String nomPorte){
    return this.lesPortes.get(nomPorte);
  }

  /**
   * Permet d'enlever un Objet "obj" de la Piece. Si un Objet a été retiré, alors il est retourné.
   *
   * @param obj
   *        L'Objet à retirer de la Piece
   *
   * @return L'Objet retiré, si un Objet a été retiré, "null" sinon
   */
  public Objet retirer(Objet obj) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException {
    return retirer(obj.getNom());
  }

  /**
   * Permet d'enlever un Objet de nom "nomObj" de la Piece. Si un Objet a été retiré, alors il est retourné.
   *
   * @param nomObj
   *        Le nom de l'Objet à retirer de la Piece
   *
   * @return L'Objet retiré, si un Objet a été retiré, "null" sinon
   */
  public Objet retirer(String nomObj) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException {
    Objet objetRetire = lesObjets.remove(nomObj);
    if (objetRetire==null)
      throw new ObjetAbsentDeLaPieceException("L'objet " + nomObj + " n'est pas présent dans la pièce " + this.getNom());
    if (!objetRetire.estDeplacable()){
      deposer(objetRetire);
      throw new ObjetNonDeplacableException(nomObj + " n'est pas déplacable");
    }
    return objetRetire;
  }

  /**
   * Permet de faire sortir un Vivant de nom "nomVivant" de la Piece. Si un Vivant est sorti, alors il est retourné.
   *
   * @param nomVivant
   *        Le nom du Vivant à faire sortir de la Piece
   *
   * @return Le Vivant sorti, si un Vivant est sorti, "null" sinon
   */
  public Vivant sortir(String nomVivant) throws VivantAbsentDeLaPieceException {
    if (!contientVivant(nomVivant))
      throw new VivantAbsentDeLaPieceException("Le vivant n'est pas présent dans la pièce "+nomVivant);
    return lesVivants.remove(nomVivant);
  }

  /**
   * Permet de faire sortir un Vivant "vivant" de la Piece. Si un Vivant est sorti, alors il est retourné.
   *
   * @param vivant
   *        Le Vivant à faire sortir de la Piece
   *
   * @return Le Vivant sorti, si un Vivant est sorti, "null" sinon
   */
  public Vivant sortir(Vivant vivant) throws VivantAbsentDeLaPieceException {
    return sortir(vivant.getNom());
  }

  /**
   * Permet d'obtenir les Porte accessibles depuis la Piece.
   *
   * @return Les Porte accessibles depuis la Piece
   */
  public Map<String,Porte> getPortes(){
    return this.lesPortes;
  }

  /**
   * Renvoie une chaine de caractères contenant les informations propres à une Piece, telles que le nom de
   * la Piece, et les objets contenus dans la Piece.
   *
   * @return La chaine de caractères contenant les informations sur la Piece
   */
  public String toString(){
    StringBuilder laChaine = new StringBuilder("\nDans la pièce ");
    laChaine.append(getNom());
    laChaine.append(" :\n\t- il y a les vivants :");
    for (Vivant viv : this.lesVivants.values()){
      laChaine.append(String.format("\n \t\t - %s",viv.getNom()));
    }
    laChaine.append("\n\t- il y a les objets :");
    for (Objet obj : this.getObjets().values()){
      laChaine.append(String.format("\n \t\t - %s",obj.getNom()));
    }
    laChaine.append("\n\t- et les portes : ");
    for (Porte p : this.getPortes().values()){
      laChaine.append(String.format("\n \t\t - %s qui mène à la pièce %s et dont l'état est %s",p.getNom(),p.getPieceAutreCote(this).getNom(), p.getEtat()));
    }
    return laChaine.toString();
  }
}
