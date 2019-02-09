package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import java.util.*;

import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.ASIAventureException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;

/**
 * Monstre est une classe permettant de représenter un Monstre, qui est un Vivant.
 * Un Monstre rentre qui se déplace à travers les pièces en franchissant au hasard
 * les portes (ouvertes ou fermées). Le Monstre doit ramasser tout ce qu'il
 * trouve dans une pièce et déposer tout ce qu'il avait avant.
 * <br> Le Monstre perd un point de vie à chaque franchissement de porte. Une
 * fois mort evidemment le monstre ne peut plus rien faire.
 *
 * @author Darchen / Wang
 * @version 1.1
 */
public class Monstre extends Vivant implements Executable{

  // constructeurs

  /**
   * Constructeur Monstre.<br>
   *
   * @param nom
   *        Le nom à associer au Monstre
   * @param monde
   *        Le Monde auquel appartient le Monstre
   * @param pv
   *        Les points de vie du Monstre
   * @param pf
   *        Les points de force du Monstre
   * @param p
   *        La Piece dans laquelle se situe le Monstre
   */
  public Monstre(String nom, Monde monde, int pv, int pf, Piece p) throws NomDEntiteDejaUtiliseDansLeMondeException {
    super(nom,monde,pv,pf,p);
  }

  //méthodes

  /**
   * Permet de modéliser le comportement d'un Monstre. Il sélectionne une Porte au
   * hasard dans une Piece, l'ouvre si cela est possible, et la franchit.
   * Ensuite, il dépose tous les Objet en sa possession dans la Piece, et prend tous
   * les Objet de la Piece. Le Monstre perd un point de vie à chaque tour.
   */
  public void executer() throws Throwable{
    if(this.estMort())
      throw new Throwable(String.format("Le monstre %s est mort.",this.getNom()));

    Map<String,Porte> lesPortes;
    Porte porteAFranchir;
    Boolean aFranchi = false;
    lesPortes = getPiece().getPortes();
    do {
      porteAFranchir = porteAuHasard(lesPortes);
      if (porteAFranchir==null)
        break;
      try{
        if(porteAFranchir.getEtat()==Etat.FERME)
          this.activerActivable(porteAFranchir);
        this.franchir(porteAFranchir);
        aFranchi = true;
      }catch(ASIAventureException e){
        // Le Monstre n'a pas franchi la Porte
     }
   }while(!aFranchi);
    this.setPointsDeVie(this.getPointVie()-1);
    echangerObjetsDeLaPieceEtDuMonstre();
  }


  private Porte porteAuHasard(Map<String,Porte> lesPortes){
    if (lesPortes.size()==0)
      return null;
    int nombreAleatoire = (int)(lesPortes.size()*Math.random());
    Porte[] tabPortes = lesPortes.values().toArray(new Porte[lesPortes.size()]);
    return tabPortes[nombreAleatoire];
  }

  private void echangerObjetsDeLaPieceEtDuMonstre() throws Throwable{
    Map<String,Objet> objetsDeLaPiece = this.getPiece().getObjets();
    Map<String,Objet> objetsDuMonstre = this.getObjets();
    Map<String,Objet> tmp = new HashMap<>();

    for(Objet o : this.getPiece().getObjets().values()){
      tmp.put(o.getNom(),getPiece().retirer(o));
    }

    for(Objet o : objetsDuMonstre.values()){
      this.deposer(o); // enleve de monstre et ajoute dans piece
    }

    for(Objet o : tmp.values()){
      this.getPiece().deposer(o);
      this.prendre(o);
    }
  }

}
