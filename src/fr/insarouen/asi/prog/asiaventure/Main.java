package fr.insarouen.asi.prog.asiaventure;

import fr.insarouen.asi.prog.asiaventure.Simulateur;

import java.io.*;
import java.util.Scanner;

public class Main{
  private static Simulateur simulateur;

  public static void main(String[] args) throws Throwable{
    Scanner sc = new Scanner(System.in);
    int choix=0;
    do{
      afficherMenu();
      System.out.println("Entrez votre choix (1-5) : ");
      choix = sc.nextInt();
        if(choix!=1 && choix !=2 && choix !=3 && choix !=4 && choix!=5)
          System.out.println("Choix invalide, entrez un nombre entre 1 et 5.");
        else
          effectuerAction(choix);
    }
    while(choix!=5);
  }

  private static void afficherMenu(){
    System.out.println("\n --- Menu ---");
    System.out.println("1) jouer ");
    System.out.println("2) charger un fichier de description");
    System.out.println("3) sauver la partie actuelle");
    System.out.println("4) charger une partie");
    System.out.println("5) quitter\n");
  }

  private static void effectuerAction(int choix) throws Throwable{
    switch(choix){
      case 1 :
	      choix1();
        break;
      case 2 :
        choix2();
        break;
      case 3 :
        choix3();
        break;
      case 4 :
        choix4();
        break;
      case 5 :
        break;
    }
  }

  private static void choix1() throws Throwable{
    String choix = "o";
    while(choix.equals("o") && simulateur.getEtatDuJeu()==EtatDuJeu.ENCOURS){
      try{
        simulateur.executerUnTour();
      } catch(ASIAventureException aae){
        System.err.println(aae.getMessage());
      }
      if (simulateur.getEtatDuJeu() == EtatDuJeu.ENCOURS){
        System.out.println("\nSouhaitez vous rejouer ? (o/n)");
        Scanner sc = new Scanner(System.in);
        choix = sc.nextLine();
      }
    }
    String resultatPartie = (simulateur.getEtatDuJeu()==EtatDuJeu.SUCCES) ? "Victoire !" : "Défaite !";
    System.out.println(String.format("Il est impossible de jouer, la partie est terminée. %s",resultatPartie));
  }

  private static void choix2() throws Throwable{
    Reader fichier = new FileReader("./exemplesimulation1.aa");
    simulateur = new Simulateur(fichier);
    System.out.println(simulateur);
    fichier.close();
    System.out.println("Chargement du fichier de description effectué avec succès. \n");
  }

  private static void choix3() throws Throwable {
    if (simulateur == null)
      System.out.println("Aucune partie à sauvegarder.\n");
    else{
      FileOutputStream fichier = new FileOutputStream("./sauvegarde.aa");
      ObjectOutputStream oos = new ObjectOutputStream(fichier);
      simulateur.enregistrer(oos);
      fichier.close();
      System.out.println("Sauvegarde effectuée avec succès. \n");
    }
  }

  private static void choix4() throws Throwable {
    FileInputStream fichier = new FileInputStream("./sauvegarde.aa");
    ObjectInputStream ois = new ObjectInputStream(fichier);
    simulateur = new Simulateur(ois);
    fichier.close();
    System.out.println(simulateur);
    System.out.println("Chargement de la partie effectué avec succès. \n");

  }
}
