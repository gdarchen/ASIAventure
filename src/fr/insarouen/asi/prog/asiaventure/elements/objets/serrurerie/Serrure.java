package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;

/**
 * Serrure est une classe permettant de spécialiser les caractéristiques d'une Porte.
 * <br> Une Serrure permet de verrouiller le franchissement d'une Porte.
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public class Serrure extends Objet implements Activable{

  /**
   * La Clef permettant d'ouvrir la Serrure.
   */
  private Clef clef;

  /**
   * L'Etat de la Serrure à un instant donné.
   */
  private Etat etat;

  // constructeurs

  /**
   * Constructeur Serrure.<br>
   * A la construction dans ce constructeur, une Serrure se voit affecter un nom généré aléatoirement.
   *
   * @param monde
   *        Le Monde dans lequel se situe la Serrure
   */
  public Serrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    this(String.format("Serrure_numéro_%s",creerNomAleatoire()),monde);
  }

  /**
   * Constructeur Serrure.<br>
   *
   * @param nom
   *        Le nom de la Serrure
   * @param monde
   *        Le Monde dans lequel se situe la Serrure
   */
  public Serrure (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom,monde);
    clef = creerClef();
    etat = Etat.VERROUILLE;
  }

  //méthodes

  /**
   * Renvoie un booléen indiquant si la Serrure est activable avec l'Objet passé en paramètre.
   *
   * @param obj
   *        L'Objet qui peut potentiellement activer la Serrure
   *
   * @return le booléen indiquant si la Serrure est activable avec l'Objet
   */
  public boolean activableAvec(Objet obj){
    if (obj instanceof PiedDeBiche)
      return true;
    return (obj.equals(this.clef));
  }

  /**
   * Lance une Exception : impossible d'ouvrir une Serrure sans Objet.
   */
  public void activer() throws ActivationException{
    throw new ActivationException("Impossible d'ouvrir une serrure sans clé");
  }

  /**
   * Permet d'activer la Serrure avec l'Objet passé en paramètre, si cela est possible.
   * <br> Peut aussi permettre de verrouiller ou deverrouiller une Serrure.
   *
   * @param obj
   *        L'Objet qui peut potentiellement activer la Porte
   */
  public void activerAvec(Objet obj) throws ActivationImpossibleAvecObjetException{
    if (!activableAvec(obj)){
      throw new ActivationImpossibleAvecObjetException(String.format("Activation impossible avec l'objet %s",obj.getNom()));
    }

    if (obj instanceof PiedDeBiche && this.getEtat()==Etat.VERROUILLE)
      this.etat = Etat.CASSE;
    else {
      if (this.etat.equals(Etat.VERROUILLE)){
        this.etat = Etat.DEVERROUILLE;
      }
      else {
        this.etat = Etat.VERROUILLE;
      }
    }
  }

  /**
   * Permet de créer une Clef permettant d'activer la Serrure.
   *
   * @return la Clef qui peut ouvrir la Serrure
   */
  public Clef creerClef(){
    do {
      try{
        this.clef = null;
        String nomClef = String.format("Clef_%s_de_la_serrure_%s",this.creerNomAleatoire(),this.getNom());
        this.clef = new Clef(nomClef,this.getMonde());
      } catch(NomDEntiteDejaUtiliseDansLeMondeException e) {
        //On reboucle
      }
    } while(clef==null);
    return this.clef;
  }

  /**
   * Renvoie un booléen "false" : une Serrure n'est pas déplacable.
   *
   * @return un booléen "false"
   */
  public boolean estDeplacable(){
    return false;
  }

  /**
   * Renvoie l'Etat d'une Serrure.
   *
   * @return l'Etat de la Serrure
   */
  public Etat getEtat(){
    return this.etat;
  }


  // méthode privée
  private static String creerNomAleatoire(){
    int nombreAleatoire = (int)(MAXIMUMNOMALEATOIRE*Math.random());
    String nomAleatoire = String.format("%d",nombreAleatoire);
    return nomAleatoire;
  }

}
