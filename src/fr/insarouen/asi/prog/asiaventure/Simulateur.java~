package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.JoueurHumain;
import fr.insarouen.asi.prog.asiaventure.EtatDuJeu;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;

import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 * Simulateur est une classe qui permet de gérer la simulation d'un Monde à partir de différents
 * supports de stockage (fichier de description, sauvegarde...).
 *
 * @author Darchen / Wang
 * @version 1.3
 */
public class Simulateur {
  /**
   * Le Monde du Simulateur.
   */
  private Monde monde;

  /**
   * Les ConditionDeFin associées au Simulateur.
   */
  private Collection<ConditionDeFin> condFin;

  /**
   * L'EtatDuJeu du Simulateur.
   */
  private EtatDuJeu etatDuJeu;

  //constructeurs

  /**
   * Constructeur Simulateur<br>
   * A la construction d'un Simulateur, le Monde "monde" est associé au Simulateur, ainsi que les ConditionDeFin
   * "conditions" et l'EtatDuJeu du Simulateur.
   * @param monde
   *        Le Monde du Simulateur
   * @param conditions
   *        Le tableau de ConditionDeFin à associer au Simulateur
   */
  public Simulateur(Monde monde, ConditionDeFin... conditions){
    this.monde = monde;
    condFin = new LinkedList<ConditionDeFin>();
    for (ConditionDeFin cond : conditions)
      condFin.add(cond);
    etatDuJeu = EtatDuJeu.ENCOURS;
  }

  /**
   * Constructeur Simulateur à partir d'une sauvegarde à déserialiser <br>
   * A la construction d'un Simulateur, le Monde du Simulateur est lu dans le flux entrant de la sauvegarde, ainsi que
   * les ConditionDeFin.
   * @param ois
   *        Le flux entrant associé à la sauvegarde à déserialiser.
   */
  @SuppressWarnings("unchecked")
  public Simulateur(ObjectInputStream ois) throws IOException, ClassNotFoundException{ // Lecture d'une sauvegarde
    this.monde = (Monde)ois.readObject();
    this.condFin = (LinkedList<ConditionDeFin>)ois.readObject();
    this.etatDuJeu = (EtatDuJeu)ois.readObject();
  }

  /**
   * Constructeur Simulateur à partir d'un fichier de description <br>
   * A la construction d'un Simulateur, le Monde du Simulateur est construit selon ce qui est lu dans le fichier associé
   * à la variable "reader". Dans le fichier de description peuvent être lus des Monde, des Piece, des PorteSerrure, des Porte,
   * des Clef, des JoueurHumain, des ConditionDeFinVivantDansPiece.
   * @param reader
   *        Le flux Reader associé au fichier de description à lire.
   */
  public Simulateur(Reader reader) throws IOException,NomDEntiteDejaUtiliseDansLeMondeException{ // Lecture d'un fichier
    this.condFin = new LinkedList<>();
    etatDuJeu = EtatDuJeu.ENCOURS;
    Scanner sc = new Scanner(reader);
    while (sc.hasNext()){
      switch (sc.next()){
        case "Monde" :
          lireMonde(sc);
          break;
        case "Piece" :
          lirePiece(sc);
          break;
        case "PorteSerrure" :
          lirePorteSerrure(sc);
          break;
        case "Porte" :
          lirePorte(sc);
          break;
        case "Clef" :
          lireClef(sc);
          break;
        case "JoueurHumain" :
          lireJoueurHumain(sc);
          break;
        case "ConditionDeFinVivantDansPiece" :
          lireConditionDeFinVivantDansPiece(sc);
          break;
      }
    }
  }

  // méthodes

  /**
   * Cette méthode a pour but de sérialiser le Monde "monde" et les ConditionsDeFin qui lui sont associées.
   * @param oos
   *        Le flux sortant dans lequel le Monde et les ConditionsDeFin doivent être séralisées
   */
	public void enregistrer(ObjectOutputStream oos) throws IOException{
    oos.writeObject(this.monde);
    oos.writeObject(this.condFin);
    oos.writeObject(this.etatDuJeu);
  }

