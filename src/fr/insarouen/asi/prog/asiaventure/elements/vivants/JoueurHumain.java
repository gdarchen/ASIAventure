package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.Monde;

import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;

import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;

import java.util.Arrays;
import java.lang.reflect.*;


/**
 * JoueurHumain est une classe permettant de modéliser un joueur humain dans le jeu, capable d'intéragir avec
 * les éléments du Monde dans lequel il se trouve, en exécutant des ordres.
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public class JoueurHumain extends Vivant {
  /**
   * L'ordre que le JoueurHumain peut exécuter.
   */
  private String ordre;

  //constructeur

  /**
   * Constructeur JoueurHumain<br>
   * A la construction d'un JoueurHumain, le nom du joueur lui est associé tout comme son Monde, ses
   * points de vie et de force, sa Piece et les Objet en sa possession.
   * @param nom
   *        Le nom du JoueurHumain
   * @param monde
   *        Le Monde du JoueurHumain
   * @param pointVie
   *        La quantité de points de vie du JoueurHumain
   * @param pointForce
   *        La quantité de points de force du JoueurHumain
   * @param piece
   *        La Piece dans laquelle doit se trouver le JoueurHumain à sa construction
   * @param objets
   *        Les Objet en possession du JoueurHumain à sa création
   */
  public JoueurHumain(String nom,Monde monde,int pointVie,int pointForce,Piece piece,  Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
    super(nom,monde,pointVie,pointForce,piece,objets);
  }

  //méthodes

  /**
   * Cette méthode a pour but d'associer un ordre à un JoueurHumain.
   * @param ordre
   *        L'ordre à associer au JoueurHumain
   */
  public void setOrdre(String ordre){
    this.ordre = ordre;
  }

  private void commandePrendre(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
    this.prendre(nomObjet);
  }

  private void commandePoser(String nomObjet) throws ObjetNonPossedeParLeVivantException {
    this.deposer(nomObjet);
  }

  private void commandeFranchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException {
    this.franchir(nomPorte);
  }

  private void commandeOuvrirPorte(String nomPorte) throws ActivationException, PorteInexistanteDansLaPieceException{
    this.activerActivable(this.getPiece().getPorte(nomPorte));
  }

  private void commandeOuvrirPorte(String nomPorte, String nomObjet) throws ActivationException, PorteInexistanteDansLaPieceException,ObjetNonPossedeParLeVivantException {
    this.activerActivableAvecObjet(this.getPiece().getPorte(nomPorte),this.getObjet(nomObjet));
  }

  /**
   * Cette méthode a pour but d'éxecuter l'ordre associé à un JoueurHumain.
   */
  @SuppressWarnings("unchecked")
  public void executer() throws CommandeImpossiblePourLeVivantException, Throwable {
    String[] mots = ordre.split(" ");
    String ordreDonne = mots[0];
    ordreDonne = ordreDonne.substring(0,1).toUpperCase()+ordreDonne.substring(1);

    Class[] tabFormel = new Class[mots.length-1];
    for(int i=0; i<tabFormel.length; i++)
      tabFormel[i]=java.lang.String.class;

    try{
      Object[] obj = Arrays.copyOfRange(mots,1,mots.length);
      Class classe = this.getClass();
      Method methode = classe.getDeclaredMethod("commande"+ordreDonne,tabFormel);
      methode.invoke(this,obj);
    }
    catch(NoSuchMethodException e){
      throw new CommandeImpossiblePourLeVivantException("Problème avec la commande '"+this.ordre+"' : cette commande n'existe pas pour le Vivant "+this.getNom(),e);
    }
    catch(IllegalArgumentException e){
      throw new CommandeImpossiblePourLeVivantException("Problème avec la commande '"+this.ordre+"' : paramètres non valides",e);
    }
    catch(InvocationTargetException e){
      System.err.println(e.getCause().getMessage());
    }
  }

  /**
   * Renvoie une chaine de caractères contenant les informations propres à un JoueurHumain, telles que le nom du
   * JoueurHumain, les objets détenus par le JoueurHumain, le Monde auquel il appartient, ses points de vie et de force, et
   * la Piece dans laquelle il se situe.
   *
   * @return La chaine de caractères contenant les informations sur le JoueurHumain
   */
  public String toString(){
    StringBuilder situation = new StringBuilder(String.format("Le JoueurHumain %s est situé dans la pièce %s. Il a %d PV et %d PF.\nIl possède les objets : ",this.getNom(), this.getPiece().getNom(), this.getPointVie(),this.getPointForce()));
    for (Objet o : this.getObjets().values()){
      situation.append(String.format("\n\t\t- %s",o.getNom()));
    }
    return situation.toString();
  }


}
