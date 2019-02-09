package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import java.util.*;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;

/**
 * Vivant est une classe représentant une Entite vivante. <br>
 * Un Vivant est caractérisé par :
 * <ul>
 *    <li> les Objet qu'il détient ; </li>
 *    <li> ses points de vie ; </li>
 *    <li> ses points de force ; </li>
 *    <li> la Piece dans laquelle le Vivant </li>
 * </ul>
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public abstract class Vivant extends Entite implements Executable{

  /**
   * Les Objet que possède le Vivant.
   */
  private Map<String,Objet> lesObjets;

  /**
   * Les points de vie du Vivant.
   */
  private int PV;

  /**
   * Les points de force du Vivant.
   */
  private int PF;

  /**
   * La Piece dans laquelle se trouve le Vivant.
   */
  private Piece piece;


  // constructeur

  /**
   * Constructeur Vivant. <br>
   * A la construction d'un objet Vivant, le constructeur de la classe mère Entite est appelé.<br>
   * On affecte à la construction les Objet passés en paramètre du constructeur au Vivant. <br>
   * On affecte au Vivant ses points de vie et points de force. <br>
   * On fixe aussi la Piece dans laquelle se trouve le Vivant.
   *
   * @param nom
   *        Le nom du Vivant, affecté à la création
   * @param monde
   *        Le Monde associé au Vivant à la création
   * @param pv
   *        Les points de vie affectés au Vivant
   * @param pf
   *        Les points de force affectés au Vivant
   * @param objets
   *        Les Objet affectés au Vivant
   */
  public Vivant(String nom, Monde monde, int pv, int pf, Piece p, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
    super(nom,monde);
    this.PV=pv;
    this.PF=pf;
    this.piece=p;
    lesObjets = new HashMap<>();
    for (int i=0;i<objets.length;i++)
      lesObjets.put(objets[i].getNom(),objets[i]);
    piece.entrer(this);
  }

  // méthodes

  /**
   * Méthode permettant au Vivant d'activer un Objet Activable.
   * @param activable
   *        L'Objet Activable à activer
   */
  public void activerActivable(Activable activable) throws ActivationException{
    activable.activer();
  }

  /**
   * Méthode permettant au Vivant d'activer un Objet Activable à l'aide d'un Objet "objet".
   * @param activable
   *        L'Objet Activable à activer
   * @param objet
   *        L'Objet avec lequel le Vivant doit activer l'Activable activable
   */
  public void activerActivableAvecObjet(Activable activable, Objet objet) throws ActivationException{
    activable.activerAvec(objet);
  }

  /**
   * Permet de déposer un Objet détenu par le Vivant. Ainsi, l'Objet est ajouté aux Objet de la Piece. L'Objet déposé
   * est alors retiré des Objet détenus par le Vivant.
   *
   * @param objet
   *        L'Objet à déposer dans la Piece
   */
  public void deposer(Objet objet) throws ObjetNonPossedeParLeVivantException {
    if (!(this.possede(objet)))
      throw new ObjetNonPossedeParLeVivantException("Le vivant ne possède pas l'objet" + objet.getNom());
    piece.deposer(lesObjets.remove(objet.getNom()));
  }

  /**
   * Permet de déposer un Objet de nom "nomObjet" détenu par le Vivant. Ainsi, l'Objet est ajouté aux Objet de la Piece. L'Objet déposé
   * est alors retiré des Objet détenus par le Vivant.
   *
   * @param nomObjet
   *        Le nom de l'Objet à déposer dans la Piece
   */
  public void deposer(String nomObjet) throws ObjetNonPossedeParLeVivantException {
    if (!lesObjets.containsKey(nomObjet))
      throw new ObjetNonPossedeParLeVivantException("Le vivant ne possède pas l'objet" + nomObjet);
    piece.deposer(lesObjets.remove(nomObjet));
  }

  /**
   * Renvoie un booléen indiquant si le Vivant est mort.
   *
   * @return Un booléen indiquant si les points de vie du Vivant sont à 0
   */
  public boolean estMort(){
    return this.PV==0;
  }

  /**
   * Permet à un Vivant de passer dans la Piece située de l'autre côté de la Porte
   * passée en paramètre.
   *
   * @param porte
   *        La Porte à franchir
   */
  public void franchir(Porte porte) throws PorteFermeException, PorteInexistanteDansLaPieceException{
    franchir(porte.getNom());
  }

  /**
   * Permet à un Vivant de passer dans la Piece située de l'autre côté de la Porte
   * dont le nom est passé en paramètre.
   *
   * @param nomPorte
   *        Le nom de la Porte à franchir
   */
  public void franchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException{
    Porte laPorte = this.piece.getPorte(nomPorte);
    if (laPorte == null)
      throw new PorteInexistanteDansLaPieceException("La porte "+ nomPorte +" est inexistante dans la pièce "+ piece.getNom());
    if (laPorte.getEtat().equals(Etat.FERME) || laPorte.getEtat().equals(Etat.VERROUILLE))
      throw new PorteFermeException("La porte "+ nomPorte +" est fermée ou verouillée.");
    this.piece = laPorte.getPieceAutreCote(this.piece);
  }

  /**
   * Renvoie l'Objet de nom "nomObjet" si celui-ci est détenu par le Vivant.
   *
   * @param nomObjet
   *        Le nom de l'Objet à renvoyer s'il est présent dans l'inventaire du Vivant
   *
   * @return L'Objet renvoyé s'il est présent dans l'inventaire du Vivant, "null" sinon
   */
  public Objet getObjet(String nomObjet){
    return lesObjets.get(nomObjet);
  }

  /**
   * Renvoie l'ensemble des Objet détenus par le Vivant.
   *
   * @return l'ensemble des Objet détenus par le Vivant
   */
  public Map<String,Objet> getObjets(){
    return lesObjets;
  }

  /**
   * Renvoie la Piece dans laquelle se trouve le Vivant.
   *
   * @return la Piece où se situe le Vivant
   */
  public Piece getPiece(){
    return this.piece;
  }

  /**
   * Renvoie les points de force du Vivant.
   *
   * @return les points de force du Vivant
   */
  public int getPointForce(){
    return this.PF;
  }

  /**
   * Renvoie les points de vie du Vivant.
   *
   * @return les points de vie du Vivant
   */
  public int getPointVie(){
    return this.PV;
  }

  /**
   * Renvoie un booléen indiquant si le Vivant possède l'Objet "obj".
   *
   * @param obj
   *        L'objet dont on veut regarder s'il est présent dans l'inventaire du Vivant
   *
   * @return Un booléen indiquant si l'Objet "obj" est présent dans l'inventaire du Vivant
   */
  public boolean possede(Objet obj){
    return lesObjets.containsValue(obj);
  }

  /**
   * Permet à un Vivant de prendre un Objet "obj". Ainsi, l'objet pris est ajouté dans l'inventaire du Vivant et
   * est retiré de l'ensemble des Objet présents dans la Piece.
   *
   * @param obj
   *        L'Objet à prendre dans la Piece
   */
  public void prendre(Objet obj) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException {
    if (!(this.piece.contientObjet(obj)))
      throw new ObjetAbsentDeLaPieceException(obj.getNom() + " n'est pas présent dans la piece "+this.piece.getNom());
    if (!obj.estDeplacable())
      throw new ObjetNonDeplacableException(obj.getNom() + " n'est pas déplacable");
    lesObjets.put(obj.getNom(),piece.retirer(obj));
  }

  /**
   * Permet à un Vivant de prendre un Objet de nom "nomObj". Ainsi, l'objet pris est ajouté dans l'inventaire du Vivant et
   * est retiré de l'ensemble des Objet présents dans la Piece.
   *
   * @param nomObj
   *        Le nom de l'Objet à prendre dans la Piece
   */
  public void prendre(String nomObj) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException {
    if (!(this.piece.contientObjet(nomObj)))
      throw new ObjetAbsentDeLaPieceException(nomObj + " n'est pas présent dans la piece "+this.piece.getNom());
    Objet objAPrendre = piece.retirer(nomObj);
    if (!objAPrendre.estDeplacable()){
      throw new ObjetNonDeplacableException(nomObj + " n'est pas déplacable");
    }
    lesObjets.put(nomObj,objAPrendre);
  }

  public void setPointsDeVie(int ptsDeVie){
    this.PV = ptsDeVie;
  }

  /**
   * Renvoie une chaine de caractères contenant les informations propres à un Vivant, telles que le nom du
   * Vivant, les objets détenus par le Vivant, le Monde auquel il appartient, ses points de vie et de force, et
   * la Piece dans laquelle il se situe.
   *
   * @return La chaine de caractères contenant les informations sur le Vivant
   */
  public String toString(){
    StringBuilder laChaine = new StringBuilder("Le vivant ");
    laChaine.append(super.toString());
    laChaine.append(" du monde ");
    laChaine.append(this.getMonde().toString());
    laChaine.append(" - PV : ");
    laChaine.append(getPointVie());
    laChaine.append(" - PF : ");
    laChaine.append(getPointForce());
    laChaine.append("\n");
    laChaine.append(getPiece().toString());
    laChaine.append("\nLe vivant détient");
    laChaine.append(lesObjets.toString());
    return laChaine.toString();
  }

}