  private void lireMonde(Scanner sc){
    String nomMonde = sc.next();
    nomMonde = nomMonde.substring(1,nomMonde.length()-1);
    this.monde = new Monde(nomMonde);
  }

  private void lirePiece(Scanner sc) throws NomDEntiteDejaUtiliseDansLeMondeException{
    String nomPiece = sc.next();
    nomPiece = nomPiece.substring(1,nomPiece.length()-1);
    new Piece(nomPiece,this.monde);
  }

  private void lirePorteSerrure(Scanner sc) throws NomDEntiteDejaUtiliseDansLeMondeException{
    String nomPorteSerrure = sc.next();
    nomPorteSerrure = nomPorteSerrure.substring(1,nomPorteSerrure.length()-1);
    String nomPieceA = sc.next();
    nomPieceA = nomPieceA.substring(1,nomPieceA.length()-1);
    String nomPieceB = sc.next();
    nomPieceB = nomPieceB.substring(1,nomPieceB.length()-1);
    Serrure serrure = new Serrure(this.monde);
    new Porte(nomPorteSerrure,this.monde,serrure,(Piece)this.monde.getEntite(nomPieceA),(Piece)this.monde.getEntite(nomPieceB));
  }

  private void lirePorte(Scanner sc) throws NomDEntiteDejaUtiliseDansLeMondeException{
    String nomPorte = sc.next();
    nomPorte = nomPorte.substring(1,nomPorte.length()-1);
    String nomPieceA = sc.next();
    nomPieceA = nomPieceA.substring(1,nomPieceA.length()-1);
    String nomPieceB = sc.next();
    nomPieceB = nomPieceB.substring(1,nomPieceB.length()-1);
    new Porte(nomPorte,this.monde,(Piece)this.monde.getEntite(nomPieceA),(Piece)this.monde.getEntite(nomPieceB));
  }

  private void lireClef(Scanner sc) throws NomDEntiteDejaUtiliseDansLeMondeException{
    String nomPorte = sc.next();
    nomPorte = nomPorte.substring(1,nomPorte.length()-1);
    String nomPiece = sc.next();
    nomPiece = nomPiece.substring(1,nomPiece.length()-1);
    Porte porte = (Porte) this.monde.getEntite(nomPorte);
    Clef clef = porte.getSerrure().creerClef();
    Piece piece = (Piece)this.monde.getEntite(nomPiece);
    piece.deposer(clef);
  }

  private void lireJoueurHumain(Scanner sc) throws NomDEntiteDejaUtiliseDansLeMondeException{
    String nomHumain = sc.next();
    nomHumain = nomHumain.substring(1,nomHumain.length()-1);
    int pv = sc.nextInt();
    int pf = sc.nextInt();
    String nomPiece = sc.next();
    nomPiece = nomPiece.substring(1,nomPiece.length()-1);
    new JoueurHumain(nomHumain,this.monde,pv,pf,(Piece)this.monde.getEntite(nomPiece));
  }

  private void lireConditionDeFinVivantDansPiece(Scanner sc) {
    String etat = sc.next();
    String nomVivant = sc.next();
    nomVivant = nomVivant.substring(1,nomVivant.length()-1);
    String nomPiece = sc.next();
    nomPiece = nomPiece.substring(1,nomPiece.length()-1);
    this.condFin.add(new ConditionDeFinVivantDansPiece(EtatDuJeu.valueOf(etat),(Vivant)this.monde.getEntite(nomVivant), (Piece)this.monde.getEntite(nomPiece)));
  }

  /**
   * Cette méthode renvoie l'EtatDuJeu du Simulateur.
   * @return l'EtatDuJeu du Simulateur
   */
  public EtatDuJeu getEtatDuJeu(){
    return etatDuJeu;
  }

