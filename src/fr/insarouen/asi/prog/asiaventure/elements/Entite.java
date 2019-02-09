package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.*;

import java.io.*;

/**
 * Entite est la classe de base qui permet de représenter tous les élements du jeu.<br>
 * Une Entite appartient à un Monde. <br>
 * Une Entite est caracterisée par :
 * <ul>
 *    <li> Un nom ; </li>
 *    <li> Un Monde. </li>
 * </ul> <br>
 * Entite est une classe abstraite.
 *
 * @see Monde
 *
 * @author Darchen / Wang
 * @version 1.1
 */
public abstract class Entite implements Serializable{

  /**
   * Le nom de l'Entite.
   */
  private String nom;

  /**
   * Le Monde associé à l'Entite.
   */
  private Monde monde;

  public final static int MAXIMUMNOMALEATOIRE=10000;

  // constructeurs

  /**
   * Constructeur Entite.<br>
   * A la construction d'un objet Entite, le "nom" de l'Entite est fixé selon l'entrée "nom"
   * donnée au constructeur et le Monde de l'Entite est fixé selon le paramètre "monde". <br>
   * L'Entite est automatiquement ajoutée au Monde auquel elle appartient à sa création.
   *
   * @param nom
   *        Le nom à associer à l'Entite
   * @param monde
   *        Le Monde auquel appartient l'Entite
   *
   * @see Monde#ajouter
   */
  public Entite (String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
    this.nom=nom;
    this.monde=monde;
    try{
      this.monde.ajouter(this);
    }
    catch(EntiteDejaDansUnAutreMondeException e){
      System.err.println("Exception : l'entité "+this.getNom()+" est déjà présente dans un autre Monde.");
      e.printStackTrace();
    }
  }

  // méthodes

  /**
   * Renvoie le nom de l'Entite.
   *
   * @return le nom de l'Entite sous forme d'une chaîne de caracteres
   */
  public String getNom(){
    return this.nom;
  }

  /**
   * Renvoie le Monde associé à l'Entite.
   *
   * @return le Monde associé à l'Entite
   */
  public Monde getMonde(){
    return this.monde;
  }

  /**
   * Renvoie une chaine de caractères contenant le nom d'une Entite.
   *
   * @return La chaine de caractères contenant le nom de l'Entite
   */
  public String toString(){
    return String.format("%s", this.getNom());
  }

  /**
   * Renvoie un booléen dont la valeur dépend de l'égalite entre l'Entite de laquelle
   * la méthode est appelée et de l'objet passé en paramètre (après transtypage).<br>
   * L'égalite ne teste que l'égalité des classes, des noms des Entite et l'égalité des Monde.
   *
   * @param o
   *        L'objet "Object" dont on veut comparer l'égalité avec l'Entite
   *
   * @return Un booléen vrai si les objets sont égaux, faux dans le cas contraire
   */
  public boolean equals(Object o){
    if (o==this)
      return true;

    if (o.getClass()!=getClass())
      return false;

    Entite entite2=(Entite) o;
    return this.getNom().equals(entite2.getNom()) && (this.getMonde()==entite2.getMonde());
  }

  /**
   * Renvoie un entier de hashCode qui est la somme du hashCode du nom de l'Entite
   * et du hashCode du Monde.
   *
   * @return La somme du hashCode du nom de l'Entite et du hashCode du Monde
  */
  public int hashCode(){
    return getNom().hashCode()+getMonde().hashCode();
  }
}
