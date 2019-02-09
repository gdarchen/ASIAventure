package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestPiece {
  private Piece piece,pieceB;
  private Monde monde;
  private Porte porte;

  @Before
  public void avantTest() throws Exception{
    monde = new Monde("mondeTest");
    piece = new Piece("pieceTest",monde);
    pieceB = new Piece("pieceTestB",monde);
    porte = new Porte("porteTest",monde,piece,pieceB);
  }

  @Test
  public void testConstructeur() {
    assertThat(piece.getNom(),IsEqual.equalTo("pieceTest"));
    assertThat(monde.getNom(),IsEqual.equalTo("mondeTest"));
  }

  @Test
  public void testAddPorte() throws Exception{
    Piece pieceC = new Piece("pieceTestC",monde);
    Porte porte2 = new Porte("porteTest2",monde,pieceB,pieceC);
    assertThat(piece.aLaPorte(porte2),Is.is(false));
    piece.addPorte(porte2);
    assertThat(piece.getPorte(porte2.getNom()),IsSame.sameInstance(porte2));
  }

  @Test
  public void testALaPorte(){
    assertThat(piece.aLaPorte(porte),Is.is(true));
  }

  @Test
  public void testALaPorteString(){
    assertThat(piece.aLaPorte(porte.getNom()),Is.is(true));
  }

  @Test
  public void testContientObjet() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    assertThat(piece.contientObjet(pdb),IsEqual.equalTo(false));
  }

  @Test
  public void testContientObjetChaine() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    assertThat(piece.contientObjet("pdbTest"),IsEqual.equalTo(false));
  }

  @Test
  public void testContientVivant() throws Exception{
    Piece piece2 = new Piece("pieceTest2",monde);
    Vivant vivant = new Vivant("vivantTest",monde,15,30,piece){};
    assertThat(piece.contientVivant(vivant),IsEqual.equalTo(true));
    assertThat(piece2.contientVivant(vivant),IsEqual.equalTo(false));
  }

  @Test
  public void testContientVivantChaine() throws Exception{
    Piece piece2 = new Piece("pieceTest2",monde);
    Vivant vivant = new Vivant("vivantTest",monde,15,30,piece){};
    assertThat(piece.contientVivant("vivantTest"),IsEqual.equalTo(true));
    assertThat(piece2.contientVivant("vivantTest"),IsEqual.equalTo(false));
  }

  @Test
  public void testDeposer() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    assertThat(piece.contientObjet(pdb),IsEqual.equalTo(false));
    piece.deposer(pdb);
    assertThat(piece.contientObjet(pdb),IsEqual.equalTo(true));
  }

  @Test
  public void testEntrer() throws Exception{
    Piece piece2 = new Piece("pieceTest2",monde);
    Vivant vivant = new Vivant("vivantTest",monde,15,30,piece){};
    assertThat(piece2.contientVivant(vivant),IsEqual.equalTo(false));
    piece2.entrer(vivant);
    assertThat(piece2.contientVivant(vivant),IsEqual.equalTo(true));
  }

  @Test
  public void testGetObjets() throws Exception {
    PiedDeBiche pdb2 = new PiedDeBiche("pdbTest",monde);
    assertThat(piece.getObjets().size(),IsEqual.equalTo(0));
    piece.deposer(pdb2);
    assertThat(piece.getObjets().size(),IsEqual.equalTo(1));
    assertThat(piece.getObjets().get("pdbTest"),IsSame.sameInstance((Objet) pdb2));
  }

  @Test
  public void testGetPorte(){
    assertThat(piece.getPorte(porte.getNom()),IsSame.sameInstance(porte));
  }

  @Test
  public void testRetirer() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    piece.deposer(pdb);
    assertThat(piece.contientObjet(pdb),IsEqual.equalTo(true));
    piece.retirer(pdb);
    assertThat(piece.contientObjet(pdb),IsEqual.equalTo(false));
  }

  @Test
  public void testRetirerChaine() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    piece.deposer(pdb);
    assertThat(piece.contientObjet(pdb),IsEqual.equalTo(true));
    piece.retirer("pdbTest");
    assertThat(piece.contientObjet(pdb),IsEqual.equalTo(false));
  }

  @Test(expected=ObjetAbsentDeLaPieceException.class)
  public void testRetirerObjetAbsentDeLaPiece() throws Exception{
    PiedDeBiche pdb = new PiedDeBiche("pdbTest",monde);
    piece.retirer(pdb);
  }

  @Test(expected=ObjetNonDeplacableException.class)
  public void testRetirerObjetNonDeplacable() throws Exception{
    Objet objNonDep = new Objet("objNonDeplacable",monde){
      public boolean estDeplacable(){
        return false;
      }
    };
    piece.deposer(objNonDep);
    piece.retirer(objNonDep);
  }

  @Test
  public void testSortir() throws Exception{
    Vivant vivant = new Vivant("vivantTest",monde,15,30,piece){};
    assertThat(piece.contientVivant(vivant),IsEqual.equalTo(true));
    piece.sortir(vivant);
    assertThat(piece.contientVivant(vivant),IsEqual.equalTo(false));
  }

  @Test
  public void testSortirChaine() throws Exception{
    Vivant vivant = new Vivant("vivantTest",monde,15,30,piece){};
    assertThat(piece.contientVivant("vivantTest"),IsEqual.equalTo(true));
    piece.sortir(vivant);
    assertThat(piece.contientVivant("vivantTest"),IsEqual.equalTo(false));
  }

  @Test(expected = VivantAbsentDeLaPieceException.class)
  public void testSortirVivantAbsentDeLaPiece() throws Exception{
    Vivant vivant = new Vivant("vivantTest",monde,15,30,piece){};
    Piece piece2 = new Piece("piece2",monde);
    piece2.sortir(vivant);
  }
}
