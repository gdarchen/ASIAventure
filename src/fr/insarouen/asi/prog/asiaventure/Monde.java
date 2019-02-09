package fr.insarouen.asi.prog.asiaventure;

import java.util.*;
import java.io.*;

import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import java.lang.StringBuffer;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;

/**
 * Monde est la classe caractérisant un monde du jeu. <br>
 * Un Monde est caractérisé par les informations suivantes :
 * <ul>
 *    <li> Un nom ; </li>
 *    <li> Une Map(String,Entite). </li>
 * </ul>
 *
 * @see Entite
 *
 * @author Darchen / Wang
 * @version 1.0
 */
public class Monde implements Serializable{

  /**
   * Le nom du monde.
   */
  private String nom;

  /**
   * Une Map de (String,Entite) (initialement nulle) contenant les Entite du Monde.
   */
  private Map<String,Entite> entites = new HashMap<>();

  /**
   * Les Executable dans le Monde
   */
  private Collection<Executable> executables = new LinkedList<>();

  // constructeurs

  /**
   * Constructeur Monde. <br>
   * A la construction d'un objet Monde, le "nom" du Monde est fixé selon
   * l'entrée "nomDuMonde" donnée au constructeur.
   *
   * @param nomDuMonde
   *        Le nom du Monde affecté à la creation
   */
  public Monde(String nomDuMonde){
    this.nom=nomDuMonde;
  }

  // methodes

  /**
   * Ajoute une Entite à la Map "entites" du Monde.
   *
   * @param entite
   *        L'Entite à ajouter
   */
  public void ajouter(Entite entite) throws NomDEntiteDejaUtiliseDansLeMondeException,EntiteDejaDansUnAutreMondeException{
    if (this.getEntite(entite.getNom())!= null)
      throw new NomDEntiteDejaUtiliseDansLeMondeException(entite.getNom()+" déjà présent dans le monde "+entite.getMonde());

    if (!(this.nom.equals(entite.getMonde().getNom())))
      throw new EntiteDejaDansUnAutreMondeException("Problème de symétrie des mondes");

    if (entite instanceof Executable)
      executables.add((Executable)entite);

    this.entites.put(entite.getNom(),entite);
  }

  /**
   * Renvoie une Entite associée a un nom "nomEntite" si celle-ci est présente dans
   * la Map "entites", attribut d'un Monde. <br>
   * Cette fonction renvoie "null" si l'Entite n'est pas présente.
   *
   * @param nomEntite
   *        Le nom de l'Entite que l'on veut chercher dans le Monde
   *
   * @return L'Entite correspondante si elle est trouvée, "null" sinon
   */
  public Entite getEntite(String nomEntite){
    return this.entites.get(nomEntite);
  }

  /**
   * Renvoie le nom du Monde.
   *
   * @return Le nom du Monde
   */
  public String getNom(){
    return this.nom;
  }

  /**
   * Renvoie les Executable du Monde.
   *
   * @return Les Executable du Monde
   */
  public Collection<Executable> getExecutables(){
    return executables;
  }

  /**
   * Renvoie une chaine de caractères contenant les informations permettant de connaitre
   * le nom d'un Monde.  <br>
   * Cette chaine contient le nom du Monde.
   *
   * @return La chaine de caractères contenant le nom du le Monde
   *
   * @see Entite#toString
   */
  public String toString(){
    StringBuilder laChaine = new StringBuilder("");
    laChaine.append(this.getNom());
    laChaine.append("\n Entités : \n");
    for(Entite e : this.entites.values()){
      laChaine.append(String.format(" \t - %s \n",e.getNom()));
    }
    laChaine.append("\n");
    return laChaine.toString();
  }
}