  /**
   * Cette méthode a pour but d'ajouter des ConditionDeFin au Simulateur.
   * @param conditions
   *        Les ConditionDeFin à ajouter au Simulateur
   */
  public void ajouterConditionsDeFin(Collection<ConditionDeFin> conditions){
    condFin.addAll(conditions);
  }

  /**
   * Cette méthode a pour but d'ajouter une ConditionDeFin au Simulateur.
   * @param condition
   *        La ConditionDeFin à ajouter au Simulateur
   */
  public void ajouterConditionDeFin(ConditionDeFin condition){
    condFin.add(condition);
  }

  private String afficherSituation(JoueurHumain j){
    StringBuilder laSituation = new StringBuilder("\n");
    laSituation.append(j.toString());
    laSituation.append("\n");
    laSituation.append(j.getPiece().toString());
    return laSituation.toString();
  }

  /**
   * Cette méthode a pour but d'exécuter un tour de jeu. C'est-à-dire que pour chaque JoueurHumain
   * la situation est affichée, et l'utilisateur peut saisir un ordre que le JoueurHumain devra exécuter.
   * Ensuite tous les exécutables s'exécutent. Enfin si aucune des ConditionDeFin du Simulateur n'est vérifiée
   * la méthode renvoie ENCOURS, sinon retourne l'EtatDuJeu du Simulateur.
   * @return EtatDuJeu l'EtatDuJeu selon les explications en description de la méthode
   */
  public EtatDuJeu executerUnTour() throws Throwable{
    Scanner sc = new Scanner(System.in);
    for(Executable e : this.monde.getExecutables()){
      if (e instanceof JoueurHumain){
        JoueurHumain j = (JoueurHumain)e;
        System.out.println(afficherSituation(j));
        System.out.println("\nQue doit faire ce JoueurHumain ?");
        String ordre = sc.nextLine();
        j.setOrdre(ordre);
      }
    }

    for (Executable e : this.monde.getExecutables()){
      try {
        e.executer();
      } catch(ASIAventureException aae){
        aae.printStackTrace();
      }
    }

    for (ConditionDeFin cf : this.condFin){
      EtatDuJeu edjCourrant = cf.verifierCondition();
      if (edjCourrant!=EtatDuJeu.ENCOURS){
        this.etatDuJeu = edjCourrant;
        return edjCourrant;
      }
    }
    this.etatDuJeu = EtatDuJeu.ENCOURS;
    return getEtatDuJeu();
  }

  /**
   * Cette méthode a pour but d'appeler "executerUnTour()" jusqu'à ce que l'EtatDuJeu du Simulateur
   * soit ENCOURS. La méthode renvoie ensuite l'EtatDuJeu du Simulateur.
   * @return EtatDuJeu l'EtatDuJeu du Simulateur (donc ECHEC ou SUCCES)
   */
  public EtatDuJeu executerJusquALaFin() throws Throwable{
    while(getEtatDuJeu() == EtatDuJeu.ENCOURS){
      executerUnTour();
    }
    return getEtatDuJeu();
  }

  /**
   * Cette méthode a pour but d'appeler "executerUnTour()" n fois.
   * @param n
   *        le nombre de tours à exécuter
   * @return EtatDuJeu l'EtatDuJeu du Simulateur après les n tours
   */
  public EtatDuJeu executerNbTours(int n) throws Throwable{
    for (int i=0; i<n; i++){
      executerUnTour();
      if (getEtatDuJeu() != EtatDuJeu.ENCOURS)
        return getEtatDuJeu();
    }
    return getEtatDuJeu();
  }


  //--------------------------MAIN--------------------------------//
  public static void main(String[] args) throws Throwable{
    Reader fichier = new FileReader("./exemplesimulation1.aa");
    Simulateur simulateur = new Simulateur(fichier);
    System.out.println(simulateur.monde);
    System.out.println(simulateur.condFin);
  }
}
