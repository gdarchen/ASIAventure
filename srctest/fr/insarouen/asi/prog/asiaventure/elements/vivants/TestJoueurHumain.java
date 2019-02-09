package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestJoueurHumain {
  private Monde monde;
  private Piece piece;
  private JoueurHumain joueur;
  private Objet[] objets;
  private PiedDeBiche pdb;

  @Before
  public void avantTest() throws Exception {
    monde = new Monde("MondeDeTest");
    piece = new Piece("pieceTest",monde);
    pdb = new PiedDeBiche("pdbTest",monde);
    objets = new Objet[]{pdb, new Coffre("coffreTest",monde)};
    joueur = new JoueurHumain("humainTest",monde,10,15,piece,objets);
  }

  @Test
  public void testExecuterCommandrePrendre() throws Throwable{
    PiedDeBiche pdb2 = new PiedDeBiche("PDB2",monde);
    piece.deposer(pdb2);
    assertThat(piece.contientObjet(pdb2),Is.is(true));
    assertThat(joueur.possede(pdb2),Is.is(false));
    joueur.setOrdre("prendre PDB2");
    joueur.executer();
    assertThat(piece.contientObjet(pdb2),Is.is(false));
    assertThat(joueur.possede(pdb2),Is.is(true));
  }

  @Test(expected=CommandeImpossiblePourLeVivantException.class)
  public void testExecuterCommandreQuiNExistePas() throws Throwable{
    PiedDeBiche pdb2 = new PiedDeBiche("PDB2",monde);
    piece.deposer(pdb2);
    joueur.setOrdre("chocolat PDB2");
    joueur.executer();
  }

  @Test(expected=CommandeImpossiblePourLeVivantException.class)
  public void testExecuterPrendreImpossible() throws Throwable{
    JoueurHumain j2 = new JoueurHumain("humain2",monde,12,18,piece,objets);
    joueur.setOrdre("prendre humain2");
    joueur.executer();
  }

  @Test
  public void testExecuterCommandePoser() throws Throwable{
    assertThat(joueur.possede(pdb),Is.is(true));
    assertThat(piece.contientObjet(pdb),Is.is(false));
    joueur.setOrdre("poser pdbTest");
    joueur.executer();
    assertThat(joueur.possede(pdb),Is.is(false));
    assertThat(piece.contientObjet(pdb),Is.is(true));
  }

  @Test
  public void testExecuterCommandeFranchir() throws Throwable{
    Piece pieceB = new Piece("PieceB",monde);
    Porte porteSansSerrure = new Porte("PorteSansSerrure",monde,piece,pieceB);
    assertThat(joueur.getPiece(), Is.is(piece));
    porteSansSerrure.activer();
    joueur.setOrdre("franchir PorteSansSerrure");
    joueur.executer();
    assertThat(joueur.getPiece(), Is.is(pieceB));
  }

  @Test
  public void testExecuterCommandeOuvrirPorte() throws Throwable {
    Piece pieceB = new Piece("PieceB",monde);
    Porte porteSansSerrure = new Porte("PorteSansSerrure",monde,piece,pieceB);
    assertThat(porteSansSerrure.getEtat(),Is.is(Etat.FERME));
    joueur.setOrdre("ouvrirPorte PorteSansSerrure");
    joueur.executer();
    assertThat(porteSansSerrure.getEtat(),Is.is(Etat.OUVERT));
  }

  @Test
  public void testExecuterCommandeOuvrirPorteAvecObjetClef() throws Throwable{
    Piece pieceB = new Piece("PieceB",monde);
    Serrure serrure = new Serrure(monde);
    Porte porteAvecSerrure = new Porte("PorteAvecSerrure",monde,serrure,piece,pieceB);
    Clef clef = serrure.creerClef();
    piece.deposer(clef);
    joueur.prendre(clef);
    assertThat(porteAvecSerrure.getEtat(),Is.is(Etat.VERROUILLE));
    joueur.setOrdre("ouvrirPorte "+porteAvecSerrure.getNom() +" "+ clef.getNom());
    joueur.executer();
    assertThat(porteAvecSerrure.getEtat(),Is.is(Etat.OUVERT));
  }

  @Test
  public void testExecuterCommandeOuvrirPorteAvecObjetPiedDeBiche() throws Throwable{
    Piece pieceB = new Piece("PieceB",monde);
    Serrure serrure = new Serrure(monde);
    Porte porteAvecSerrure = new Porte("PorteAvecSerrure",monde,serrure,piece,pieceB);
    piece.deposer(pdb);
    joueur.prendre(pdb);
    assertThat(porteAvecSerrure.getEtat(),Is.is(Etat.VERROUILLE));
    joueur.setOrdre("ouvrirPorte "+porteAvecSerrure.getNom() +" "+ pdb.getNom());
    joueur.executer();
    assertThat(porteAvecSerrure.getEtat(),Is.is(Etat.CASSE));
  }
}
